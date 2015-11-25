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
 */
@XmlRegistry
public class ObjectFactory {

    public static final String HTTP_STEEKPROEF_RDW = "http://steekproef.rdw/";
    private static final QName _Steekproef_QNAME = new QName(HTTP_STEEKPROEF_RDW, "steekproef");
    private static final QName _SteekproefResponse_QNAME = new QName(HTTP_STEEKPROEF_RDW, "steekproefResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances .
     * of schema derived classes for package: javaminor.al.service.client.
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Steekproef }.
     *
     * @return een steekproef
     */
    public Steekproef createSteekproef() {
        return new Steekproef();
    }

    /**
     * Create an instance of {@link SteekproefResponse }.
     *
     * @return een SteekproefResponse
     */
    public SteekproefResponse createSteekproefResponse() {
        return new SteekproefResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Steekproef }{@code >}}.
     *
     * @param value value
     * @return iets
     */
    @XmlElementDecl(namespace = HTTP_STEEKPROEF_RDW, name = "steekproef")
    public JAXBElement<Steekproef> createSteekproef(Steekproef value) {
        return new JAXBElement<Steekproef>(_Steekproef_QNAME, Steekproef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SteekproefResponse }{@code >}}.
     *
     * @param value value
     * @return ook iets
     */
    @XmlElementDecl(namespace = HTTP_STEEKPROEF_RDW, name = "steekproefResponse")
    public JAXBElement<SteekproefResponse> createSteekproefResponse(SteekproefResponse value) {
        return new JAXBElement<SteekproefResponse>(_SteekproefResponse_QNAME, SteekproefResponse.class, null, value);
    }

}
