package week3.day2;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RetrieveAllRecordsBasedOnQueryParam {

	public static void main(String[] args) {
		
		Map<String, Object> query_params = new HashMap<String, Object>();
		query_params.put("category", "hardware");
		query_params.put("sysparm_fields", "sys_id,number,short_description,description,category");
		query_params.put("sysparm_limit", 3);		
		
		RestAssured.given()		           
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
		           // Validate the result set should have the size 3
		           .body("result", Matchers.hasSize(3)) 
		           // Validate expected list of fields should be available in the JSON array
		           .body("result", Matchers.everyItem(Matchers.hasKey("sys_id")))
		           .body("result", Matchers.everyItem(Matchers.hasKey("number")))
		           .body("result", Matchers.everyItem(Matchers.hasKey("short_description")))
		           .body("result", Matchers.everyItem(Matchers.hasKey("description")))
		           .body("result", Matchers.everyItem(Matchers.hasKey("category")))
		           // Validate all the category field in array should be hardware
		           .body("result.category", Matchers.everyItem(Matchers.equalToIgnoringCase("hardware")));
		           
	}

}