//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.07 at 08:01:35 PM IST 
//


package com.mozu.qbintegration.model.qbmodel.allgen;

import java.math.BigInteger;
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
 *         &lt;element ref="{}LocationID"/>
 *         &lt;element name="Location">
 *           &lt;simpleType>
 *             &lt;restriction base="{}STRTYPE">
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{}LeadAddress" minOccurs="0"/>
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
    "locationID",
    "location",
    "leadAddress"
})
@XmlRootElement(name = "LocationsMod")
public class LocationsMod {

    @XmlElement(name = "LocationID", required = true)
    protected BigInteger locationID;
    @XmlElement(name = "Location", required = true)
    protected String location;
    @XmlElement(name = "LeadAddress")
    protected LeadAddress leadAddress;

    /**
     * Gets the value of the locationID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLocationID() {
        return locationID;
    }

    /**
     * Sets the value of the locationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLocationID(BigInteger value) {
        this.locationID = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the leadAddress property.
     * 
     * @return
     *     possible object is
     *     {@link LeadAddress }
     *     
     */
    public LeadAddress getLeadAddress() {
        return leadAddress;
    }

    /**
     * Sets the value of the leadAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link LeadAddress }
     *     
     */
    public void setLeadAddress(LeadAddress value) {
        this.leadAddress = value;
    }

}
