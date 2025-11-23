package week4.day1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import servicenow.response.pojos.Result;

public class ExtractJsonArrayUsingJsonPath {
	
	public static void main(String[] args) {
		Map<String, Object> query_params = new HashMap<String, Object>();
		query_params.put("category", "hardware");
		query_params.put("sysparm_fields", "sys_id,number,short_description,description,category");
		query_params.put("sysparm_limit", 3);		
		
		List<Result> list = RestAssured.given()		           
		           .auth()
		           .basic("admin", "e5!pRsPN%lH5")
		           .queryParams(query_params)
		           .log().all()
		           .when()
		           .get("https://dev324941.service-now.com/api/now/table/incident")
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(200)
		           .statusLine(Matchers.containsString("OK")) // HTTP1.1 200 OK
		           .contentType(ContentType.JSON)
		           .time(Matchers.lessThan(5000L))
		           .extract()
		           .response()
		           .jsonPath()
		           .getList("result", Result.class);
		
		for (Result result : list) {
			System.out.println(result.getSysId());
		}
	}

}