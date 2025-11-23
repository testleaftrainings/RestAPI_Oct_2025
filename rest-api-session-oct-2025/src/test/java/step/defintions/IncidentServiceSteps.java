package step.defintions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class IncidentServiceSteps {
	
	private RequestSpecification requestSpecification = given();
	private Response response;

	@Given("user should set the base uri {string} of the incident table service")
	public void user_should_set_the_base_uri_of_the_incident_table_service(String baseUri) {
		requestSpecification.baseUri(baseUri);
	}

	@Given("user should set the base path {string} of the incident table service")
	public void user_should_set_the_base_path_of_the_incident_table_service(String basePath) {
		requestSpecification.basePath(basePath);
	}

	@Given("user should set basi auth authencation username {string} and password {string} for the incident table service")
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

}