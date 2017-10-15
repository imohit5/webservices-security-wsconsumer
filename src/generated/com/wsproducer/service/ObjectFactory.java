
package com.wsproducer.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.wsproducer.service package. 
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

    private final static QName _CalculateSum_QNAME = new QName("http://service.wsproducer.com/", "CalculateSum");
    private final static QName _CalculateSumResponse_QNAME = new QName("http://service.wsproducer.com/", "CalculateSumResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.wsproducer.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CalculateSum }
     * 
     */
    public CalculateSum createCalculateSum() {
        return new CalculateSum();
    }

    /**
     * Create an instance of {@link CalculateSumResponse }
     * 
     */
    public CalculateSumResponse createCalculateSumResponse() {
        return new CalculateSumResponse();
    }

    /**
     * Create an instance of {@link SumRequest }
     * 
     */
    public SumRequest createSumRequest() {
        return new SumRequest();
    }

    /**
     * Create an instance of {@link SumResponse }
     * 
     */
    public SumResponse createSumResponse() {
        return new SumResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateSum }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.wsproducer.com/", name = "CalculateSum")
    public JAXBElement<CalculateSum> createCalculateSum(CalculateSum value) {
        return new JAXBElement<CalculateSum>(_CalculateSum_QNAME, CalculateSum.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateSumResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.wsproducer.com/", name = "CalculateSumResponse")
    public JAXBElement<CalculateSumResponse> createCalculateSumResponse(CalculateSumResponse value) {
        return new JAXBElement<CalculateSumResponse>(_CalculateSumResponse_QNAME, CalculateSumResponse.class, null, value);
    }

}
