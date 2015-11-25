package javaminor.al.service.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.*;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebServiceClient(name = "RDWSteekproefWebService",
        targetNamespace = "http://steekproef.rdw/",
        wsdlLocation = "file:///home/alex/documents/Opdrachten/RDWSteekproefWebService2.wsdl")
public class RDWSteekproefWebServiceService
        extends Service {

    private static final URL RDWSTEEKPROEFWEBSERVICE_WSDL_LOCATION;
    private static final WebServiceException RDWSTEEKPROEFWEBSERVICE_EXCEPTION;
    public static final String HTTP_STEEKPROEF_RDW = "http://steekproef.rdw/";
    public static final String RDW_STEEKPROEF_WEB_SERVICE = "RDWSteekproefWebService";
    private static final QName RDWSTEEKPROEFWEBSERVICE_QNAME = new QName(HTTP_STEEKPROEF_RDW,
            RDW_STEEKPROEF_WEB_SERVICE);
    public static final String RDW_STEEKPROEF_WEB_SERVICE_PORT = "RDWSteekproefWebServicePort";

    /**
     * Document this public constructor.
     */
    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:///home/alex/documents/Opdrachten/RDWSteekproefWebService2.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        RDWSTEEKPROEFWEBSERVICE_WSDL_LOCATION = url;
        RDWSTEEKPROEFWEBSERVICE_EXCEPTION = e;
    }

    /**
     * A.
     */
    public RDWSteekproefWebServiceService() {
        super(getWsdlLocation(), RDWSTEEKPROEFWEBSERVICE_QNAME);
    }

    /**
     * A.
     *
     * @param features b
     */
    public RDWSteekproefWebServiceService(WebServiceFeature... features) {
        super(getWsdlLocation(), RDWSTEEKPROEFWEBSERVICE_QNAME, features);
    }

    /**
     * A.
     *
     * @param wsdlLocation b
     */
    public RDWSteekproefWebServiceService(URL wsdlLocation) {
        super(wsdlLocation, RDWSTEEKPROEFWEBSERVICE_QNAME);
    }

    /**
     * A.
     *
     * @param wsdlLocation a
     * @param features     b
     */
    public RDWSteekproefWebServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, RDWSTEEKPROEFWEBSERVICE_QNAME, features);
    }

    /**
     * Document this public constructor.
     *
     * @param serviceName  a
     * @param wsdlLocation b
     */
    public RDWSteekproefWebServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     * A.
     *
     * @param wsdlLocation a
     * @param serviceName  b
     * @param features     c
     */
    public RDWSteekproefWebServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * bla.
     *
     * @return returns RDWSteekproefWebService
     */
    @WebEndpoint(name = "RDWSteekproefWebServicePort")
    public RDWSteekproefWebService getRDWSteekproefWebServicePort() {
        return super.getPort(new QName(HTTP_STEEKPROEF_RDW, RDW_STEEKPROEF_WEB_SERVICE_PORT),
                RDWSteekproefWebService.class);
    }

    /**
     * bla.
     *
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.
     *                 Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns RDWSteekproefWebService
     */
    @WebEndpoint(name = "RDWSteekproefWebServicePort")
    public RDWSteekproefWebService getRDWSteekproefWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName(HTTP_STEEKPROEF_RDW, RDW_STEEKPROEF_WEB_SERVICE_PORT),
                RDWSteekproefWebService.class, features);
    }

    private static URL getWsdlLocation() {
        if (RDWSTEEKPROEFWEBSERVICE_EXCEPTION != null) {
            throw RDWSTEEKPROEFWEBSERVICE_EXCEPTION;
        }
        return RDWSTEEKPROEFWEBSERVICE_WSDL_LOCATION;
    }

}
