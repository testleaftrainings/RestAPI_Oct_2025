package design;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface ApiClient {
	
	public Response get(RequestSpecification requestSpecification, String endPoint);
	
	public Response post(RequestSpecification requestSpecification, String endPoint, Object requestPayload);
	
	public Response put(RequestSpecification requestSpecification, String endPoint, Object requestPayload);
	
	public Response patch(RequestSpecification requestSpecification, String endPoint, Object requestPayload);
	
	public Response delete(RequestSpecification requestSpecification, String endPoint);

}