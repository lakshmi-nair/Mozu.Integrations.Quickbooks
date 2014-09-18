//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.04 at 02:52:52 PM IST 
//


package com.mozu.qbintegration.model.qbmodel.productadd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ItemInventoryAddRsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ItemInventoryAddRsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ItemInventoryRet" type="{}ItemInventoryRetType"/>
 *         &lt;element name="ErrorRecovery" type="{}ErrorRecoveryType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="statusSeverity" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="statusMessage" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemInventoryAddRsType", propOrder = {
    "itemInventoryRet",
    "errorRecovery"
})
public class ItemInventoryAddRsType {

    @XmlElement(name = "ItemInventoryRet", required = true)
    protected ItemInventoryRetType itemInventoryRet;
    @XmlElement(name = "ErrorRecovery", required = true)
    protected ErrorRecoveryType errorRecovery;
    @XmlAttribute(name = "statusCode")
    protected String statusCode;
    @XmlAttribute(name = "statusSeverity")
    protected String statusSeverity;
    @XmlAttribute(name = "statusMessage")
    protected String statusMessage;

    /**
     * Gets the value of the itemInventoryRet property.
     * 
     * @return
     *     possible object is
     *     {@link ItemInventoryRetType }
     *     
     */
    public ItemInventoryRetType getItemInventoryRet() {
        return itemInventoryRet;
    }

    /**
     * Sets the value of the itemInventoryRet property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemInventoryRetType }
     *     
     */
    public void setItemInventoryRet(ItemInventoryRetType value) {
        this.itemInventoryRet = value;
    }

    /**
     * Gets the value of the errorRecovery property.
     * 
     * @return
     *     possible object is
     *     {@link ErrorRecoveryType }
     *     
     */
    public ErrorRecoveryType getErrorRecovery() {
        return errorRecovery;
    }

    /**
     * Sets the value of the errorRecovery property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorRecoveryType }
     *     
     */
    public void setErrorRecovery(ErrorRecoveryType value) {
        this.errorRecovery = value;
    }

    /**
     * Gets the value of the statusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the statusSeverity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusSeverity() {
        return statusSeverity;
    }

    /**
     * Sets the value of the statusSeverity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusSeverity(String value) {
        this.statusSeverity = value;
    }

    /**
     * Gets the value of the statusMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Sets the value of the statusMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusMessage(String value) {
        this.statusMessage = value;
    }

}