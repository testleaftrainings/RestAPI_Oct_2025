package com.testleaf.matchie.step.definitions;

import com.testleaf.servicenow.api.som.IncidentService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

public class IncidentServiceSteps {	
	
	RequestSpecBuilder preConditions = new RequestSpecBuilder();
	IncidentService incidentService = new IncidentService();

	@Given("user should set the base uri {string} of the incident table service")
	public void user_should_set_the_base_uri_of_the_incident_table_service(String baseUri) {
		preConditions.setBaseUri(baseUri);
	}

	@Given("user should set the base path {string} of the incident table service")
	public void user_should_set_the_base_path_of_the_incident_table_service(String basePath) {
		preConditions.setBasePath(basePath);
	}

	@Given("user should set basic auth authencation username {string} and password {string} for the incident table service")
	public void user_should_set_basic_auth_authencation_username_and_password_for_the_incident_table_service(
			String username, String password) {
		preConditions.setAuth(RestAssured.basic(username, password));
	}

	@When("user hit get method of the {string} table service to get all records")
	public void user_hit_get_method_of_the_table_service_to_get_all_records(String endpoint) {
		incidentService.getRecords(preConditions);
	}

	@Then("user should see the status code as {string} in the repose")
	public void user_should_see_the_status_code_as_in_the_repose(String statusCode) {
		incidentService.validateStatusCode(statusCode);
	}

	@Then("user should see the status line as {string} in the repose")
	public void user_should_see_the_status_line_as_in_the_repose(String statusLine) {
		incidentService.validateStatusLine(statusLine);
	}

	@Then("user should get the response in the {string} format")
	public void user_should_get_the_response_in_the_format(String responeFormat) {
	    incidentService.validateResponseFormat(responeFormat);
	}

}