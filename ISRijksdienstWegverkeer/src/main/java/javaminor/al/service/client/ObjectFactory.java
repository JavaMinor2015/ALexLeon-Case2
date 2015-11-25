
package javaminor.al.service.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the javaminor.al.service.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Steekproef_QNAME = new QName("http://steekproef.rdw/", "steekproef");
    private final static QName _SteekproefResponse_QNAME = new QName("http://steekproef.rdw/", "steekproefResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: javaminor.al.service.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Steekproef }
     * 
     */
    public Steekproef createSteekproef() {
        return new Steekproef();
    }

    /**
     * Create an instance of {@link SteekproefResponse }
     * 
     */
    public SteekproefResponse createSteekproefResponse() {
        return new SteekproefResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Steekproef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://steekproef.rdw/", name = "steekproef")
    public JAXBElement<Steekproef> createSteekproef(Steekproef value) {
        return new JAXBElement<Steekproef>(_Steekproef_QNAME, Steekproef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SteekproefResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://steekproef.rdw/", name = "steekproefResponse")
    public JAXBElement<SteekproefResponse> createSteekproefResponse(SteekproefResponse value) {
        return new JAXBElement<SteekproefResponse>(_SteekproefResponse_QNAME, SteekproefResponse.class, null, value);
    }

}
