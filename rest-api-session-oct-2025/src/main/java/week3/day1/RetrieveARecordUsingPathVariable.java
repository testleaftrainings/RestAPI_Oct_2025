package week3.day1;

import static io.restassured.RestAssured.*;

public class RetrieveARecordUsingPathVariable {

	public static void main(String[] args) {
		given()		 
		 .basePath("/api/now/table")
		 .pathParam("tableName", "incident")
		 .pathParam("sysId", "1c741bd70b2322007518478d83673af3")
		 .baseUri("https://dev324941.service-now.com")
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .log().all()
		.when()
		 .get("/{tableName}/{sysId}")
		.then()		
		 .log().all()
		 .assertThat()
		 .statusCode(200);
	}

}
