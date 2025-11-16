package week3.day1;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;

public class RetrieveAllRecordsFromIncidentInXml {

	public static void main(String[] args) {
		RestAssured.given()	
		           .baseUri("https://dev324941.service-now.com")
		           .basePath("/api/now/table")
		           .header("Accept","application/xml")
		           .auth()
		           .basic("admin", "e5!pRsPN%lH5")
		           .log().all() // Print Request Information in the Console
		           .when()
		           .get("/incident")
		           .then()
		           .log().all() // Print Response Information in the Console
		           .assertThat()
		           .statusCode(200)
		           .statusLine(Matchers.containsString("OK"))
		           .header("Content-Type", Matchers.containsString("application/xml"));
	}

}