package week3.day1;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;

public class CreateNewRecordBodyAsString {
	
	static String request_body = """
			{
              "short_description": "RESTAPIJUL2025",
              "description": "Create new record using POST with body",
              "category": "software"
            }
			""";
	
	public static void main(String[] args) {
		given()
		  .baseUri("https://dev324941.service-now.com")
		  .basePath("api/now/table")
		  .pathParam("tableName", "incident")
		  .auth()
		  .basic("admin", "e5!pRsPN%lH5")
		  //.header("Content-Type", "application/json")
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