//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.05 at 06:20:26 PM IST 
//


package com.mozu.qbintegration.model.qbmodel.salesorderadd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SalesOrderAddRqType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesOrderAddRqType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SalesOrderAdd" type="{}SalesOrderAddType"/>
 *         &lt;element name="IncludeRetElement" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesOrderAddRqType", propOrder = {
    "salesOrderAdd",
    "includeRetElement"
})
public class SalesOrderAddRqType {

    @XmlElement(name = "SalesOrderAdd", required = true)
    protected SalesOrderAddType salesOrderAdd;
    @XmlElement(name = "IncludeRetElement", required = true)
    protected String includeRetElement;

    /**
     * Gets the value of the salesOrderAdd property.
     * 
     * @return
     *     possible object is
     *     {@link SalesOrderAddType }
     *     
     */
    public SalesOrderAddType getSalesOrderAdd() {
        return salesOrderAdd;
    }

    /**
     * Sets the value of the salesOrderAdd property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesOrderAddType }
     *     
     */
    public void setSalesOrderAdd(SalesOrderAddType value) {
        this.salesOrderAdd = value;
    }

    /**
     * Gets the value of the includeRetElement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncludeRetElement() {
        return includeRetElement;
    }

    /**
     * Sets the value of the includeRetElement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncludeRetElement(String value) {
        this.includeRetElement = value;
    }

}
