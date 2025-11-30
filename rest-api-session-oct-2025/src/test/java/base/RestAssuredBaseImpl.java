package base;

import java.io.File;

import design.ApiClient;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredBaseImpl implements ApiClient {	

	@Override
	public Response get(RequestSpecification requestSpecification, String endPoint) {		
		return requestSpecification.get(endPoint);
	}

	@Override
	public Response post(RequestSpecification requestSpecification, String endPoint, Object requestPayload) {
		 if(requestPayload instanceof String) {
			 return requestSpecification.body((String) requestPayload).post(endPoint);
		 } else if(requestPayload instanceof File) {
			 return requestSpecification.body((File) requestPayload).post(endPoint);
		 } else if(requestPayload == null) {
			 return requestSpecification.post(endPoint);
		 } else {
			 return requestSpecification.body(requestPayload).post(endPoint);
		 }
	}
	
	

	@Override
	public Response put(RequestSpecification requestSpecification, String endPoint, Object requestPayload) {
		if(requestPayload instanceof String) {
			 return requestSpecification.body((String) requestPayload).put(endPoint);
		 } else if(requestPayload instanceof File) {
			 return requestSpecification.body((File) requestPayload).put(endPoint);
		 } else {
			 return requestSpecification.body(requestPayload).put(endPoint);
		 }
	}

	@Override
	public Response patch(RequestSpecification requestSpecification, String endPoint, Object requestPayload) {
		if(requestPayload instanceof String) {
			 return requestSpecification.body((String) requestPayload).patch(endPoint);
		 } else if(requestPayload instanceof File) {
			 return requestSpecification.body((File) requestPayload).patch(endPoint);
		 } else {
			 return requestSpecification.body(requestPayload).patch(endPoint);
		 }
	}

	@Override
	public Response delete(RequestSpecification requestSpecification, String endPoint) {
		return requestSpecification.delete(endPoint);
	}

}