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
 * <p>Java class for FixedAssetSalesInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FixedAssetSalesInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SalesDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SalesDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SalesPrice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SalesExpense" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FixedAssetSalesInfoType", propOrder = {
    "salesDesc",
    "salesDate",
    "salesPrice",
    "salesExpense"
})
public class FixedAssetSalesInfoType {

    @XmlElement(name = "SalesDesc", required = true)
    protected String salesDesc;
    @XmlElement(name = "SalesDate", required = true)
    protected String salesDate;
    @XmlElement(name = "SalesPrice", required = true)
    protected String salesPrice;
    @XmlElement(name = "SalesExpense", required = true)
    protected String salesExpense;

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
     * Gets the value of the salesDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesDate() {
        return salesDate;
    }

    /**
     * Sets the value of the salesDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesDate(String value) {
        this.salesDate = value;
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
     * Gets the value of the salesExpense property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesExpense() {
        return salesExpense;
    }

    /**
     * Sets the value of the salesExpense property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesExpense(String value) {
        this.salesExpense = value;
    }

}
