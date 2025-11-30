package base;

import java.io.File;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import week3.day2.CreateIncidentPojo;

public class RestAssuredBaseImplTest extends RestAssuredBaseImpl {
	
	private RequestSpecification requestSpecification = RestAssured.given();
	private Response response;
	
	@BeforeClass
	public void setUp() {
		requestSpecification.baseUri("https://dev324941.service-now.com");
		requestSpecification.basePath("/api/now/table");
		requestSpecification.auth().basic("admin", "e5!pRsPN%lH5");
		requestSpecification.pathParam("tableName", "incident");
		requestSpecification.contentType(ContentType.JSON);
	}
	
	@Test
	public void sendRequestBodyAsString() {
		
		String requstBody = """
				{
	              "short_description": "RESTAPIJUL2025",
	              "description": "Create new record using POST with body as file",
	              "category": "software"
                }
				""";
		 response = post(requestSpecification, "/{tableName}", requstBody);
		 response.prettyPrint();
	}
	
	@Test
	public void sendRequestBodyAsFile() {
		File requstBody = new File("src/main/resources/servicenow/create_incident.json");
		response = post(requestSpecification, "/{tableName}", requstBody);
		response.prettyPrint();
	}
	
	@Test
	public void sendRequestBodyAsObject() {
		CreateIncidentPojo requstBody = new CreateIncidentPojo();
		requstBody.setShort_description("RESTAPISESSIONOCT2025");
		response = post(requestSpecification, "/{tableName}", requstBody);
		response.prettyPrint();
	}


}