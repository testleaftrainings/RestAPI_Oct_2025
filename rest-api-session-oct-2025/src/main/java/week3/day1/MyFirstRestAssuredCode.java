package week3.day1;

import io.restassured.RestAssured;

public class MyFirstRestAssuredCode {

	public static void main(String[] args) {
		RestAssured.given()
		           .baseUri("https://dev324941.service-now.com")
		           .basePath("api/now/table")
		           .auth()
		           .basic("admin", "e5!pRsPN%lH5")
		           .when()
		           .get("/incident")
		           .then()
		           .assertThat()
		           .statusCode(200);
	}

}