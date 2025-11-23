package week3.day2.servicenow.pojos;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CreateIncidentSerializer implements JsonSerializer<CreateIncident>{

	@Override
	public JsonElement serialize(CreateIncident src, Type typeOfSrc, JsonSerializationContext context) {
		
		if(src.getShortDescription() == null) {
			throw new JsonParseException("Missing required field for serialization: short_description");
		}
		
		Gson gsonWithoutCustomSerializer = new Gson(); 
		JsonObject jsonObject = gsonWithoutCustomSerializer.toJsonTree(src).getAsJsonObject();
		
		return jsonObject;
	}

}
