//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.07 at 08:01:35 PM IST 
//


package com.mozu.qbintegration.model.qbmodel.allgen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ItemAssembliesCanBuildQueryRqType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ItemAssembliesCanBuildQueryRqType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{}ItemAssembliesCanBuildQuery"/>
 *       &lt;/sequence>
 *       &lt;attribute name="requestID" type="{}STRTYPE" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemAssembliesCanBuildQueryRqType", propOrder = {
    "itemInventoryAssemblyRef",
    "txnDate"
})
public class ItemAssembliesCanBuildQueryRqType {

    @XmlElement(name = "ItemInventoryAssemblyRef", required = true)
    protected ItemInventoryAssemblyRef itemInventoryAssemblyRef;
    @XmlElement(name = "TxnDate", required = true)
    protected String txnDate;
    @XmlAttribute(name = "requestID")
    protected String requestID;

    /**
     * Gets the value of the itemInventoryAssemblyRef property.
     * 
     * @return
     *     possible object is
     *     {@link ItemInventoryAssemblyRef }
     *     
     */
    public ItemInventoryAssemblyRef getItemInventoryAssemblyRef() {
        return itemInventoryAssemblyRef;
    }

    /**
     * Sets the value of the itemInventoryAssemblyRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemInventoryAssemblyRef }
     *     
     */
    public void setItemInventoryAssemblyRef(ItemInventoryAssemblyRef value) {
        this.itemInventoryAssemblyRef = value;
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
     * Gets the value of the requestID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestID() {
        return requestID;
    }

    /**
     * Sets the value of the requestID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestID(String value) {
        this.requestID = value;
    }

}
