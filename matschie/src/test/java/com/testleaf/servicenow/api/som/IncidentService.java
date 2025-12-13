package com.testleaf.servicenow.api.som;

import static org.hamcrest.Matchers.*;

import com.testleaf.matchie.rest.assured.api.client.RestAssuredApiClient;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class IncidentService {
	
	private static final String TABLENAME = "incident";
	private RestAssuredApiClient apiClient = new RestAssuredApiClient();
	private Response response;
	
	public void getRecords(RequestSpecBuilder requestSpecBuilder) {
		response = apiClient.get(requestSpecBuilder, TABLENAME);
	}
	
	public void getRecordsBasedOnCategory(RequestSpecBuilder requestSpecBuilder, String categoryName) {
		response = apiClient.get(requestSpecBuilder
				               .addQueryParam("category", categoryName), TABLENAME);
	}
	
	public void validateStatusCode(String statusCode) {
		response.then().assertThat().statusCode(Integer.parseInt(statusCode));
	}
	
	public void validateStatusLine(String statusLine) {
		response.then().assertThat().statusLine(containsString(statusLine));
	}
	
	public void validateResponseFormat(String responseFormat) {
		if (responseFormat.equalsIgnoreCase("JSON")) {
			response.then().assertThat().contentType(ContentType.JSON);
		} else if (responseFormat.equalsIgnoreCase("XML")) {
			response.then().assertThat().contentType(ContentType.XML);
		} else {
			throw new RuntimeException("Currently matschie framework support JSON or XML.");
		}
	}

}