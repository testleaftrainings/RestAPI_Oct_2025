package week3.day2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import servicenow.response.pojos.CategoryHardware;
import servicenow.response.pojos.Result;

public class RetrieveAllRecordsBasedOnQueryParamUsingDeSeiralization {

	public static void main(String[] args) {
		
		Map<String, Object> query_params = new HashMap<String, Object>();
		query_params.put("category", "hardware");
		query_params.put("sysparm_fields", "sys_id,number,short_description,description,category");
		query_params.put("sysparm_limit", 3);		
		
		List<Result> results = RestAssured.given()		           
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
		           .as(CategoryHardware.class)
		           .getResult();
		
		System.out.println(results.size());
		
		for (Result result : results) {
			System.out.println(result.getCategory());
			MatcherAssert.assertThat(result.getCategory(), Matchers.equalToIgnoringCase("hardware"));
		}
		           
	}

}