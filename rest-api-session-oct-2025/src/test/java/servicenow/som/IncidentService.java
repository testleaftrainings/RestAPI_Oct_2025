package servicenow.som;

import static org.hamcrest.Matchers.containsString;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import week3.day2.CreateIncidentPojo;

public class IncidentService {
	
	private static final String tableName = "/incident";
	private Response response;	
	
	public void getRecords(RequestSpecification requestSpecification) {
		response = requestSpecification.log().all().get(tableName);
	}
	
	public void getRecord(RequestSpecification requestSpecification, String sysId) {
		response = requestSpecification.log().all().get(tableName+"/"+sysId);
	}
	
	public void getRecord(RequestSpecification requestSpecification) {
		response = requestSpecification.log().all().get(tableName+"/{sys_Id}");
	}
	
	public void createNewRecord(RequestSpecification requestSpecification) {
		response = requestSpecification.log().all().post(tableName);
	}
	
	public void createNewRecord(RequestSpecification requestSpecification, CreateIncidentPojo incident) {
		response = requestSpecification.log().all().body(incident).post(tableName);
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