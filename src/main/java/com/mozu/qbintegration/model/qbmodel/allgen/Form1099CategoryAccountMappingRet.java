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
 *         &lt;element ref="{}IsFiling1099Misc"/>
 *         &lt;element ref="{}CategoryAccountMapping" maxOccurs="unbounded" minOccurs="0"/>
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
    "isFiling1099Misc",
    "categoryAccountMapping"
})
@XmlRootElement(name = "Form1099CategoryAccountMappingRet")
public class Form1099CategoryAccountMappingRet {

    @XmlElement(name = "IsFiling1099Misc", required = true)
    protected String isFiling1099Misc;
    @XmlElement(name = "CategoryAccountMapping")
    protected List<CategoryAccountMapping> categoryAccountMapping;

    /**
     * Gets the value of the isFiling1099Misc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsFiling1099Misc() {
        return isFiling1099Misc;
    }

    /**
     * Sets the value of the isFiling1099Misc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsFiling1099Misc(String value) {
        this.isFiling1099Misc = value;
    }

    /**
     * Gets the value of the categoryAccountMapping property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categoryAccountMapping property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategoryAccountMapping().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CategoryAccountMapping }
     * 
     * 
     */
    public List<CategoryAccountMapping> getCategoryAccountMapping() {
        if (categoryAccountMapping == null) {
            categoryAccountMapping = new ArrayList<CategoryAccountMapping>();
        }
        return this.categoryAccountMapping;
    }

}
