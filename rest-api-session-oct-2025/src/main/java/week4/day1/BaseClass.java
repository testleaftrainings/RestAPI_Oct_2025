package week4.day1;

import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.*;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;
import week3.day2.servicenow.pojos.CreateIncident;
import week3.day2.servicenow.pojos.UpdateIncident;


public class BaseClass {
	
	protected RequestSpecification requestSpecification;
	protected CreateIncident createIncident = new CreateIncident();
	protected UpdateIncident updateIncident = new UpdateIncident();
	protected String sysId;
	
	@BeforeClass
	public void beforeClass() {
		requestSpecification = given()
				                .filter(new AllureRestAssured())
				                .baseUri("https://dev324941.service-now.com")
				                .basePath("/api/now/table")
				                .auth().basic("admin", "e5!pRsPN%lH5")
				                .pathParam("tableName", "incident");
	}

}