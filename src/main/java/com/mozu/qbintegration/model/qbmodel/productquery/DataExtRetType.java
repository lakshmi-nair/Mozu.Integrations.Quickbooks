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
 * <p>Java class for DataExtRetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataExtRetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OwnerID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DataExtName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DataExtType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DataExtValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataExtRetType", propOrder = {
    "ownerID",
    "dataExtName",
    "dataExtType",
    "dataExtValue"
})
public class DataExtRetType {

    @XmlElement(name = "OwnerID", required = true)
    protected String ownerID;
    @XmlElement(name = "DataExtName", required = true)
    protected String dataExtName;
    @XmlElement(name = "DataExtType", required = true)
    protected String dataExtType;
    @XmlElement(name = "DataExtValue", required = true)
    protected String dataExtValue;

    /**
     * Gets the value of the ownerID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerID() {
        return ownerID;
    }

    /**
     * Sets the value of the ownerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerID(String value) {
        this.ownerID = value;
    }

    /**
     * Gets the value of the dataExtName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataExtName() {
        return dataExtName;
    }

    /**
     * Sets the value of the dataExtName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataExtName(String value) {
        this.dataExtName = value;
    }

    /**
     * Gets the value of the dataExtType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataExtType() {
        return dataExtType;
    }

    /**
     * Sets the value of the dataExtType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataExtType(String value) {
        this.dataExtType = value;
    }

    /**
     * Gets the value of the dataExtValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataExtValue() {
        return dataExtValue;
    }

    /**
     * Sets the value of the dataExtValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataExtValue(String value) {
        this.dataExtValue = value;
    }

}
