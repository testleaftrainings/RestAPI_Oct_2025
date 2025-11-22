package week3.day2;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;

import io.restassured.http.ContentType;

public class ExtractSysIdFromTheCreationResponseUsingJsonPath {

	static String request_body = """
			{
              "short_description": "RESTAPIJUL2025",
              "description": "Create new record using POST with body",
              "category": "software"
            }
			""";
	
	public static void main(String[] args) {
		String sys_id = given()
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
		  .statusCode(201)
		  .statusLine(Matchers.containsString("Created"))
		  .contentType(ContentType.JSON)
		  .extract()
		  .jsonPath()
		  .getString("$.result.sys_id");
		
		System.out.println(sys_id);
	}

}