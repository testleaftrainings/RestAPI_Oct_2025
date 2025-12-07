package week6.day1;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class MyFirstWiremockSCript {

	public static void main(String[] args) {
		
		// Mock the Request
		MappingBuilder requestMocking = WireMock.get("/welcome");
		
		// Mock the Response
		ResponseDefinitionBuilder responseMocking = WireMock.aResponse()
		        .withStatus(200)
		        .withStatusMessage("OK")
		        .withBody("Hi Team! Welcome to Wiremock session");
		
		WireMock.stubFor(requestMocking.willReturn(responseMocking));
		

	}

}