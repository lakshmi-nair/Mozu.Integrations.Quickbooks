//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.04 at 02:45:08 PM IST 
//


package com.mozu.qbintegration.model.qbmodel.qborderquery;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RefNumberFilterType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RefNumberFilterType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MatchCriterion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RefNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RefNumberFilterType", propOrder = {
    "matchCriterion",
    "refNumber"
})
public class RefNumberFilterType {

    @XmlElement(name = "MatchCriterion", required = true)
    protected String matchCriterion;
    @XmlElement(name = "RefNumber", required = true)
    protected String refNumber;

    /**
     * Gets the value of the matchCriterion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatchCriterion() {
        return matchCriterion;
    }

    /**
     * Sets the value of the matchCriterion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchCriterion(String value) {
        this.matchCriterion = value;
    }

    /**
     * Gets the value of the refNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefNumber() {
        return refNumber;
    }

    /**
     * Sets the value of the refNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefNumber(String value) {
        this.refNumber = value;
    }

}