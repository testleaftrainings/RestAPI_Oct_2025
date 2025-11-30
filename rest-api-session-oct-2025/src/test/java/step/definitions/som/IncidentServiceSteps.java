package step.definitions.som;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.specification.RequestSpecification;
import servicenow.som.IncidentServiceV1;
import week3.day2.CreateIncidentPojo;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.List;
import java.util.Map;

public class IncidentServiceSteps {
	
	private RequestSpecification requestSpecification = given();	
	private CreateIncidentPojo incident = new CreateIncidentPojo();
	private IncidentServiceV1 incidentService = new IncidentServiceV1();
	private File file;
	public static String sysId;

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
		incidentService.getRecords(requestSpecification);
	}
	
	@Given("user should add header with key as {string} and value as {string}")
	public void user_should_add_header_with_key_as_and_value_as(String key, String value) {
	    requestSpecification.header(key, value);
	}
	
	@When("user hit post method of the {string} table service to create new record")
	public void user_hit_post_method_of_the_table_service_to_create_new_record(String tableName) {
		incidentService.createNewRecord(requestSpecification);
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
	   incidentService.createNewRecord(requestSpecification, incident);
	}
	
	@Then("user should validate the response component with the expected value")
	public void user_should_validate_the_response_component_with_the_expected_value(DataTable dataTable) {
		List<Map<String, String>> maps = dataTable.asMaps();
		for (Map<String, String> map : maps) {
			incidentService.validateResponse(Integer.parseInt(map.get("statusCode")), map.get("statusLine"),
					map.get("contentType"));
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
	
	@Then("user extract the sysid from the response body")
	public void user_extract_the_sysid_from_the_response_body() {
		sysId = incidentService.extractSysIdFromResponseBody();		
	}
	
	@Given("user fetch the sysId and set the path parameter which was already created from POST method")
	public void user_fetch_the_sys_id_and_set_the_path_parameter_which_was_already_created_from_post_method() {
		System.out.println(sysId);
		requestSpecification.pathParam("sys_Id", sysId);
	}
	
	@When("user hit get method of the {string} table service to get a give sysId records")
	public void user_hit_get_method_of_the_table_service_to_get_a_give_sys_id_records(String string) {
	    incidentService.getRecord(requestSpecification);
	}
	
	@When("user should use {string} file path to send request body")
	public void user_should_use_file_path_to_send_request_body(String pathName) {
	    file = new File(pathName);
	}
	
	@When("user hit post method of the {string} table service to create new via File")
	public void user_hit_post_method_of_the_table_service_to_create_new_via_file(String string) {
	   incidentService.createNewRecord(requestSpecification, file);
	}
	
}