package week3.day2;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RetriveAllSysIdsFromRecords {

	public static void main(String[] args) {
		List<String> sys_ids = RestAssured.given()
        .baseUri("https://dev324941.service-now.com")
        .basePath("api/now/table")
        .auth()
        .basic("admin", "e5!pRsPN%lH5")
        .when()
        .get("/incident")
        .then()
        .assertThat()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .extract()
        .jsonPath()
        .getList("result.sys_id");
		
		System.out.println(sys_ids);

	}

}
