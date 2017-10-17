package com.wsconsumer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.junit.Test;

import com.wsconsumer.handler.ConsumerPasswordCallback;
import com.wsproducer.service.Sum;
import com.wsproducer.service.SumRequest;
import com.wsproducer.service.SumResponse;
import com.wsproducer.service.SumService;

public class SumServiceTest {

	@Test
	public void calculateSumTest() {
		try {
			SumService sumService = new SumService(new URL("http://localhost:8080/webservices-security-wsproducer/services/sumService?wsdl"));
			Sum sumPort = sumService.getSumPort();
			
			Client client = ClientProxy.getClient(sumPort);
			Endpoint endpoint = client.getEndpoint();
			
			HashMap<String, Object> outDetails = new HashMap<>();
			outDetails.put(WSHandlerConstants.ACTION, "UsernameToken Timestamp Signature Encrypt");
			outDetails.put(WSHandlerConstants.USER, "admin");
			outDetails.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
			outDetails.put(WSHandlerConstants.PW_CALLBACK_CLASS, ConsumerPasswordCallback.class.getName());
			
			outDetails.put(WSHandlerConstants.ENCRYPTION_USER, "wsproducerkey");
			outDetails.put(WSHandlerConstants.ENC_PROP_FILE, "props/consumerKeystore.properties");
			
			outDetails.put(WSHandlerConstants.ENCRYPTION_PARTS, "{Element}{http://www.w3.org/2000/09/xmldsig#}Signature;{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");
			
			outDetails.put(WSHandlerConstants.SIGNATURE_USER, "wsconsumerkey");
			outDetails.put(WSHandlerConstants.SIG_PROP_FILE, "props/consumerKeystore.properties");
			
			outDetails.put(WSHandlerConstants.SIGNATURE_PARTS, "{Element}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp;{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body");
			
			outDetails.put("timeToLive", "30");
			
			WSS4JOutInterceptor wss4jOutInterceptor = new WSS4JOutInterceptor(outDetails);
			
			endpoint.getOutInterceptors().add(wss4jOutInterceptor);
			
			
			Map<String, Object> inDetails = new HashMap<>();
			inDetails.put(WSHandlerConstants.ACTION, "Signature Encrypt Timestamp");
			inDetails.put(WSHandlerConstants.PW_CALLBACK_CLASS, ConsumerPasswordCallback.class.getName());
			
			inDetails.put(WSHandlerConstants.DEC_PROP_FILE, "props/consumerKeystore.properties");
			
			inDetails.put(WSHandlerConstants.SIG_PROP_FILE, "props/consumerKeystore.properties");
			
			WSS4JInInterceptor wss4jInInterceptor = new WSS4JInInterceptor(inDetails);
			
			endpoint.getInInterceptors().add(wss4jInInterceptor);
			
			SumRequest sumRequest = new SumRequest();
			sumRequest.setNum1(25);
			sumRequest.setNum2(25);
			SumResponse sumResponse = sumPort.calculateSum(sumRequest);
			assertNotNull(sumResponse);
			assertEquals(50, sumResponse.getResult());
			
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}

}
