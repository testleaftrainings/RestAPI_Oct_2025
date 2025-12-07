package week6.day1;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class MockingGetMethod {

	public static void main(String[] args) {
		
		String user = """
				{
				  "name": "Karthi",
				  "email": "test@example.com"				  
				}
				""";
		
		// Mock the Request
		MappingBuilder requestMocking = WireMock.get("/userinfo");
				
		// Mock the Response
		ResponseDefinitionBuilder responseMocking = WireMock.aResponse()
				        .withStatus(200)
				        .withStatusMessage("OK")
				        .withHeader("Content-Type", "application/json")				        
				        .withBody(user);
				
		WireMock.stubFor(requestMocking.willReturn(responseMocking));

	}

}
