package javaminor.al.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for steekproefResponse complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="steekproefResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "steekproefResponse", propOrder = {
        "aReturn"
})
public class SteekproefResponse {

    @XmlElement(name = "return")
    protected Boolean aReturn;

    /**
     * Gets the value of the return property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isReturn() {
        return aReturn;
    }

    /**
     * Sets the value of the return property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setReturn(Boolean value) {
        this.aReturn = value;
    }

}
