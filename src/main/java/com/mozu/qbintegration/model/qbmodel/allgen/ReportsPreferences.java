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
 *         &lt;element ref="{}AgingReportBasis"/>
 *         &lt;element ref="{}SummaryReportBasis"/>
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
    "agingReportBasis",
    "summaryReportBasis"
})
@XmlRootElement(name = "ReportsPreferences")
public class ReportsPreferences {

    @XmlElement(name = "AgingReportBasis", required = true)
    protected String agingReportBasis;
    @XmlElement(name = "SummaryReportBasis", required = true)
    protected String summaryReportBasis;

    /**
     * Gets the value of the agingReportBasis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgingReportBasis() {
        return agingReportBasis;
    }

    /**
     * Sets the value of the agingReportBasis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgingReportBasis(String value) {
        this.agingReportBasis = value;
    }

    /**
     * Gets the value of the summaryReportBasis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummaryReportBasis() {
        return summaryReportBasis;
    }

    /**
     * Sets the value of the summaryReportBasis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummaryReportBasis(String value) {
        this.summaryReportBasis = value;
    }

}
