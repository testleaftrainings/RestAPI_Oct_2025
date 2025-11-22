package week3.day2;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

public class CreateNewRecordBodyAsObject {	 

	public static void main(String[] args) {
		
		// Serialization => Java Object -> JSON Object	
		CreateIncidentPojo request_body = new CreateIncidentPojo();
		request_body.setShort_description("RESTAPIOCT2025");		
		
		given()
		  .baseUri("https://dev324941.service-now.com")
		  .basePath("api/now/table")
		  .pathParam("tableName", "incident")
		  .auth()
		  .basic("admin", "e5!pRsPN%lH5")		  
		  .contentType(ContentType.JSON)
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