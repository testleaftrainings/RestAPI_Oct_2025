package week6.day1;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class MockingPostMethod {
	
	public static void main(String[] args) {
		
		String userInput = """
				{
				  "name": "Karthieyan",
				  "username": "karthike89"
				}				
				""";
		String ouput = """
				{
				  "message": "user is successfully create"
				}
				""";
		
		MappingBuilder requestMocking = WireMock.post("/create/user")
		        .withHeader("Content-Type", WireMock.equalTo("application/json"))
		        .withRequestBody(WireMock.equalToJson(userInput));
		
		ResponseDefinitionBuilder responseMocking = WireMock.aResponse()
		        .withStatus(201)
		        .withStatusMessage("Created")
		        .withHeader("Content-Type", "application/json")
		        .withBody(ouput);
		
		WireMock.stubFor(requestMocking.willReturn(responseMocking));
		
	}

}