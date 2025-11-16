package week3.day1;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.hamcrest.Matchers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateChangeRequestAssessment {
	
	static String request_body_string = """
			{
              "short_description": "RESTAPIJUL2025",
              "description": "Create new record using POST with body as string",
              "category": "software"
            }
			""";
	
	static File request_body_file = new File("src/main/resources/servicenow/create_incident.json");

	public static void main(String[] args) {
		
		baseURI = "https://dev324941.service-now.com";
		basePath = "api/now/table";
		authentication = basic("admin", "e5!pRsPN%lH5");
		
		createChangeRequest(request_body_string);
		createChangeRequest(request_body_file);
		
	}
	
	public static void createChangeRequest(String requestBody) {
		Response response = given()		
		.contentType(ContentType.JSON)
		.when()
		.body(requestBody)
		.post("/change_request");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
		response.prettyPrint();
		
		response.then()
		.assertThat()
		.statusCode(201)
		.statusLine(Matchers.containsString("Created"))
		.body("result.category", Matchers.equalToIgnoringCase("software"));
		
	}
	
	public static void createChangeRequest(File requestBody) {
		Response response = given()				
		.contentType(ContentType.JSON)
		.when()
		.body(requestBody)
		.post("/change_request");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
		response.prettyPrint();
		
		response.then()
		.assertThat()
		.statusCode(201)
		.statusLine(Matchers.containsString("Created"));
		
	}
	
	public static void createChangeRequest(Object requestBody) {
		Response response = null;
		if(requestBody instanceof String) {
			response = given()				
					.contentType(ContentType.JSON)
					.when()
					.body((String) requestBody)
					.post("/change_request");
		} else if(requestBody instanceof File) {
			response = given()				
					.contentType(ContentType.JSON)
					.when()
					.body((File) requestBody)
					.post("/change_request");
		}
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
		response.prettyPrint();
		
		response.then()
		.assertThat()
		.statusCode(201)
		.statusLine(Matchers.containsString("Created"));
	}

}
