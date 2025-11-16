package week3.day1;

import static io.restassured.RestAssured.*;

import java.io.File;

//import io.restassured.http.ContentType;

public class CreateNewRecordBodyAsFile {
	
	static File request_body = new File("src/main/resources/servicenow/create_incident.json");

	public static void main(String[] args) {
		given()
		  .baseUri("https://dev324941.service-now.com")
		  .basePath("api/now/table")
		  .pathParam("tableName", "incident")
		  .auth()
		  .basic("admin", "e5!pRsPN%lH5")
		  .header("Content-Type", "application/json")
		  //.contentType(ContentType.JSON)
		  .log().all()
		  .when()
		  .body(request_body)
		  .post("/{tableName}")
		  .then()
		  .log().all()
		  .assertThat()
		  .statusCode(201);
	}

}