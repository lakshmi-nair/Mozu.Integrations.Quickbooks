//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.05 at 06:41:40 PM IST 
//


package com.mozu.qbintegration.model.qbmodel.productquery;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SalesAndPurchaseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesAndPurchaseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SalesDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SalesPrice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncomeAccountRef" type="{}IncomeAccountRefType"/>
 *         &lt;element name="PurchaseDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PurchaseCost" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ExpenseAccountRef" type="{}ExpenseAccountRefType"/>
 *         &lt;element name="PrefVendorRef" type="{}PrefVendorRefType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesAndPurchaseType", propOrder = {
    "salesDesc",
    "salesPrice",
    "incomeAccountRef",
    "purchaseDesc",
    "purchaseCost",
    "expenseAccountRef",
    "prefVendorRef"
})
public class SalesAndPurchaseType {

    @XmlElement(name = "SalesDesc", required = true)
    protected String salesDesc;
    @XmlElement(name = "SalesPrice", required = true)
    protected String salesPrice;
    @XmlElement(name = "IncomeAccountRef", required = true)
    protected IncomeAccountRefType incomeAccountRef;
    @XmlElement(name = "PurchaseDesc", required = true)
    protected String purchaseDesc;
    @XmlElement(name = "PurchaseCost", required = true)
    protected String purchaseCost;
    @XmlElement(name = "ExpenseAccountRef", required = true)
    protected ExpenseAccountRefType expenseAccountRef;
    @XmlElement(name = "PrefVendorRef", required = true)
    protected PrefVendorRefType prefVendorRef;

    /**
     * Gets the value of the salesDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesDesc() {
        return salesDesc;
    }

    /**
     * Sets the value of the salesDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesDesc(String value) {
        this.salesDesc = value;
    }

    /**
     * Gets the value of the salesPrice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesPrice() {
        return salesPrice;
    }

    /**
     * Sets the value of the salesPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesPrice(String value) {
        this.salesPrice = value;
    }

    /**
     * Gets the value of the incomeAccountRef property.
     * 
     * @return
     *     possible object is
     *     {@link IncomeAccountRefType }
     *     
     */
    public IncomeAccountRefType getIncomeAccountRef() {
        return incomeAccountRef;
    }

    /**
     * Sets the value of the incomeAccountRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link IncomeAccountRefType }
     *     
     */
    public void setIncomeAccountRef(IncomeAccountRefType value) {
        this.incomeAccountRef = value;
    }

    /**
     * Gets the value of the purchaseDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurchaseDesc() {
        return purchaseDesc;
    }

    /**
     * Sets the value of the purchaseDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurchaseDesc(String value) {
        this.purchaseDesc = value;
    }

    /**
     * Gets the value of the purchaseCost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurchaseCost() {
        return purchaseCost;
    }

    /**
     * Sets the value of the purchaseCost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurchaseCost(String value) {
        this.purchaseCost = value;
    }

    /**
     * Gets the value of the expenseAccountRef property.
     * 
     * @return
     *     possible object is
     *     {@link ExpenseAccountRefType }
     *     
     */
    public ExpenseAccountRefType getExpenseAccountRef() {
        return expenseAccountRef;
    }

    /**
     * Sets the value of the expenseAccountRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpenseAccountRefType }
     *     
     */
    public void setExpenseAccountRef(ExpenseAccountRefType value) {
        this.expenseAccountRef = value;
    }

    /**
     * Gets the value of the prefVendorRef property.
     * 
     * @return
     *     possible object is
     *     {@link PrefVendorRefType }
     *     
     */
    public PrefVendorRefType getPrefVendorRef() {
        return prefVendorRef;
    }

    /**
     * Sets the value of the prefVendorRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrefVendorRefType }
     *     
     */
    public void setPrefVendorRef(PrefVendorRefType value) {
        this.prefVendorRef = value;
    }

}
