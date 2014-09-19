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
 *         &lt;element name="Name">
 *           &lt;simpleType>
 *             &lt;restriction base="{}STRTYPE">
 *               &lt;maxLength value="31"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{}IsActive" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element ref="{}PriceLevelFixedPercentage"/>
 *           &lt;group ref="{}PriceLevelPerItemCurrency"/>
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
    "name",
    "isActive",
    "priceLevelFixedPercentage",
    "priceLevelPerItem",
    "currencyRef"
})
@XmlRootElement(name = "PriceLevelAdd")
public class PriceLevelAdd {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "IsActive")
    protected String isActive;
    @XmlElement(name = "PriceLevelFixedPercentage")
    protected String priceLevelFixedPercentage;
    @XmlElement(name = "PriceLevelPerItem")
    protected List<PriceLevelPerItem> priceLevelPerItem;
    @XmlElement(name = "CurrencyRef")
    protected CurrencyRef currencyRef;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the isActive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsActive() {
        return isActive;
    }

    /**
     * Sets the value of the isActive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsActive(String value) {
        this.isActive = value;
    }

    /**
     * Gets the value of the priceLevelFixedPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceLevelFixedPercentage() {
        return priceLevelFixedPercentage;
    }

    /**
     * Sets the value of the priceLevelFixedPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceLevelFixedPercentage(String value) {
        this.priceLevelFixedPercentage = value;
    }

    /**
     * Gets the value of the priceLevelPerItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the priceLevelPerItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPriceLevelPerItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PriceLevelPerItem }
     * 
     * 
     */
    public List<PriceLevelPerItem> getPriceLevelPerItem() {
        if (priceLevelPerItem == null) {
            priceLevelPerItem = new ArrayList<PriceLevelPerItem>();
        }
        return this.priceLevelPerItem;
    }

    /**
     * Gets the value of the currencyRef property.
     * 
     * @return
     *     possible object is
     *     {@link CurrencyRef }
     *     
     */
    public CurrencyRef getCurrencyRef() {
        return currencyRef;
    }

    /**
     * Sets the value of the currencyRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyRef }
     *     
     */
    public void setCurrencyRef(CurrencyRef value) {
        this.currencyRef = value;
    }

}
