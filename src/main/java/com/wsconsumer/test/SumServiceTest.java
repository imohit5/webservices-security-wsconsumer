package com.wsconsumer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

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
