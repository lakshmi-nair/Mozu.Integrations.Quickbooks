/**
 * 
 */
package com.mozu.qbintegration.service;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mozu.api.ApiContext;
import com.mozu.api.ApiException;
import com.mozu.api.MozuApiContext;
import com.mozu.api.contracts.commerceruntime.discounts.AppliedDiscount;
import com.mozu.api.contracts.commerceruntime.discounts.AppliedLineItemProductDiscount;
import com.mozu.api.contracts.commerceruntime.orders.Order;
import com.mozu.api.contracts.commerceruntime.orders.OrderCollection;
import com.mozu.api.contracts.commerceruntime.orders.OrderItem;
import com.mozu.api.contracts.commerceruntime.products.Product;
import com.mozu.api.contracts.customer.CustomerAccount;
import com.mozu.api.contracts.customer.CustomerContact;
import com.mozu.api.contracts.mzdb.EntityCollection;
import com.mozu.api.contracts.mzdb.EntityContainer;
import com.mozu.api.contracts.mzdb.EntityContainerCollection;
import com.mozu.api.contracts.sitesettings.application.Application;
import com.mozu.api.resources.commerce.OrderResource;
import com.mozu.api.resources.platform.entitylists.EntityContainerResource;
import com.mozu.api.resources.platform.entitylists.EntityResource;
import com.mozu.api.utils.JsonUtils;
import com.mozu.qbintegration.handlers.CustomerHandler;
import com.mozu.qbintegration.handlers.OrderHandler;
import com.mozu.qbintegration.model.GeneralSettings;
import com.mozu.qbintegration.model.MozuOrderDetail;
import com.mozu.qbintegration.model.MozuProduct;
import com.mozu.qbintegration.model.OrderCompareDetail;
import com.mozu.qbintegration.model.OrderConflictDetail;
import com.mozu.qbintegration.model.ProductToMapToQuickbooks;
import com.mozu.qbintegration.model.ProductToQuickbooks;
import com.mozu.qbintegration.model.QuickBooksSavedOrderLine;
import com.mozu.qbintegration.model.SubnavLink;
import com.mozu.qbintegration.model.qbmodel.allgen.AssetAccountRef;
import com.mozu.qbintegration.model.qbmodel.allgen.BillAddress;
import com.mozu.qbintegration.model.qbmodel.allgen.COGSAccountRef;
import com.mozu.qbintegration.model.qbmodel.allgen.CustomerAdd;
import com.mozu.qbintegration.model.qbmodel.allgen.CustomerAddRqType;
import com.mozu.qbintegration.model.qbmodel.allgen.CustomerQueryRqType;
import com.mozu.qbintegration.model.qbmodel.allgen.CustomerQueryRsType;
import com.mozu.qbintegration.model.qbmodel.allgen.CustomerRef;
import com.mozu.qbintegration.model.qbmodel.allgen.IncomeAccountRef;
import com.mozu.qbintegration.model.qbmodel.allgen.ItemInventoryAdd;
import com.mozu.qbintegration.model.qbmodel.allgen.ItemInventoryAddRqType;
import com.mozu.qbintegration.model.qbmodel.allgen.ItemQueryRqType;
import com.mozu.qbintegration.model.qbmodel.allgen.ItemRef;
import com.mozu.qbintegration.model.qbmodel.allgen.QBXML;
import com.mozu.qbintegration.model.qbmodel.allgen.QBXMLMsgsRq;
import com.mozu.qbintegration.model.qbmodel.allgen.SalesOrderAdd;
import com.mozu.qbintegration.model.qbmodel.allgen.SalesOrderLineAdd;
import com.mozu.qbintegration.model.qbmodel.allgen.SalesOrderLineMod;
import com.mozu.qbintegration.model.qbmodel.allgen.SalesOrderMod;
import com.mozu.qbintegration.model.qbmodel.allgen.SalesTaxCodeRef;
import com.mozu.qbintegration.tasks.WorkTask;
import com.mozu.base.utils.ApplicationUtils;
import com.mozu.qbintegration.utils.EntityHelper;

/**
 * @author Akshay
 * 
 */
@Service
public class QuickbooksServiceImpl implements QuickbooksService {

	private static final String QBXML_PREFIX = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
			+ "<?qbxml version=\"13.0\"?>";

	private static final Logger logger = LoggerFactory
			.getLogger(QuickbooksServiceImpl.class);

	private static ObjectMapper mapper = JsonUtils.initObjectMapper();
	
	// Heavy object, initialize in constructor
	private JAXBContext contextObj = null;

	// One time as well
	Marshaller marshallerObj = null;

	@Autowired
	private QueueManagerService queueManagerService;

	@Autowired 
	CustomerHandler customerHandler;
	
	@Autowired 
	OrderHandler orderHandler;
	
	
	public QuickbooksServiceImpl() throws JAXBException {
		contextObj = JAXBContext.newInstance(QBXML.class);
		marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
	}

	@Override
	public String getQBCustomerSaveXML(final String orderId, final CustomerAccount customerAccount) {

		QBXML qbXML = new QBXML();
		QBXMLMsgsRq qbxmlMsgsRqType = new QBXMLMsgsRq();
		qbXML.setQBXMLMsgsRq(qbxmlMsgsRqType);
		qbxmlMsgsRqType.setOnError("stopOnError");

		CustomerAddRqType qbXMLCustomerAddRqType = new CustomerAddRqType();
		qbxmlMsgsRqType
				.getHostQueryRqOrCompanyQueryRqOrCompanyActivityQueryRq().add(
						qbXMLCustomerAddRqType);

		// Set customer information
		CustomerAdd qbXMCustomerAddType = new CustomerAdd();
		qbXMLCustomerAddRqType.setRequestID(orderId);
		qbXMLCustomerAddRqType.setCustomerAdd(qbXMCustomerAddType);
		qbXMCustomerAddType.setFirstName(customerAccount.getFirstName());
		qbXMCustomerAddType.setLastName(customerAccount.getLastName());
		qbXMCustomerAddType.setMiddleName("");
		qbXMCustomerAddType.setName(customerAccount.getFirstName() + " "
				+ customerAccount.getLastName());
		qbXMCustomerAddType.setPhone(customerAccount.getContacts().get(0)
				.getPhoneNumbers().getMobile());
		qbXMCustomerAddType.setEmail(customerAccount.getEmailAddress());
		qbXMCustomerAddType.setContact("Self");

		// Set billing address
		BillAddress qbXMLBillAddressType = new BillAddress();
		qbXMCustomerAddType.setBillAddress(qbXMLBillAddressType);

		CustomerContact cc = customerAccount.getContacts().get(0);
		qbXMLBillAddressType.setAddr1(cc.getAddress().getAddress1());
		qbXMLBillAddressType.setCity(cc.getAddress().getCityOrTown());
		qbXMLBillAddressType.setState(cc.getAddress().getStateOrProvince());
		qbXMLBillAddressType.setCountry(cc.getAddress().getCountryCode());
		qbXMLBillAddressType
				.setPostalCode(cc.getAddress().getPostalOrZipCode());

		return getMarshalledValue(qbXML);
	}

