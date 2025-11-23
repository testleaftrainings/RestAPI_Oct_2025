package week4.day1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateOAuthAccessToken {

	public static void main(String[] args) {
		Response response = RestAssured.given()
		           .baseUri("https://dev324941.service-now.com")
		           .contentType(ContentType.URLENC)
		           .when()
		           .formParam("grant_type", "password")
		           .formParam("client_id", "19fb80e453894a7380a4ad380b89bb8f")
		           .formParam("client_secret", "SjDT#pJ;k,")
		           .formParam("username", "admin")
		           .formParam("password", "e5!pRsPN%lH5")
		           .post("/oauth_token.do")
		           .then()
		           .statusCode(200)
		           .contentType(ContentType.JSON)
		           .extract()
		           .response();
		
		System.out.println(response.asPrettyString());
		System.out.println(response.jsonPath().getString("access_token"));
	}

}
