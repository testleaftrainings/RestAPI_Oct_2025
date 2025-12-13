package week6.day2.code;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GitHubGraphQLTest {
	
	static String query = """
			query {
    viewer {
    login
    name
    avatarUrl
    company
    repositories {            
      totalCount
      totalDiskUsage
    }
    followers {
      totalCount      
    }
  }
}
			""";

	public static void main(String[] args) {
		RestAssured.given()
		           .baseUri("https://api.github.com")
		           .basePath("/graphql")		           
		           .header("Authorization", "Bearer <GITHUB_TOKEN>")
		           .log().all()
		           .when()
		           .body(convertGraphQLQueryToJSONString(query))
		           .post()
		           .then()
		           .log().body(true)
		           .assertThat()
		           .statusCode(200)
		           .contentType(ContentType.JSON);
	}
	
	private static String convertGraphQLQueryToJSONString(String query) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("query", query);
		return jsonObject.toString();
	}

}