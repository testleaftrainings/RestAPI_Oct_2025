package week6.day2.code;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AttachReportToTheUserSttory {

	public static void main(String[] args) {
		RestAssured.given()
		           .baseUri("https://karthistestlab.atlassian.net")
		           .basePath("/rest/api/3")
		           .auth()		     
		           .preemptive()
		           .basic("karthis.testlab@gmail.com", "<JIRA_API_TOKEN>")
		           .header("X-Atlassian-Token", "no-check")
		           .log().headers()
		           .contentType(ContentType.MULTIPART)
		           .when()
		           .multiPart(new File("./cucumber-report/result.html"))
		           .post("/issue/10007/attachments")
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(200)
		           .contentType(ContentType.JSON);
	}

}