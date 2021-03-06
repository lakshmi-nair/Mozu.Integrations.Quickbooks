//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.04 at 03:01:40 PM IST 
//


package com.mozu.qbintegration.model.qbmodel.orderupdate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SalesOrderLineGroupModType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesOrderLineGroupModType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TxnLineID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ItemGroupRef" type="{}ItemGroupRefType"/>
 *         &lt;element name="Quantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UnitOfMeasure" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OverrideUOMSetRef" type="{}OverrideUOMSetRefType"/>
 *         &lt;element name="SalesOrderLineMod" type="{}SalesOrderLineModType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesOrderLineGroupModType", propOrder = {
    "txnLineID",
    "itemGroupRef",
    "quantity",
    "unitOfMeasure",
    "overrideUOMSetRef",
    "salesOrderLineMod"
})
public class SalesOrderLineGroupModType {

    @XmlElement(name = "TxnLineID", required = true)
    protected String txnLineID;
    @XmlElement(name = "ItemGroupRef", required = true)
    protected ItemGroupRefType itemGroupRef;
    @XmlElement(name = "Quantity", required = true)
    protected String quantity;
    @XmlElement(name = "UnitOfMeasure", required = true)
    protected String unitOfMeasure;
    @XmlElement(name = "OverrideUOMSetRef", required = true)
    protected OverrideUOMSetRefType overrideUOMSetRef;
    @XmlElement(name = "SalesOrderLineMod", required = true)
    protected SalesOrderLineModType salesOrderLineMod;

    /**
     * Gets the value of the txnLineID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxnLineID() {
        return txnLineID;
    }

    /**
     * Sets the value of the txnLineID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxnLineID(String value) {
        this.txnLineID = value;
    }

    /**
     * Gets the value of the itemGroupRef property.
     * 
     * @return
     *     possible object is
     *     {@link ItemGroupRefType }
     *     
     */
    public ItemGroupRefType getItemGroupRef() {
        return itemGroupRef;
    }

    /**
     * Sets the value of the itemGroupRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemGroupRefType }
     *     
     */
    public void setItemGroupRef(ItemGroupRefType value) {
        this.itemGroupRef = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantity(String value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the unitOfMeasure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Sets the value of the unitOfMeasure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitOfMeasure(String value) {
        this.unitOfMeasure = value;
    }

    /**
     * Gets the value of the overrideUOMSetRef property.
     * 
     * @return
     *     possible object is
     *     {@link OverrideUOMSetRefType }
     *     
     */
    public OverrideUOMSetRefType getOverrideUOMSetRef() {
        return overrideUOMSetRef;
    }

    /**
     * Sets the value of the overrideUOMSetRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link OverrideUOMSetRefType }
     *     
     */
    public void setOverrideUOMSetRef(OverrideUOMSetRefType value) {
        this.overrideUOMSetRef = value;
    }

    /**
     * Gets the value of the salesOrderLineMod property.
     * 
     * @return
     *     possible object is
     *     {@link SalesOrderLineModType }
     *     
     */
    public SalesOrderLineModType getSalesOrderLineMod() {
        return salesOrderLineMod;
    }

    /**
     * Sets the value of the salesOrderLineMod property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesOrderLineModType }
     *     
     */
    public void setSalesOrderLineMod(SalesOrderLineModType value) {
        this.salesOrderLineMod = value;
    }

}
