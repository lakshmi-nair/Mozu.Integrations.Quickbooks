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
 * <p>Java class for QBXMLMsgsRsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QBXMLMsgsRsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ItemQueryRs" type="{}ItemQueryRsType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QBXMLMsgsRsType", propOrder = {
    "itemQueryRs"
})
public class QBXMLMsgsRsType {

    @XmlElement(name = "ItemQueryRs", required = true)
    protected ItemQueryRsType itemQueryRs;

    /**
     * Gets the value of the itemQueryRs property.
     * 
     * @return
     *     possible object is
     *     {@link ItemQueryRsType }
     *     
     */
    public ItemQueryRsType getItemQueryRs() {
        return itemQueryRs;
    }

    /**
     * Sets the value of the itemQueryRs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemQueryRsType }
     *     
     */
    public void setItemQueryRs(ItemQueryRsType value) {
        this.itemQueryRs = value;
    }

}
