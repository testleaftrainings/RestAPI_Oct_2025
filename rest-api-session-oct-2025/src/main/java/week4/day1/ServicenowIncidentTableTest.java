package week4.day1;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import testng.listeners.RetryFailedTest;

public class ServicenowIncidentTableTest extends BaseClass {	
	
	@Test(priority = 1, retryAnalyzer = RetryFailedTest.class)
	public void createRecord() {
		
		createIncident.setShortDescription("RESTAPISESSIONOCT2025");
		
		sysId = requestSpecification		  		  
		  .contentType(ContentType.JSON)
		  .log().all()
		  .when()		  
		  .body(createIncident)
		  .post("/{tableName}")
		  .then()
		  .log().all()
		  .assertThat()
		  .statusCode(201)
		  .statusLine(Matchers.containsString("Created"))
		  .contentType(ContentType.JSON)
		  .body("result.short_description", Matchers.equalTo(createIncident.getShortDescription()))
		  .extract()
		  .jsonPath()
		  .getString("result.sys_id");
	}
	
	@Test(priority = 2, retryAnalyzer = RetryFailedTest.class)
	public void getRecord() {
		requestSpecification		  
		  .pathParam("sys_id", sysId)		  
		  .log().all()
		  .when()	
		  .get("/{tableName}/{sys_id}")
		  .then()
		  .log().all()
		  .assertThat()
		  .statusCode(200)
		  .statusLine(Matchers.containsString("OK"))
		  .contentType(ContentType.JSON)
		  .body("result.sys_id", Matchers.equalTo(sysId));
		  
	}
	
	@Test(priority = 3, retryAnalyzer = RetryFailedTest.class)
	public void updateRecord() {
		
		updateIncident.setCategory("hardware");
		
		requestSpecification		  
		  .pathParam("sys_id", sysId)		 		  
		  .contentType(ContentType.JSON)
		  .log().all()
		  .when()		  
		  .body(updateIncident)
		  .put("/{tableName}/{sys_id}")
		  .then()
		  .log().all()
		  .assertThat()
		  .statusCode(200)
		  .statusLine(Matchers.containsString("OK"))
		  .contentType(ContentType.JSON)
		  .body("result.sys_id", Matchers.equalTo(sysId))
		  .body("result.category", Matchers.equalTo(updateIncident.getCategory()));
		
	}
	
	@Test(priority = 4, retryAnalyzer = RetryFailedTest.class)
	public void deleteRecord() {
		requestSpecification		  		  
		  .pathParam("sys_id", sysId)
		  .log().all()
		  .when()	
		  .delete("/{tableName}/{sys_id}")
		  .then()
		  .log().all()
		  .assertThat()
		  .statusCode(204)
		  .statusLine(Matchers.containsString("No Content"));
	}
	
	@Test(priority = 5)
	public void validateIsRecordDeleted() {
		requestSpecification		  		  
		  .pathParam("sys_id", sysId)		 
		  .log().all()
		  .when()	
		  .get("/{tableName}/{sys_id}")
		  .then()
		  .log().all()
		  .assertThat()
		  .statusCode(404)
		  .statusLine(Matchers.containsString("Not Found"));
	}

}