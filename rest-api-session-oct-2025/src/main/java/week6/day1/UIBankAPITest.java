package week6.day1;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UIBankAPITest {
	
	String requestBody = """
			{
              "username": "FebApiuser",
              "password": "Eagle@123"
            }
			""";
	String sampleResponse = """
			{
              "id": "L3292aisknVjuhWkWbIospFk6mTHOLOkPiuvD0A8GDKTO7EoEbfhfswUL5YSy7In",
              "ttl": 1209600,
              "created": "2025-12-07T10:55:04.105Z",
              "userId": "64290731ba9f8a0047adacfc"
            }
			""";
	String token;
	String userId;
	Response response;
	WireMockServer mockServer;
	
	@BeforeSuite
	public void beforeSuite() {
		mockServer = new WireMockServer();
		mockServer.start();
	}
	
	@BeforeClass
	public void beforeClass() {
		MappingBuilder createTokenReq = WireMock.post("/api/users/login")
		        .withHeader("Content-Type", WireMock.equalTo("application/json"))
		        .withRequestBody(WireMock.equalToJson(requestBody));
		
		ResponseDefinitionBuilder createTokeRes = WireMock.aResponse()
		        .withStatus(200)
		        .withStatusMessage("OK")
		        .withHeader("Content-Type", "application/json")
		        .withBody(sampleResponse);
		
		mockServer.stubFor(createTokenReq.willReturn(createTokeRes));
		
		MappingBuilder userAccountsReq = WireMock.get(WireMock.urlPathEqualTo("/api/accounts"))	        
		        .withHeader("Authorization", WireMock.equalTo("Bearer L3292aisknVjuhWkWbIospFk6mTHOLOkPiuvD0A8GDKTO7EoEbfhfswUL5YSy7In"))
		        .withQueryParam("filter[where][userId]", WireMock.equalTo("64290731ba9f8a0047adacfc"));
		
		ResponseDefinitionBuilder userAccountsRes = WireMock.aResponse()
		        .withStatus(200)
		        .withStatusMessage("OK")
		        .withHeader("Content-Type", "application/json")
		        .withBodyFile("accounts.json");
		
		mockServer.stubFor(userAccountsReq.willReturn(userAccountsRes));
	}
	
	@Test
	public void createToken() {
		response = RestAssured.given()
		           .baseUri("http://localhost:8080")
		           .basePath("/api")
		           .contentType(ContentType.JSON)
		           .log().all()
		           .when()
		           .body(requestBody)
		           .post("/users/login")
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(200)
		           .statusLine(Matchers.containsString("OK"))
		           .extract()
		           .response();
		
		token = response.jsonPath().getString("id");
		userId = response.jsonPath().getString("userId");

	}
	
	@Test
	public void getAccountsByUserId() {
		RestAssured.given()
        .baseUri("http://localhost:8080")
        .basePath("/api")
        .header("Authorization", "Bearer "+token)
        .queryParam("filter[where][userId]", userId)
        .urlEncodingEnabled(false)
        .log().all()
        .when()
        .get("/accounts")
        .then()
        .log().all()
        .assertThat()
        .statusCode(200)
        .statusLine(Matchers.containsString("OK"));
	}

}