//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.05 at 06:20:26 PM IST 
//


package com.mozu.qbintegration.model.qbmodel.salesorderadd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QBXMLMsgsRqType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QBXMLMsgsRqType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SalesOrderAddRq" type="{}SalesOrderAddRqType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="onError" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QBXMLMsgsRqType", propOrder = {
    "salesOrderAddRq"
})
public class QBXMLMsgsRqType {

    @XmlElement(name = "SalesOrderAddRq", required = true)
    protected SalesOrderAddRqType salesOrderAddRq;
    @XmlAttribute(name = "onError")
    protected String onError;

    /**
     * Gets the value of the salesOrderAddRq property.
     * 
     * @return
     *     possible object is
     *     {@link SalesOrderAddRqType }
     *     
     */
    public SalesOrderAddRqType getSalesOrderAddRq() {
        return salesOrderAddRq;
    }

    /**
     * Sets the value of the salesOrderAddRq property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesOrderAddRqType }
     *     
     */
    public void setSalesOrderAddRq(SalesOrderAddRqType value) {
        this.salesOrderAddRq = value;
    }

    /**
     * Gets the value of the onError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnError() {
        return onError;
    }

    /**
     * Sets the value of the onError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnError(String value) {
        this.onError = value;
    }

}