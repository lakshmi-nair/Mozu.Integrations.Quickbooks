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
 * <p>Java class for QBXMLType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QBXMLType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QBXMLMsgsRq" type="{}QBXMLMsgsRqType"/>
 *         &lt;element name="QBXMLMsgsRs" type="{}QBXMLMsgsRsType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QBXMLType", propOrder = {
    "qbxmlMsgsRq",
    "qbxmlMsgsRs"
})
public class QBXMLType {

    @XmlElement(name = "QBXMLMsgsRq", required = true)
    protected QBXMLMsgsRqType qbxmlMsgsRq;
    @XmlElement(name = "QBXMLMsgsRs", required = true)
    protected QBXMLMsgsRsType qbxmlMsgsRs;

    /**
     * Gets the value of the qbxmlMsgsRq property.
     * 
     * @return
     *     possible object is
     *     {@link QBXMLMsgsRqType }
     *     
     */
    public QBXMLMsgsRqType getQBXMLMsgsRq() {
        return qbxmlMsgsRq;
    }

    /**
     * Sets the value of the qbxmlMsgsRq property.
     * 
     * @param value
     *     allowed object is
     *     {@link QBXMLMsgsRqType }
     *     
     */
    public void setQBXMLMsgsRq(QBXMLMsgsRqType value) {
        this.qbxmlMsgsRq = value;
    }

    /**
     * Gets the value of the qbxmlMsgsRs property.
     * 
     * @return
     *     possible object is
     *     {@link QBXMLMsgsRsType }
     *     
     */
    public QBXMLMsgsRsType getQBXMLMsgsRs() {
        return qbxmlMsgsRs;
    }

    /**
     * Sets the value of the qbxmlMsgsRs property.
     * 
     * @param value
     *     allowed object is
     *     {@link QBXMLMsgsRsType }
     *     
     */
    public void setQBXMLMsgsRs(QBXMLMsgsRsType value) {
        this.qbxmlMsgsRs = value;
    }

}
