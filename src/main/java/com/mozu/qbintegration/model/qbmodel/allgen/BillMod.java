//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.07 at 08:01:35 PM IST 
//


package com.mozu.qbintegration.model.qbmodel.allgen;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{}TxnCoreMod"/>
 *         &lt;element ref="{}VendorRef" minOccurs="0"/>
 *         &lt;element ref="{}VendorAddress" minOccurs="0"/>
 *         &lt;element ref="{}APAccountRef" minOccurs="0"/>
 *         &lt;element ref="{}TxnDate" minOccurs="0"/>
 *         &lt;element ref="{}DueDate" minOccurs="0"/>
 *         &lt;element name="RefNumber" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{}STRTYPE">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{}TermsRef" minOccurs="0"/>
 *         &lt;element name="Memo" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{}STRTYPE">
 *               &lt;maxLength value="4095"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{}IsTaxIncluded" minOccurs="0"/>
 *         &lt;element ref="{}SalesTaxCodeRef" minOccurs="0"/>
 *         &lt;element ref="{}ExchangeRate" minOccurs="0"/>
 *         &lt;element ref="{}ClearExpenseLines" minOccurs="0"/>
 *         &lt;element ref="{}ExpenseLineMod" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ClearItemLines" minOccurs="0"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{}ItemLineMod"/>
 *           &lt;element ref="{}ItemGroupLineMod"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "txnID",
    "editSequence",
    "vendorRef",
    "vendorAddress",
    "apAccountRef",
    "txnDate",
    "dueDate",
    "refNumber",
    "termsRef",
    "memo",
    "isTaxIncluded",
    "salesTaxCodeRef",
    "exchangeRate",
    "clearExpenseLines",
    "expenseLineMod",
    "clearItemLines",
    "itemLineModOrItemGroupLineMod"
})
@XmlRootElement(name = "BillMod")
public class BillMod {

    @XmlElement(name = "TxnID", required = true)
    protected String txnID;
    @XmlElement(name = "EditSequence", required = true)
    protected String editSequence;
    @XmlElement(name = "VendorRef")
    protected VendorRef vendorRef;
    @XmlElement(name = "VendorAddress")
    protected VendorAddress vendorAddress;
    @XmlElement(name = "APAccountRef")
    protected APAccountRef apAccountRef;
    @XmlElement(name = "TxnDate")
    protected String txnDate;
    @XmlElement(name = "DueDate")
    protected String dueDate;
    @XmlElement(name = "RefNumber")
    protected String refNumber;
    @XmlElement(name = "TermsRef")
    protected TermsRef termsRef;
    @XmlElement(name = "Memo")
    protected String memo;
    @XmlElement(name = "IsTaxIncluded")
    protected String isTaxIncluded;
    @XmlElement(name = "SalesTaxCodeRef")
    protected SalesTaxCodeRef salesTaxCodeRef;
    @XmlElement(name = "ExchangeRate")
    protected String exchangeRate;
    @XmlElement(name = "ClearExpenseLines")
    protected String clearExpenseLines;
    @XmlElement(name = "ExpenseLineMod")
    protected List<ExpenseLineMod> expenseLineMod;
    @XmlElement(name = "ClearItemLines")
    protected String clearItemLines;
    @XmlElements({
        @XmlElement(name = "ItemLineMod", type = ItemLineMod.class),
        @XmlElement(name = "ItemGroupLineMod", type = ItemGroupLineMod.class)
    })
    protected List<Object> itemLineModOrItemGroupLineMod;

    /**
     * Gets the value of the txnID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxnID() {
        return txnID;
    }

    /**
     * Sets the value of the txnID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxnID(String value) {
        this.txnID = value;
    }

    /**
     * Gets the value of the editSequence property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEditSequence() {
        return editSequence;
    }

    /**
     * Sets the value of the editSequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEditSequence(String value) {
        this.editSequence = value;
    }

    /**
     * Gets the value of the vendorRef property.
     * 
     * @return
     *     possible object is
     *     {@link VendorRef }
     *     
     */
    public VendorRef getVendorRef() {
        return vendorRef;
    }

    /**
     * Sets the value of the vendorRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link VendorRef }
     *     
     */
    public void setVendorRef(VendorRef value) {
        this.vendorRef = value;
    }

