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
 *         &lt;element ref="{}SubscriberID"/>
 *         &lt;element ref="{}COMCallbackInfo"/>
 *         &lt;element ref="{}MenuExtensionSubscription"/>
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
    "subscriberID",
    "comCallbackInfo",
    "menuExtensionSubscription"
})
@XmlRootElement(name = "UIExtensionSubscriptionAdd")
public class UIExtensionSubscriptionAdd {

    @XmlElement(name = "SubscriberID", required = true)
    protected String subscriberID;
    @XmlElement(name = "COMCallbackInfo", required = true)
    protected COMCallbackInfo comCallbackInfo;
    @XmlElement(name = "MenuExtensionSubscription", required = true)
    protected MenuExtensionSubscription menuExtensionSubscription;

    /**
     * Gets the value of the subscriberID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberID() {
        return subscriberID;
    }

    /**
     * Sets the value of the subscriberID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberID(String value) {
        this.subscriberID = value;
    }

    /**
     * Gets the value of the comCallbackInfo property.
     * 
     * @return
     *     possible object is
     *     {@link COMCallbackInfo }
     *     
     */
    public COMCallbackInfo getCOMCallbackInfo() {
        return comCallbackInfo;
    }

    /**
     * Sets the value of the comCallbackInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link COMCallbackInfo }
     *     
     */
    public void setCOMCallbackInfo(COMCallbackInfo value) {
        this.comCallbackInfo = value;
    }

    /**
     * Gets the value of the menuExtensionSubscription property.
     * 
     * @return
     *     possible object is
     *     {@link MenuExtensionSubscription }
     *     
     */
    public MenuExtensionSubscription getMenuExtensionSubscription() {
        return menuExtensionSubscription;
    }

    /**
     * Sets the value of the menuExtensionSubscription property.
     * 
     * @param value
     *     allowed object is
     *     {@link MenuExtensionSubscription }
     *     
     */
    public void setMenuExtensionSubscription(MenuExtensionSubscription value) {
        this.menuExtensionSubscription = value;
    }

}
