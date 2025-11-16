package week3.day1;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;

public class RetriveAllRecordsForTheHardwareCategory {

	public static void main(String[] args) {
		given()
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("api/now/table")
		 .pathParam("tableName", "incident")
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .queryParam("sysparm_query", "category=hardware")
		.when()
		  .get("/{tableName}")
		.then()
		  .log().all()
		  .assertThat()
		  .statusCode(200)
		  .time(Matchers.lessThan(5000L));
	}

}