    /**
     * Gets the value of the vendorAddress property.
     * 
     * @return
     *     possible object is
     *     {@link VendorAddress }
     *     
     */
    public VendorAddress getVendorAddress() {
        return vendorAddress;
    }

    /**
     * Sets the value of the vendorAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link VendorAddress }
     *     
     */
    public void setVendorAddress(VendorAddress value) {
        this.vendorAddress = value;
    }

    /**
     * Gets the value of the apAccountRef property.
     * 
     * @return
     *     possible object is
     *     {@link APAccountRef }
     *     
     */
    public APAccountRef getAPAccountRef() {
        return apAccountRef;
    }

    /**
     * Sets the value of the apAccountRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link APAccountRef }
     *     
     */
    public void setAPAccountRef(APAccountRef value) {
        this.apAccountRef = value;
    }

    /**
     * Gets the value of the txnDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxnDate() {
        return txnDate;
    }

    /**
     * Sets the value of the txnDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxnDate(String value) {
        this.txnDate = value;
    }

    /**
     * Gets the value of the dueDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Sets the value of the dueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDueDate(String value) {
        this.dueDate = value;
    }

    /**
     * Gets the value of the refNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefNumber() {
        return refNumber;
    }

    /**
     * Sets the value of the refNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefNumber(String value) {
        this.refNumber = value;
    }

    /**
     * Gets the value of the termsRef property.
     * 
     * @return
     *     possible object is
     *     {@link TermsRef }
     *     
     */
    public TermsRef getTermsRef() {
        return termsRef;
    }

    /**
     * Sets the value of the termsRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link TermsRef }
     *     
     */
    public void setTermsRef(TermsRef value) {
        this.termsRef = value;
    }

    /**
     * Gets the value of the memo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemo() {
        return memo;
    }

    /**
     * Sets the value of the memo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemo(String value) {
        this.memo = value;
    }

    /**
     * Gets the value of the isTaxIncluded property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsTaxIncluded() {
        return isTaxIncluded;
    }

    /**
     * Sets the value of the isTaxIncluded property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsTaxIncluded(String value) {
        this.isTaxIncluded = value;
    }

    /**
     * Gets the value of the salesTaxCodeRef property.
     * 
     * @return
     *     possible object is
     *     {@link SalesTaxCodeRef }
     *     
     */
    public SalesTaxCodeRef getSalesTaxCodeRef() {
        return salesTaxCodeRef;
    }

    /**
     * Sets the value of the salesTaxCodeRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesTaxCodeRef }
     *     
     */
    public void setSalesTaxCodeRef(SalesTaxCodeRef value) {
        this.salesTaxCodeRef = value;
    }

    /**
     * Gets the value of the exchangeRate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExchangeRate() {
        return exchangeRate;
    }

    /**
     * Sets the value of the exchangeRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExchangeRate(String value) {
        this.exchangeRate = value;
    }

    /**
     * Gets the value of the clearExpenseLines property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClearExpenseLines() {
        return clearExpenseLines;
    }

    /**
     * Sets the value of the clearExpenseLines property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClearExpenseLines(String value) {
        this.clearExpenseLines = value;
    }

    /**
     * Gets the value of the expenseLineMod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the expenseLineMod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExpenseLineMod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExpenseLineMod }
     * 
     * 
     */
    public List<ExpenseLineMod> getExpenseLineMod() {
        if (expenseLineMod == null) {
            expenseLineMod = new ArrayList<ExpenseLineMod>();
        }
        return this.expenseLineMod;
    }

    /**
     * Gets the value of the clearItemLines property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClearItemLines() {
        return clearItemLines;
    }

    /**
     * Sets the value of the clearItemLines property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClearItemLines(String value) {
        this.clearItemLines = value;
    }

    /**
     * Gets the value of the itemLineModOrItemGroupLineMod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemLineModOrItemGroupLineMod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemLineModOrItemGroupLineMod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemLineMod }
     * {@link ItemGroupLineMod }
     * 
     * 
     */
    public List<Object> getItemLineModOrItemGroupLineMod() {
        if (itemLineModOrItemGroupLineMod == null) {
            itemLineModOrItemGroupLineMod = new ArrayList<Object>();
        }
        return this.itemLineModOrItemGroupLineMod;
    }

}