	@Override
	public String getQBCustomerUpdateXML(final Order order, final CustomerAccount customerAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQBCustomerGetXML(final Order order, final CustomerAccount orderingCustomer) {

		QBXML qbXML = new QBXML();
		QBXMLMsgsRq qbxmlMsgsRq = new QBXMLMsgsRq();
		qbXML.setQBXMLMsgsRq(qbxmlMsgsRq);
		qbxmlMsgsRq.setOnError("stopOnError");
		CustomerQueryRqType customerQueryRqType = new CustomerQueryRqType();
		customerQueryRqType.setRequestID(order.getId());
		
		qbxmlMsgsRq.getHostQueryRqOrCompanyQueryRqOrCompanyActivityQueryRq()
				.add(customerQueryRqType);

		customerQueryRqType.getFullName().add(
				orderingCustomer.getFirstName() + " "
						+ orderingCustomer.getLastName());

		return getMarshalledValue(qbXML);
	}

	/* (non-Javadoc)
	 * @see com.mozu.qbintegration.service.QuickbooksService#getQBOrderSaveXML(com.mozu.api.contracts.commerceruntime.orders.Order, java.lang.String, java.util.List)
	 */
	@Override
	public String getQBOrderSaveXML(Order singleOrder, String customerQBListID,
			List<String> itemListIDs) {
		QBXML qbxml = new QBXML();
		QBXMLMsgsRq qbxmlMsgsRq = new QBXMLMsgsRq();
		qbxml.setQBXMLMsgsRq(qbxmlMsgsRq);
		com.mozu.qbintegration.model.qbmodel.allgen.SalesOrderAddRqType salesOrderAddRqType = new com.mozu.qbintegration.model.qbmodel.allgen.SalesOrderAddRqType();
		qbxmlMsgsRq.getHostQueryRqOrCompanyQueryRqOrCompanyActivityQueryRq()
				.add(salesOrderAddRqType);
		qbxmlMsgsRq.setOnError("stopOnError");
		SalesOrderAdd salesOrderAdd = new SalesOrderAdd();
		salesOrderAddRqType.setRequestID(singleOrder.getId());
		
		salesOrderAddRqType.setSalesOrderAdd(salesOrderAdd);
		CustomerRef customerRef = new CustomerRef();
		customerRef.setListID(customerQBListID);
		salesOrderAdd.setCustomerRef(customerRef);

		List<OrderItem> items = singleOrder.getItems();
		ItemRef itemRef = null;
		SalesOrderLineAdd salesOrderLineAdd = null;

		NumberFormat numberFormat = new DecimalFormat("#.00");
		int counter = 0;
		
		//Need to accumulate item discounts -> order discount contains these as well. So 
		//while adding pure order level discount, need to subtract.
		Double productDiscounts = 0.0;
		for (OrderItem item : items) {
			itemRef = new ItemRef();
			itemRef.setListID(itemListIDs.get(counter));
			salesOrderLineAdd = new SalesOrderLineAdd();
			if(item.getUnitPrice().getSaleAmount() != null) {
				salesOrderLineAdd.setAmount(numberFormat.format(
						item.getUnitPrice().getSaleAmount() * item.getQuantity()));
			} else {
				salesOrderLineAdd.setAmount(numberFormat.format(
						item.getUnitPrice().getListAmount() * item.getQuantity()));
			}
			salesOrderLineAdd.setItemRef(itemRef);
			salesOrderLineAdd.setQuantity(item.getQuantity().toString());
			
			// salesOrderLineAdd.setRatePercent("7.5"); // (getItemtaxTotal *
			// 100)/
			// itemTaxableTotal
			salesOrderAdd.getSalesOrderLineAddOrSalesOrderLineGroupAdd().add(
					salesOrderLineAdd);
			
			//IMP Oct 4th 2014 - add discount line items. For now, just add generic discount DISC-PRODUCT
			if(item.getDiscountTotal() > 0.0) {
				productDiscounts += item.getDiscountTotal();
				addSOAddLineItemAmount(salesOrderAdd, numberFormat.format(item.getDiscountTotal()), "DISC-PRODUCT");
			}
			counter++;
		}
		
		//IMP: Oct 4th 2014 - Now add shipping item!
		addSOAddLineItemAmount(salesOrderAdd, numberFormat.format(singleOrder.getShippingTotal()), "Shipping");
		
		//IMP: Oct 4th 2014 - Now add order level discounts! Subtraction is to get pure order discount.
		addSOAddLineItemAmount(salesOrderAdd, 
				numberFormat.format(singleOrder.getDiscountTotal() - productDiscounts), "DISC-ORDER");
		
		return getMarshalledValue(qbxml);
	}
	
	/*
	 * Get various amounts associated with order as line items
	 */
	private void addSOAddLineItemAmount(SalesOrderAdd salesOrderAdd, String amount, String fieldName) {
		SalesOrderLineAdd salesOrderLineAdd = new SalesOrderLineAdd();
		salesOrderLineAdd.setAmount(amount);
		ItemRef itemRef = new ItemRef();
		itemRef.setFullName(fieldName);
		salesOrderLineAdd.setItemRef(itemRef);
		salesOrderAdd.getSalesOrderLineAddOrSalesOrderLineGroupAdd().add(salesOrderLineAdd);
	}

	@Override
	public String getQBOrderUpdateXML(Order singleOrder, String customerQBListID, List<String> itemListIDs, MozuOrderDetail postedOrder) throws Exception {
		QBXML qbxml = new QBXML();
		QBXMLMsgsRq qbxmlMsgsRq = new QBXMLMsgsRq();
		qbxml.setQBXMLMsgsRq(qbxmlMsgsRq);
		com.mozu.qbintegration.model.qbmodel.allgen.SalesOrderModRqType salesOrderModRqType = 
				new com.mozu.qbintegration.model.qbmodel.allgen.SalesOrderModRqType();
		qbxmlMsgsRq.getHostQueryRqOrCompanyQueryRqOrCompanyActivityQueryRq()
				.add(salesOrderModRqType);
		qbxmlMsgsRq.setOnError("stopOnError");
		SalesOrderMod salesOrdermod = new SalesOrderMod();
		salesOrderModRqType.setRequestID(singleOrder.getId());
		
		salesOrderModRqType.setSalesOrderMod(salesOrdermod);
		CustomerRef customerRef = new CustomerRef();
		customerRef.setListID(customerQBListID);
		salesOrdermod.setCustomerRef(customerRef);
		
		salesOrdermod.setTxnID(postedOrder.getQuickbooksOrderListId());
		salesOrdermod.setEditSequence(postedOrder.getEditSequence());
		
		List<OrderItem> items = singleOrder.getItems();
		ItemRef itemRef = null;
		SalesOrderLineMod salesOrderLineMod = null;

		NumberFormat numberFormat = new DecimalFormat("#.00");

		List<QuickBooksSavedOrderLine> orderlines = null;
		
		if(!postedOrder.getSavedOrderLines().isEmpty()) {
			try {
				Object converted = mapper.readValue(postedOrder.getSavedOrderLines(), Object.class);
				
				List<LinkedHashMap<String, String>> values = (List<LinkedHashMap<String, String>>) converted;
				orderlines = new ArrayList<QuickBooksSavedOrderLine>();
				
				QuickBooksSavedOrderLine booksSavedOrderLine = null;
				for(LinkedHashMap<String, String> singleVal: values) {
					booksSavedOrderLine = new QuickBooksSavedOrderLine();
					booksSavedOrderLine.setProductCode(singleVal.get("productCode"));
					booksSavedOrderLine.setQbLineItemTxnID("-1");
					orderlines.add(booksSavedOrderLine);
				}
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
			}
		}
		
		int counter = 0;
		
		//Need to accumulate item discounts -> order discount contains these as well. So 
		//while adding pure order level discount, need to subtract.
		Double productDiscounts = 0.0;
		for (OrderItem item : items) {
			itemRef = new ItemRef();
			
			itemRef.setListID(itemListIDs.get(counter));
			salesOrderLineMod = new SalesOrderLineMod();
			
			if(orderlines != null) {
				for(QuickBooksSavedOrderLine singleLine: orderlines) {
					if(singleLine.getProductCode().toLowerCase().equals(item.getProduct().getProductCode().toLowerCase())) {
						salesOrderLineMod.setTxnLineID(singleLine.getQbLineItemTxnID());
					}
				}
			}
			
			if(item.getUnitPrice().getSaleAmount() != null) {
				salesOrderLineMod.setAmount(numberFormat.format(
						item.getUnitPrice().getSaleAmount() * item.getQuantity()));
			} else {
				salesOrderLineMod.setAmount(numberFormat.format(
						item.getUnitPrice().getListAmount() * item.getQuantity()));
			}
			
			salesOrderLineMod.setItemRef(itemRef);
			
			//4-Oct-2014 - Set quantity for update as well.
			salesOrderLineMod.setQuantity(item.getQuantity().toString());
			// salesOrderLineAdd.setRatePercent("7.5"); // (getItemtaxTotal *
			// 100)/
			// itemTaxableTotal
			salesOrdermod.getSalesOrderLineModOrSalesOrderLineGroupMod().add(salesOrderLineMod);
			
			//IMP Oct 4th 2014 - add discount line items. For now, just add generic discount DISC-PRODUCT
			if(item.getDiscountTotal() > 0.0) {
				productDiscounts += item.getDiscountTotal();
				addSOModLineItemAmount(salesOrdermod, 
						numberFormat.format(item.getDiscountTotal()), "DISC-PRODUCT");
			}
			
			counter++;
		}
		
		//IMP: Oct 4th 2014 - Now add shipping items!
		addSOModLineItemAmount(salesOrdermod, numberFormat.format(singleOrder.getShippingTotal()), 
				"Shipping");
		
		//IMP: Oct 4th 2014 - Now add order level discounts!
		addSOModLineItemAmount(salesOrdermod, 
				numberFormat.format(singleOrder.getDiscountTotal() - productDiscounts), "DISC-ORDER");
		
		return getMarshalledValue(qbxml);
	}

	/*
	 * Get various amounts associated with updated order as line items
	 */
	private void addSOModLineItemAmount(SalesOrderMod salesOrdermod, String amount, String fieldName) {
		SalesOrderLineMod salesOrderLineMod = new SalesOrderLineMod();
		salesOrderLineMod.setAmount(amount);
		ItemRef itemRef = new ItemRef();
		itemRef.setFullName(fieldName);
		salesOrderLineMod.setItemRef(itemRef);
		salesOrderLineMod.setTxnLineID("-1");
		salesOrdermod.getSalesOrderLineModOrSalesOrderLineGroupMod().add(salesOrderLineMod);
	}

	@Override
	public String getQBOrderGetXML(final Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQBProductSaveXML(ProductToQuickbooks productToQuickbooks) {
		QBXML qbxml = new QBXML();
		QBXMLMsgsRq qbxmlMsgsRqType = new QBXMLMsgsRq();
		qbxml.setQBXMLMsgsRq(qbxmlMsgsRqType);
		qbxmlMsgsRqType.setOnError("stopOnError");
		ItemInventoryAddRqType addRqType = new ItemInventoryAddRqType();
		
		addRqType.setRequestID(productToQuickbooks.getItemNameNumber());
		
		qbxmlMsgsRqType
				.getHostQueryRqOrCompanyQueryRqOrCompanyActivityQueryRq().add(
						addRqType);
		ItemInventoryAdd inventoryAdd = new ItemInventoryAdd();
		addRqType.setItemInventoryAdd(inventoryAdd);
		inventoryAdd.setName(productToQuickbooks.getItemNameNumber());
		inventoryAdd.setIsActive("true");

		// TODO move these to either prop files or get these details from
		// customer
		SalesTaxCodeRef salesTax = new SalesTaxCodeRef();
		salesTax.setFullName(productToQuickbooks.getItemTaxCode()); //Get tax code from user
		inventoryAdd.setSalesTaxCodeRef(salesTax);

		IncomeAccountRef incomeAccount = new IncomeAccountRef(); // TODO get client's details
		incomeAccount.setFullName(productToQuickbooks.getItemIncomeAccount());
		inventoryAdd.setIncomeAccountRef(incomeAccount);
		
		AssetAccountRef assetAccount = new AssetAccountRef(); // TODO get client's details
		assetAccount.setFullName("Inventory Asset");
		inventoryAdd.setAssetAccountRef(assetAccount);
		
		COGSAccountRef cogsAccountRef = new COGSAccountRef();
		cogsAccountRef.setFullName(productToQuickbooks.getItemExpenseAccount()); // TODO get client's details
		inventoryAdd.setCOGSAccountRef(cogsAccountRef);

		NumberFormat numberFormat = new DecimalFormat("#.00");
		inventoryAdd.setSalesDesc(productToQuickbooks.getItemSalesDesc());
		inventoryAdd.setSalesPrice(numberFormat.format(Double.valueOf(productToQuickbooks.getItemSalesPrice())));

		return getMarshalledValue(qbxml);
	}

	@Override
	public String getQBProductsGetXML(final String orderId, String productCode) {

		QBXML qbxml = new QBXML();
		QBXMLMsgsRq qbxmlMsgsRqType = new QBXMLMsgsRq();

		qbxmlMsgsRqType.setOnError("stopOnError");
		qbxml.setQBXMLMsgsRq(qbxmlMsgsRqType);
		ItemQueryRqType itemQueryRqType = new ItemQueryRqType();
		itemQueryRqType.getFullName().add(productCode);
		itemQueryRqType.setRequestID(orderId);

		qbxmlMsgsRqType.getHostQueryRqOrCompanyQueryRqOrCompanyActivityQueryRq().add(itemQueryRqType);

		return getMarshalledValue(qbxml);
	}
	
	@Override
	public String getAllQBProductsGetXML(Integer tenantId, Integer siteId) {

		QBXML qbxml = new QBXML();
		QBXMLMsgsRq qbxmlMsgsRqType = new QBXMLMsgsRq();

		qbxmlMsgsRqType.setOnError("stopOnError");
		qbxml.setQBXMLMsgsRq(qbxmlMsgsRqType);
		ItemQueryRqType itemQueryRqType = new ItemQueryRqType();

		qbxmlMsgsRqType
				.getHostQueryRqOrCompanyQueryRqOrCompanyActivityQueryRq().add(
						itemQueryRqType);

		return getMarshalledValue(qbxml);
	}

	@Override
	public void saveOrderInQuickbooks(String orderId,Integer tenantId, Integer siteId) throws Exception {
		Order order = orderHandler.getOrder(orderId, tenantId, siteId);
		saveOrderInQuickbooks(order, tenantId, siteId);
	}
	
	@Override
	public void saveOrderInQuickbooks(Order order,Integer tenantId, Integer siteId) {

		try {

			/*
			 * customerListId - the PK of this customer saved in quickbooks 3
			 * ways to get this - entity list query fetch (fastest and the
			 * best), then querying QB, then adding to QB and entityList //
			 * (slowest)
			 */
			CustomerAccount custAcct = customerHandler.getCustomer(tenantId, order.getCustomerAccountId());
			
			String customerListId = null;

			// Check in entity list first
			String isCustInEntityList = getCustFromEntityList(custAcct,tenantId);

			if (null != isCustInEntityList) { // this is the most probable condition at all times once customer is saved.
				customerListId = isCustInEntityList;
				
				//TODO 1. Since customer is present, check for items. This logic is duplicated in endpoint, so
				// refactor into single method once everything works.
				boolean allItemsInEntityList = true;
				List<String> itemListIds = new ArrayList<String>();
				for(OrderItem singleItem: order.getItems()) {
					String itemQBListId = getProductFromEntityList(singleItem, tenantId, siteId);
					if(null == itemQBListId) { //TODO 2. item not found in entity list. So issue a search to QB.
						allItemsInEntityList = false;

						this.addItemQueryTaskToQueue(order.getId(), tenantId, siteId, singleItem.getProduct().getProductCode());
						
						
					}
					itemListIds.add(itemQBListId); //list will anyway be discarded if above flag is false, so no null
				}
				
				if(allItemsInEntityList) { //Add order ADD task if all items are already present in EL
					//Check if there is a pending work item
					//if( queueManagerService.taskExists(tenantId, order.getId()) ) return;
					this.addOrderAddTaskToQueue(tenantId, siteId, custAcct, order, itemListIds);
				}
				
			} else { //Customer not found, so add task to check in qb and let it go
				//TODO all logic has been moved to EL based queue into the endpoint. Refactor it
				addCustomerQueryTaskToQueue(order, custAcct, tenantId, siteId); 
			}

			logger.debug("Entire process completed for order id: " + order.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Different exception: " + e.getMessage());
		}

	}

	@Override
	public void saveCustInEntityList(CustomerAccount custAcct,
			String customerListId, Integer tenantId, Integer siteId) {

		JsonNodeFactory nodeFactory = new JsonNodeFactory(false);
		ObjectNode custNode = nodeFactory.objectNode();

		custNode.put("custEmail", custAcct.getEmailAddress());
		custNode.put("custQBListID", customerListId);
		custNode.put("custName",
				custAcct.getFirstName() + " " + custAcct.getLastName());

		// Add the mapping entry
		JsonNode rtnEntry = null;
		String mapName = EntityHelper.getCustomerEntityName();
		EntityResource entityResource = new EntityResource(new MozuApiContext(
				tenantId)); 
		try {
			rtnEntry = entityResource.insertEntity(custNode, mapName);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error saving customer in entity list: "
					+ custAcct.getEmailAddress());
		}
		logger.debug("Retrieved entity: " + rtnEntry);
		logger.debug("Returning");
	}

	/**
	 * Get the customer based on the email address
	 * 
	 * @param custAcct
	 * @return
	 * @throws Exception 
	 */
	@Override
	public String getCustFromEntityList(CustomerAccount custAcct,Integer tenantId) throws Exception {
		String entityIdValue = custAcct.getEmailAddress();
		String mapName = EntityHelper.getCustomerEntityName();

		EntityResource entityResource = new EntityResource(new MozuApiContext(tenantId));
		String qbListID = null;
		try {
			JsonNode entity = entityResource.getEntity(mapName, entityIdValue);
			JsonNode result = entity.findValue("custQBListID");
			if (result != null) {
				qbListID = result.asText();
			}
		} catch (ApiException e) {
			if (!StringUtils.equals(e.getApiError().getErrorCode(),	"ITEM_NOT_FOUND")) {
				logger.error("Error retrieving entity for email id: "+ entityIdValue);
				throw e;
			}
		}
		return qbListID;
	}

	@Override
	public void saveProductInEntityList(OrderItem orderItem,
			String qbProdustListID, Integer tenantId, Integer siteId) {

		JsonNodeFactory nodeFactory = new JsonNodeFactory(false);
		ObjectNode custNode = nodeFactory.objectNode();

		custNode.put("productCode", orderItem.getProduct().getProductCode());
		custNode.put("qbProdustListID", qbProdustListID);
		custNode.put("productName", orderItem.getProduct().getName());

		// Add the mapping entry
		JsonNode rtnEntry = null;
		String mapName = EntityHelper.getProductEntityName();
		EntityResource entityResource = new EntityResource(new MozuApiContext(
				tenantId)); 
		try {
			rtnEntry = entityResource.insertEntity(custNode, mapName);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error saving product in entity list: "
					+ orderItem.getProduct().getProductCode());
		}
		logger.debug("Retrieved entity: " + rtnEntry);
		logger.debug("Returning");
	}

	/**
	 * Get the product quickbooks list id based on product code
	 * 
	 * @param orderItem
	 * @return
	 */
	@Override
	public String getProductFromEntityList(OrderItem orderItem,
			Integer tenantId, Integer siteId) {
		String entityIdValue = orderItem.getProduct().getProductCode();
		String mapName = EntityHelper.getProductEntityName();

		EntityResource entityResource = new EntityResource(new MozuApiContext(
				tenantId));
		String qbListID = null;
		try {
			JsonNode entity = entityResource.getEntity(mapName, entityIdValue);
			JsonNode result = entity.findValue("qbProdustListID");
			if (result != null) {
				qbListID = result.asText();
			}
		} catch (Exception e) {
			logger.error("Error retrieving entity for product code: "
					+ entityIdValue);
		}
		return qbListID;
	}

	@Override
	public GeneralSettings saveOrUpdateSettingsInEntityList(
			GeneralSettings generalSettings, Integer tenantId, String serverUrl) throws Exception {

		// First get an entity for settings if already present.
		MozuApiContext context =new MozuApiContext(tenantId); 
		EntityResource entityResource = new EntityResource(context); 
		String mapName = EntityHelper.getSettingEntityName();
		generalSettings.setId(tenantId.toString());
		boolean isUpdate = false;

		try {
			entityResource.getEntity(mapName, tenantId.toString());
			isUpdate = true;
		} catch (ApiException e) {
			if (!StringUtils.equals(e.getApiError().getErrorCode(),"ITEM_NOT_FOUND")) {
				logger.error(e.getMessage(),e);
				throw e;
			}
		}

		JsonNode custNode = mapper.valueToTree(generalSettings);
		try {
			if (!isUpdate) { // insert scenario.
				custNode = entityResource.insertEntity(custNode, mapName);
			} else {
				custNode = entityResource.updateEntity(custNode, mapName,generalSettings.getId());
			}

			Application application = ApplicationUtils.setApplicationToInitialized(context);
			addUpdateExtensionLinks(tenantId, application, serverUrl);
		} catch (ApiException e) {
			logger.error("Error saving settings for tenant id: " + tenantId, e);
			throw e;
		}

		return generalSettings;
	}

	@Override
	public GeneralSettings getSettingsFromEntityList(Integer tenantId) throws Exception {

		// First get an entity for settings if already present.
		EntityResource entityResource = new EntityResource(new MozuApiContext(
				tenantId)); 
		JsonNode savedEntry = null;
		String mapName = EntityHelper.getSettingEntityName();

		try {
			savedEntry = entityResource.getEntity(mapName, tenantId.toString());
		} catch (ApiException e) {
			if (!StringUtils.equals(e.getApiError().getErrorCode(),"ITEM_NOT_FOUND"))
				throw e;
		}

		GeneralSettings savedSettings = null;
		if (savedEntry != null) {
			savedSettings = mapper.readValue(savedEntry.toString(), GeneralSettings.class);
		}
		return savedSettings;
	}

	/**
	 * Marshal the inout qbxml.
	 * 
	 * @param qbxml
	 * @return
	 */
	private String getMarshalledValue(QBXML qbxml) {
		String qbXMLStr = null;
		try {
			StringWriter writer = new StringWriter();
			marshallerObj.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			marshallerObj.marshal(qbxml, writer);
			qbXMLStr = QBXML_PREFIX + writer.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return qbXMLStr;
	}

	/**
	 * Just return un-marshalled object. individual callers will get it
	 * converted since they know what they are looking for
	 * 
	 * @param respFromQB
	 * @return
	 */
	@Override
	public Object getUnmarshalledValue(String respFromQB) {
		Object umValue = null;
		try {
			Unmarshaller unmarshallerObj = contextObj.createUnmarshaller();
			Reader r = new StringReader(respFromQB);
			umValue = unmarshallerObj.unmarshal(r);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return umValue;
	}

	@Override
	public void setNextTask(WorkTask workTask, Integer tenantId) {
		//Decide which task will be processed next
		if("CUST_QUERY".equals(workTask)) {
			//This was a cust query task. So check if we found or not. 
			//If found, save in entity list. If not found, we need to add
			// Resumes with response.
			QBXML response = (QBXML) getUnmarshalledValue(workTask.getQbTaskResponse());
			CustomerQueryRsType custQueryResponse = (CustomerQueryRsType) response.getQBXMLMsgsRs()
					.getHostQueryRsOrCompanyQueryRsOrCompanyActivityQueryRs()
					.get(0);

			if ("warn".equalsIgnoreCase(custQueryResponse.getStatusSeverity())
					&& 500 == custQueryResponse.getStatusCode().intValue()) {
				
			} else {
				//saveCustInEntityList(custAcct, customerListId, tenantId, siteId)
			}
			
		}
		
	}
	
	
	public void addCustomerQueryTaskToQueue(Order order, CustomerAccount custAcct, Integer tenantId, Integer siteId) throws Exception {
		String requestXml = getQBCustomerGetXML(order, custAcct);
		queueManagerService.addTask(tenantId, siteId, order.getId(), "ENTERED", "CUST_QUERY", requestXml);
		
	}

	/* (non-Javadoc)
	 * @see com.mozu.qbintegration.service.QuickbooksService#addOrderAddTaskToQueue(java.lang.String, java.lang.Integer, java.lang.Integer, com.mozu.api.contracts.customer.CustomerAccount, com.mozu.api.contracts.commerceruntime.orders.Order, java.util.List)
	 */
	@Override
	public void addOrderAddTaskToQueue(Integer tenantId,Integer siteId, CustomerAccount custAcct, Order order,List<String> itemListIds) throws Exception {
		String custQBListID = getCustFromEntityList(custAcct, tenantId);
		String requestXml = getQBOrderSaveXML(order, custQBListID,itemListIds);
		queueManagerService.addTask(tenantId, siteId, order.getId(), "ENTERED", "ORDER_ADD", requestXml);
	}
	
	/* (non-Javadoc)
	 * @see com.mozu.qbintegration.service.QuickbooksService#addOrderAddTaskToQueue(java.lang.String, java.lang.Integer, java.lang.Integer, com.mozu.api.contracts.customer.CustomerAccount, com.mozu.api.contracts.commerceruntime.orders.Order, java.util.List)
	 */
	@Override
	public void addOrderUpdateTaskToQueue(String orderId, Integer tenantId,	Integer siteId, CustomerAccount custAcct, Order order,List<String> itemListIds, MozuOrderDetail postedOrder) throws Exception {
		String custQBListID = getCustFromEntityList(custAcct, tenantId);
		String requestXml = getQBOrderUpdateXML(order, custQBListID,itemListIds, postedOrder);
		queueManagerService.addTask(tenantId, siteId, order.getId(), "ENTERED", "ORDER_UPDATE", requestXml);
	}

	/* (non-Javadoc)
	 * @see com.mozu.qbintegration.service.QuickbooksService#addItemQueryTaskToQueue(java.lang.String, java.lang.Integer, java.lang.Integer, com.mozu.api.contracts.commerceruntime.orders.Order, java.lang.String)
	 */
	@Override
	public void addItemQueryTaskToQueue(String orderId, Integer tenantId,Integer siteId, String productCode) throws Exception {
		String requestXml = getQBProductsGetXML(orderId, productCode);
		queueManagerService.addTask(tenantId, siteId, orderId, "ENTERED", "ITEM_QUERY", requestXml, true);
		
	}

	@Override
	public void addCustAddTaskToQueue(String orderId, Integer tenantId,	Integer siteId,	CustomerAccount custAcct) throws Exception {
		String requestXml = getQBCustomerSaveXML(orderId, custAcct);
		queueManagerService.addTask(tenantId, siteId, orderId, "ENTERED", "CUST_ADD", requestXml);
	}

	/* (non-Javadoc)
	 * @see com.mozu.qbintegration.service.QuickbooksService#saveOrderInEntityList(com.mozu.qbintegration.model.MozuOrderDetails, com.mozu.api.contracts.customer.CustomerAccount, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void saveOrderInEntityList(MozuOrderDetail orderDetails, String mapName,Integer tenantId, Integer siteId) {
		saveOrUpdateOrderInEL(orderDetails, mapName, tenantId, siteId, Boolean.FALSE);
	}
	
	/* (non-Javadoc)
	 * @see com.mozu.qbintegration.service.QuickbooksService#updateOrderInEntityList(com.mozu.qbintegration.model.MozuOrderDetails, com.mozu.api.contracts.customer.CustomerAccount, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void updateOrderInEntityList(MozuOrderDetail orderDetails, String mapName,	Integer tenantId, Integer siteId) {
		saveOrUpdateOrderInEL(orderDetails, mapName, tenantId, siteId, Boolean.TRUE);
	}
	
	//Used for saving to orders as well as updated orders entity lists
	private void saveOrUpdateOrderInEL(MozuOrderDetail orderDetails,String mapName, Integer tenantId, Integer siteId, Boolean isUpdate) {
		JsonNode orderNode = getOrderNode(orderDetails, tenantId, siteId);
		// First get an entity for settings if already present.
		EntityResource entityResource = new EntityResource(new MozuApiContext(tenantId)); 
		
		// Add the mapping entry
		JsonNode rtnEntry = null;
		try {
			if(!isUpdate) {
				rtnEntry = entityResource.insertEntity(orderNode, mapName);
			} else {
				
				//2nd Oct '14 update - Now that the key is enteredTime, get the record first to get the id
				MozuOrderDetail updateCriteria = new MozuOrderDetail();
				updateCriteria.setOrderStatus("UPDATED");
				updateCriteria.setMozuOrderNumber(orderDetails.getMozuOrderNumber());
				
				List<MozuOrderDetail> updatedOrdList = getMozuOrderDetails(tenantId, updateCriteria, EntityHelper.getOrderUpdatedEntityName());
				String enteredTime = updatedOrdList.get(0).getEnteredTime();
				((ObjectNode)orderNode).put("enteredTime", enteredTime);
				rtnEntry = entityResource.updateEntity(orderNode, mapName, enteredTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error saving order details for tenant id: " + tenantId);
		}
		logger.debug("Saved or updated order in entity list: " + rtnEntry);
		logger.debug("Returning");
		
	}

	private JsonNode getOrderNode(MozuOrderDetail orderDetails, Integer tenantId, Integer siteId) {
		JsonNodeFactory nodeFactory = new JsonNodeFactory(false);
		ObjectNode taskNode = nodeFactory.objectNode();
		taskNode.put("enteredTime", orderDetails.getEnteredTime());
		taskNode.put("mozuOrderNumber", orderDetails.getMozuOrderNumber());
		taskNode.put("mozuOrderId", orderDetails.getMozuOrderNumber());
		taskNode.put("quickbooksOrderListId", orderDetails.getQuickbooksOrderListId() == null ? 
				"" : orderDetails.getQuickbooksOrderListId()); //For item we need to save multiple tasks
		taskNode.put("tenantId", tenantId);
		taskNode.put("siteId", siteId);
		taskNode.put("orderStatus", orderDetails.getOrderStatus());
		taskNode.put("customerEmail", orderDetails.getCustomerEmail());
		taskNode.put("orderDate", orderDetails.getOrderDate());
		taskNode.put("orderUpdatedDate", orderDetails.getOrderUpdatedDate());
		taskNode.put("conflictReason", orderDetails.getConflictReason());
		taskNode.put("amount", orderDetails.getAmount());
		taskNode.put("editSequence", orderDetails.getEditSequence());
		
		//Also save item txn ids returned from qb if any
		try {
			if(orderDetails.getSavedOrderLinesList() != null) {
				List<QuickBooksSavedOrderLine> savedOrderLines = orderDetails.getSavedOrderLinesList();
				taskNode.put("savedOrderLines", mapper.writeValueAsString(savedOrderLines));
			} else {
				taskNode.put("savedOrderLines", mapper.writeValueAsString(mapper.createArrayNode()));
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("Error saving qb item tx ids for order id: " + 
					orderDetails.getMozuOrderNumber() + ", tenant: " + tenantId);
		}
		
		return taskNode;
	}
	
	@Override
	public List<MozuOrderDetail> getMozuOrderDetails(Integer tenantId, MozuOrderDetail mozuOrderDetails, String mapName) throws Exception {

		// First get an entity for settings if already present.
		EntityResource entityResource = new EntityResource(new MozuApiContext(tenantId)); 
		
		StringBuilder sb = new StringBuilder();
		//Assuming status will never be null - it is meaningless to filter without it at this point.
		//TODO throw exception if status is null
		sb.append("orderStatus eq " + mozuOrderDetails.getOrderStatus());
		
		if(mozuOrderDetails.getMozuOrderNumber() != null) {
			sb.append(" and mozuOrderNumber eq " + mozuOrderDetails.getMozuOrderNumber());
		}

		List<MozuOrderDetail> mozuOrders = new ArrayList<MozuOrderDetail>();
		EntityCollection orderCollection = null;
		
		try {
			orderCollection = entityResource.getEntities(mapName, null, null, sb.toString(), "enteredTime desc", null);
			if (null != orderCollection) {
				for (JsonNode singleOrder : orderCollection.getItems()) {
					mozuOrders.add(mapper.readValue(singleOrder.toString(), MozuOrderDetail.class));
				}
			}
		} catch (Exception e) {
			logger.error("Error saving settings for tenant id: " + tenantId);
			throw e;
		}
		return mozuOrders;
		
	}

	/* (non-Javadoc)
	 * @see com.mozu.qbintegration.service.QuickbooksService#saveConflictInEntityList(java.lang.Integer, java.lang.String, java.util.List)
	 */
	@Override
	public void saveConflictInEntityList(Integer tenantId, Integer mozuOrderNumber,	List<OrderConflictDetail> conflictReasons) {
		// First get an entity for settings if already present.
		EntityResource entityResource = new EntityResource(new MozuApiContext(tenantId)); 
		String mapName = EntityHelper.getOrderConflictEntityName();
		
		for(OrderConflictDetail reason: conflictReasons) {
			ObjectNode conflictNode = mapper.valueToTree(reason);
			conflictNode.put("enteredTime", String.valueOf(System.currentTimeMillis()));
			conflictNode.put("mozuOrderId", reason.getMozuOrderNumber());
			
			JsonNode rtnEntry = null;
			try {
				rtnEntry = entityResource.insertEntity(conflictNode, mapName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("Error saving order conflict details for order id: " + mozuOrderNumber);
			}
			logger.info("RtnEntry: " + rtnEntry);
		}
	}

	@Override
	public List<OrderConflictDetail> getOrderConflictReasons(Integer tenantId, String orderId) {
		// First get an entity for settings if already present.
		EntityResource entityResource = new EntityResource(new MozuApiContext(
				tenantId)); 
		String mapName = EntityHelper.getOrderConflictEntityName();
		
		EntityCollection orderConflictCollection = null;
		
		List<OrderConflictDetail> conflictDetails = new ArrayList<OrderConflictDetail>();
		try {
			orderConflictCollection = entityResource.getEntities(mapName, null, null, 
					"mozuOrderId eq " + orderId, null, null);
			
			if (null != orderConflictCollection) {
				for (JsonNode singleOrderConflict : orderConflictCollection.getItems()) {
					conflictDetails.add(mapper.readValue(singleOrderConflict.toString(), OrderConflictDetail.class));
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Error getting order conflict details for order id: " + orderId);
		}
		
		return conflictDetails;
	}

	@Override
	public void saveNewProductToQB(
			ProductToQuickbooks productToQuickbooks, Integer tenantId,
			Integer siteId) {
		String qbXML = getQBProductSaveXML(productToQuickbooks);
		WorkTask itemQueryTask = new WorkTask();
		//Just to make it unique
		itemQueryTask.setEnteredTime(System.currentTimeMillis());
		itemQueryTask.setTaskId(productToQuickbooks.getItemNameNumber());//TODO this breaks in endpoint. fix it.
		itemQueryTask.setQbTaskStatus("ENTERED");
		itemQueryTask.setTenantId(tenantId);
		itemQueryTask.setSiteId(siteId);
		itemQueryTask.setQbTaskType("ITEM_ADD");
		itemQueryTask.setQbTaskRequest(qbXML);
		queueManagerService.saveTask(itemQueryTask, tenantId);
	}

	@Override
	public List<OrderCompareDetail> getOrderCompareDetails(Integer tenantId,String mozuOrderNumber) throws Exception {
		
		//Step 1: Get original order from qb_orders EL
		MozuOrderDetail criteria = new MozuOrderDetail();
		criteria.setOrderStatus("POSTED");
		criteria.setMozuOrderNumber(mozuOrderNumber);
		
		//Step 2: Get updated order from qb_updated_orders EL
		MozuOrderDetail criteriaForUpDate = new MozuOrderDetail();
		criteriaForUpDate.setOrderStatus("UPDATED");
		criteriaForUpDate.setMozuOrderNumber(mozuOrderNumber);
		
		//1. Get from EL the order
		List<MozuOrderDetail> postedOrders = getMozuOrderDetails(tenantId, 
				criteria, EntityHelper.getOrderEntityName());
		
		//2. Get from EL the updated order
		List<MozuOrderDetail> updatedOrders = getMozuOrderDetails(tenantId, 
				criteriaForUpDate, EntityHelper.getOrderUpdatedEntityName());
		
		//1. Assume one only since mozuOrderNumber is going to be unique for a tenant (or is it?)
		MozuOrderDetail postedOrder = postedOrders.get(0);
		
		//2. Get the updated order
		MozuOrderDetail updatedOrder = updatedOrders.get(0);
		
		//3. Populate one order detail each for each difference
		List<OrderCompareDetail> getOrderCompareData = getOrderCompareData(postedOrder, updatedOrder);
		return getOrderCompareData;
	}

	private List<OrderCompareDetail> getOrderCompareData(
			MozuOrderDetail postedOrder, MozuOrderDetail updatedOrder) {
		List<OrderCompareDetail> compareDetails = new ArrayList<OrderCompareDetail>();
		
		if(postedOrder.getAmount() != null && !postedOrder.getAmount().equals(updatedOrder.getAmount())) {
			OrderCompareDetail orderCompareDetail = new OrderCompareDetail();
			orderCompareDetail.setParameter("Amount");
			orderCompareDetail.setPostedOrderDetail(postedOrder.getAmount());
			orderCompareDetail.setUpdatedOrderDetail(updatedOrder.getAmount());
			compareDetails.add(orderCompareDetail);
		}
		
		return compareDetails;
	}
	
	private void addUpdateExtensionLinks(Integer tenantId, Application application, String serverUrl) throws Exception {
		ApiContext apiContext = new MozuApiContext(tenantId);
		EntityContainerResource entityContainerResource = new EntityContainerResource(apiContext);
		EntityResource entityResource = new EntityResource(apiContext);
		
		EntityContainerCollection collection = entityContainerResource.getEntityContainers(EntityHelper.getSubnavLinksEntityName(),200,null,null,null,null);

		
		SubnavLink postedOrdersLink = new SubnavLink();
		postedOrdersLink.setParentId("orders");
		postedOrdersLink.setAppId(application.getAppId());
		postedOrdersLink.setPath(new String[] {"Quickbooks","Orders","Posted"});
		postedOrdersLink.setWindowTitle("Quickbooks order Management");
		postedOrdersLink.setHref(serverUrl+"/Orders?tab=posted");
		addUpdateSubNavLink(postedOrdersLink, collection, entityResource);
		
		SubnavLink conflictOrdersLink = new SubnavLink();
		conflictOrdersLink.setParentId("orders");
		conflictOrdersLink.setAppId(application.getAppId());
		conflictOrdersLink.setPath(new String[] {"Quickbooks","Orders","Conflicts"});
		conflictOrdersLink.setWindowTitle("Quickbooks order Management");
		conflictOrdersLink.setHref(serverUrl+"/Orders?tab=conflicts");
		addUpdateSubNavLink(conflictOrdersLink, collection, entityResource);
		
		SubnavLink updatedOrdersLink = new SubnavLink();
		updatedOrdersLink.setParentId("orders");
		updatedOrdersLink.setAppId(application.getAppId());
		updatedOrdersLink.setPath(new String[] {"Quickbooks","Orders","Updates"});
		updatedOrdersLink.setWindowTitle("Quickbooks order Management");
		updatedOrdersLink.setHref(serverUrl+"/Orders?tab=updates");

		addUpdateSubNavLink(updatedOrdersLink, collection, entityResource);
	}
	
	private void addUpdateSubNavLink(SubnavLink subNavLink,EntityContainerCollection collection,EntityResource entityResource ) throws Exception {
		boolean updated = false;
		
		for(EntityContainer container: collection.getItems()) {
			String id = container.getId();
			SubnavLink link = mapper.readValue(container.getItem().toString(), SubnavLink.class);
			if (Arrays.equals(link.getPath(), subNavLink.getPath())) {
				JsonNode node = mapper.valueToTree(subNavLink);
				entityResource.updateEntity(node, EntityHelper.getSubnavLinksEntityName(), id);	
				updated = true;
			} 
		}
		
		if (!updated) {
			JsonNode node = mapper.valueToTree(subNavLink);
			entityResource.insertEntity(node, EntityHelper.getSubnavLinksEntityName());			
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mozu.qbintegration.service.QuickbooksService#getMozuProductList(java
	 * .lang.Integer)
	 */
	public List<MozuProduct> getMozuProductList(Integer tenantId) {

		List<MozuProduct> mozuProductList = new ArrayList<MozuProduct>();

		try {
			// First get an entity for settings if already present.
			EntityResource entityResource = new EntityResource(
					new MozuApiContext(tenantId));
			String mapName = EntityHelper.getProductEntityName();
			EntityCollection mozuProductCollection = entityResource
					.getEntities(mapName, 1000, 0, null, null, null);

			if (null != mozuProductCollection) {
				MozuProduct mozuProduct = null;
				for (JsonNode singleOrder : mozuProductCollection.getItems()) {
					mozuProduct = new MozuProduct();
					mozuProduct.setProductCode(singleOrder.get("productCode")
							.asText());
					mozuProduct.setQbProductListID(singleOrder.get(
							"qbProdustListID").asText());
					mozuProduct.setProductName(singleOrder.get("productName")
							.asText());
					mozuProductList.add(mozuProduct);
				}
			}
		} catch (Exception e) {
			logger.error("Exception getting all products from entity list: "
					+ e);
		}
		return mozuProductList;
	}

	/* (non-Javadoc)
	 * @see com.mozu.qbintegration.service.QuickbooksService#mapProductToQBInEL(com.mozu.qbintegration.model.ProductToMapToQuickbooks, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void mapProductToQBInEL(ProductToMapToQuickbooks productToMapToEB,
			Integer tenantId, Integer siteId) {
		
		//Just save it in entity list. User is going to retry the order anyway
		OrderItem orderItem = new OrderItem();
		Product product = new Product();
		orderItem.setProduct(product);
		product.setProductCode(productToMapToEB.getToBeMappedItemNumber());
		product.setName(productToMapToEB.getToBeMappedItemNumber());
		
		saveProductInEntityList(orderItem, productToMapToEB.getSelectedProductToMap(), tenantId, siteId);
		
		logger.debug((new StringBuilder()).append("Saved mapping of a not found item ").
				append(productToMapToEB.getToBeMappedItemNumber()).append(" to an existing qb list id ").
				append(productToMapToEB.getSelectedProductToMap()).append(" in entity list").toString());
		
	}
	
	/* (non-Javadoc)
	 * @see com.mozu.qbintegration.service.QuickbooksService#saveAllProductInEntityList(com.mozu.qbintegration.model.MozuProduct, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void saveAllProductInEntityList(MozuProduct product,
			Integer tenantId, Integer siteId) {

		ObjectNode prodNode = mapper.createObjectNode();

		prodNode.put("productCode", product.getProductCode());
		prodNode.put("qbProdustListID", product.getQbProductListID());
		prodNode.put("productName", product.getProductName());

		OrderItem orderItem = new OrderItem();
		Product productItem = new Product();
		productItem.setProductCode(product.getProductCode());

		orderItem.setProduct(productItem);

		String qbListID = getProductFromEntityList(orderItem, tenantId, siteId);

		// Add the mapping entry
		JsonNode rtnEntry = null;
		String mapName = EntityHelper.getProductEntityName();
		EntityResource entityResource = new EntityResource(new MozuApiContext(
				tenantId));
		try {
			if (qbListID == null) { //insert if not originally present
				rtnEntry = entityResource.insertEntity(prodNode, mapName);
			} else {
				rtnEntry = entityResource.updateEntity(prodNode, mapName,
						product.getProductCode());
			}
		} catch (Exception e) {
			logger.error("Error saving product in entity list during refresh all: "
					+ product.getProductCode());
		}
		logger.debug("Retrieved entity: " + rtnEntry);
		logger.debug("Returning");
	}

	@Override
	public void updateOrdersInQuickbooks(List<String> orderNumberList,Integer tenantId, Integer siteId) throws Exception {
		
		for(String mozuOrderNum: orderNumberList) {
			
			//1. Get the updated data from mozu for the order - data has ben changed
			
			OrderResource resource = new OrderResource(new MozuApiContext(tenantId, siteId));
			Order updatedOrder = null;
			try {
				OrderCollection collection = resource.getOrders(0, 10, null, "orderNumber eq " + mozuOrderNum, null, null, null);
				updatedOrder = collection.getItems().get(0);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
			}
			
			CustomerAccount custAcct = customerHandler.getCustomer(tenantId, updatedOrder.getCustomerAccountId());
			
			List<String> itemListIds = new ArrayList<String>();
			for (OrderItem item : updatedOrder.getItems()) {
				String itemListId = getProductFromEntityList(item, tenantId, siteId);
				itemListIds.add(itemListId);
			}
			
			MozuOrderDetail mozuOrderDetails = new MozuOrderDetail();
			mozuOrderDetails.setOrderStatus("POSTED");
			mozuOrderDetails.setMozuOrderNumber(mozuOrderNum);
			
			List<MozuOrderDetail> postedOrders = getMozuOrderDetails(tenantId, mozuOrderDetails, EntityHelper.getOrderEntityName());
			
			MozuOrderDetail postedOrder = postedOrders.get(0);
			
			addOrderUpdateTaskToQueue(updatedOrder.getId(), tenantId, siteId, custAcct, updatedOrder, itemListIds, postedOrder);
			
			logger.debug("Slotted an order update task for mozu order number: " + mozuOrderNum);
			
		}
		
	}
	
	@Override
	public boolean isOrderProcessed(Integer tenantId, Integer siteId, Integer orderNumber) throws Exception {
		
		EntityResource entityResource = new EntityResource(new MozuApiContext(tenantId));
		try {
			EntityCollection collection = entityResource.getEntities(EntityHelper.getOrderEntityName(), 1, 0, "mozuOrderNumber eq "+orderNumber, null, null);
			return collection.getItems().size() > 0;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		
		return false;
	}

	@Override
	public void updateConflictOrdersInQuickbooks(List<String> orderNumberList,Integer tenantId, Integer siteId) throws Exception {
	
		/*
		 * At this stage, the order was failed for some or the other reason.
		 * So it's always safe to start as if a new order was submitted 
		 */
		for(String mozuOrderNum: orderNumberList) {
			OrderResource resource = new OrderResource(new MozuApiContext(tenantId, siteId));
			Order conflictedOrder = null;
			try {
				OrderCollection collection = resource.getOrders(0, 10, null, "orderNumber eq " + mozuOrderNum, null, null, null);
				conflictedOrder = collection.getItems().get(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(), e);
			}
			
			CustomerAccount custAcct = customerHandler.getCustomer(tenantId,conflictedOrder.getCustomerAccountId());
			
			//Now first mark the orders in CONFLICT status as RETRIED since they are taken up for processing.
			//For now they will remain in entity list in RETRIED status, and if retry fails a new record will
			// be added anyway in CONFLICT status
			MozuOrderDetail criteria = new MozuOrderDetail();
			criteria.setOrderStatus("CONFLICT");
			criteria.setMozuOrderNumber(mozuOrderNum);
			try {
				List<MozuOrderDetail> conflictOrders = getMozuOrderDetails(tenantId, 
						criteria, EntityHelper.getOrderEntityName());
				for(MozuOrderDetail singleOrder: conflictOrders) {
					singleOrder.setOrderStatus("RETRIED");
					JsonNode orderNode = getOrderNode(singleOrder, tenantId, siteId);
					EntityResource entityResource = new EntityResource(new MozuApiContext(tenantId));
					entityResource.updateEntity(orderNode, EntityHelper.getOrderEntityName(), singleOrder.getEnteredTime());
					logger.debug("Updated order number: " + mozuOrderNum + " to RETRIED for tenant ID: " + tenantId);
				}
			} catch (Exception e) {
				logger.error("Error while marking CONFLICT orders as RETRIED, tenantID: " + tenantId);
			}
			
			saveOrderInQuickbooks(conflictedOrder, tenantId, siteId);
			
			logger.debug("Slotted conflicted order for retry with order number: " + mozuOrderNum + " for tenant: " + tenantId);
		}

		logger.debug("Slotted all conflicted orders for retry for tenant: " + tenantId);
	}
	


}
