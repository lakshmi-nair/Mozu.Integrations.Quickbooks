//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.07 at 08:01:35 PM IST 
//


package com.mozu.qbintegration.model.qbmodel.allgen;

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
 *         &lt;element ref="{}AccountantCopyExists"/>
 *         &lt;element ref="{}DividingDate" minOccurs="0"/>
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
    "accountantCopyExists",
    "dividingDate"
})
@XmlRootElement(name = "AccountantCopy")
public class AccountantCopy {

    @XmlElement(name = "AccountantCopyExists", required = true)
    protected String accountantCopyExists;
    @XmlElement(name = "DividingDate")
    protected String dividingDate;

    /**
     * Gets the value of the accountantCopyExists property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountantCopyExists() {
        return accountantCopyExists;
    }

    /**
     * Sets the value of the accountantCopyExists property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountantCopyExists(String value) {
        this.accountantCopyExists = value;
    }

    /**
     * Gets the value of the dividingDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDividingDate() {
        return dividingDate;
    }

    /**
     * Sets the value of the dividingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDividingDate(String value) {
        this.dividingDate = value;
    }

}
