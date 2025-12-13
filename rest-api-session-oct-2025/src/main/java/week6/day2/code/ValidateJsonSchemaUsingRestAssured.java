package week6.day2.code;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import week6.day2.fakerest.api.response.pojos.GetActivity;

public class ValidateJsonSchemaUsingRestAssured {
	
	static String expectedJsonSchema = """
			{
  "type": "array",
  "items": [
    {
      "type": "object",
      "properties": {
        "id": {
          "type": "integer"
        },
        "title": {
          "type": "string"
        },
        "dueDate": {
          "type": "string"
        },
        "completed": {
          "type": "boolean"
        }
      },
      "required": [
        "id",
        "title",
        "dueDate",
        "completed"
      ]
    }
  ]
}""";

	public static void main(String[] args) {
		System.out.println(SchemaGeneratorUtility.generateJsonSchema(GetActivity[].class));
		RestAssured.given()
		           .baseUri("https://fakerestapi.azurewebsites.net")
		           .basePath("/api/v1")
		           .when()
		           .get("Activities")
		           .then()
		           .assertThat()
		           .log().body(true)
		           .statusCode(200)
		           .contentType(ContentType.JSON)
		           //.body(JsonSchemaValidator.matchesJsonSchema(new File("src/main/resources/schema/fakeapi-get-activities.json")));
		           .body(JsonSchemaValidator.matchesJsonSchema(SchemaGeneratorUtility.generateJsonSchema(GetActivity[].class)));
	}

}
