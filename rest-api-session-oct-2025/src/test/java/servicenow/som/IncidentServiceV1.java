package servicenow.som;

import static org.hamcrest.Matchers.containsString;

import java.io.File;

import base.RestAssuredBaseImpl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import week3.day2.CreateIncidentPojo;

public class IncidentServiceV1 extends RestAssuredBaseImpl {
	
	private static final String tableName = "/incident";
	private Response response;	
	
	public void getRecords(RequestSpecification requestSpecification) {
		response = get(requestSpecification, tableName);
	}
	
	public void getRecord(RequestSpecification requestSpecification, String sysId) {
		response = get(requestSpecification, tableName+"/"+sysId);
	}
	
	public void getRecord(RequestSpecification requestSpecification) {
		response = get(requestSpecification, tableName+"/{sys_Id}");
	}
	
	public void createNewRecord(RequestSpecification requestSpecification) {
		response = post(requestSpecification, tableName, null);
	}
	
	public void createNewRecord(RequestSpecification requestSpecification, CreateIncidentPojo incident) {
		response = post(requestSpecification, tableName, incident);
	}
	
	public void createNewRecord(RequestSpecification requestSpecification, File incident) {
		response = post(requestSpecification, tableName, incident);
	}
	
	public void validateResponse(int statusCode, String statusLine, String responseFormat) {
		response.then().assertThat().statusCode(statusCode);
		response.then().assertThat().statusLine(containsString(statusLine));
		if(responseFormat.equalsIgnoreCase("JSON")) {
			response.then().assertThat().contentType(ContentType.JSON);
		} else {
			response.then().assertThat().contentType(ContentType.XML);
		}
	}
	
	public String extractSysIdFromResponseBody() {
		return response.then().extract().jsonPath().getString("result.sys_id");
	}

}