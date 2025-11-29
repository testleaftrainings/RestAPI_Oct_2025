package step.defintions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import week3.day2.CreateIncidentPojo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

public class IncidentServiceSteps {
	
	private RequestSpecification requestSpecification = given();
	private Response response;
	private CreateIncidentPojo incident = new CreateIncidentPojo();

	@Given("user should set the base uri {string} of the incident table service")
	public void user_should_set_the_base_uri_of_the_incident_table_service(String baseUri) {
		requestSpecification.baseUri(baseUri);
	}

	@Given("user should set the base path {string} of the incident table service")
	public void user_should_set_the_base_path_of_the_incident_table_service(String basePath) {
		requestSpecification.basePath(basePath);
	}

	@Given("user should set basic auth authencation username {string} and password {string} for the incident table service")
	public void user_should_set_basi_auth_authencation_username_and_password_for_the_incident_table_service(
			String username, String password) {
		requestSpecification.auth().basic(username, password);
	}

	@When("user hit get method of the {string} table service to get all records")
	public void user_hit_get_method_of_the_table_service_to_get_all_records(String tableName) {
		response = requestSpecification.log().all().get(tableName);
	}

	@Then("user should see the status code as {string} in the repose")
	public void user_should_see_the_status_code_as_in_the_repose(String statusCode) {
		System.out.println(response.asPrettyString());
		response.then().statusCode(Integer.parseInt(statusCode));
	}

	@Then("user should see the status line as {string} in the repose")
	public void user_should_see_the_status_line_as_in_the_repose(String statusLine) {
		response.then().statusLine(containsString(statusLine));
	}

	@Then("user should get the response in the JSON format")
	public void user_should_get_the_response_in_the_json_format() {
		response.then().contentType(ContentType.JSON);
	}
	
	@Then("user should get the response in the XML format")
	public void user_should_get_the_response_in_the_xml_format() {
	    response.then().contentType(ContentType.XML);
	}

	
	@Given("user should add header with key as {string} and value as {string}")
	public void user_should_add_header_with_key_as_and_value_as(String key, String value) {
	    requestSpecification.header(key, value);
	}
	
	@When("user hit post method of the {string} table service to create new record")
	public void user_hit_post_method_of_the_table_service_to_create_new_record(String tableName) {
		response = requestSpecification.log().all().post(tableName);
	}
	
	@When("user should add shortdescription as {string} in the request body")
	public void user_should_add_shortdescription_as_in_the_request_body(String shortDescription) {
	    incident.setShort_description(shortDescription);
	}
	
	@When("user should add category as {string} in the request body")
	public void user_should_add_category_as_in_the_request_body(String category) {
	    incident.setCategory(category);
	}
	
	@When("user hit post method of the {string} table service with JSON payload to create new record")
	public void user_hit_post_method_of_the_table_service_with_json_payload_to_create_new_record(String tableName) {
	   response = requestSpecification.body(incident).post(tableName);
	}
	
	@Then("user should validate the response component with the expected value")
	public void user_should_validate_the_response_component_with_the_expected_value(DataTable dataTable) {
		List<Map<String, String>> maps = dataTable.asMaps();
		for (Map<String, String> map : maps) {
			response.then().assertThat().statusCode(Integer.parseInt(map.get("statusCode")));
			response.then().assertThat().statusLine(containsString(map.get("statusLine")));
			if(map.get("contentType").equalsIgnoreCase("JSON")) {
				response.then().assertThat().contentType(ContentType.JSON);
			} else {
				response.then().assertThat().contentType(ContentType.XML);
			}
		}
	}
	
	@When("user should add the relevant value to create record as request paylod")
	public void user_should_add_the_relevant_value_to_create_record_as_request_paylod(DataTable dataTable) {
	    	List<List<String>> lists = dataTable.asLists();
	    for (List<String> list : lists) {
	    	    incident.setShort_description(list.get(0));			
			incident.setCategory(list.get(1));
		}	    
	}
	
}