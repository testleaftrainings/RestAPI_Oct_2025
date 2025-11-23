package week3.day2;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sample.serialization.pojos.Addreess;
import sample.serialization.pojos.Sample;
import week3.day2.servicenow.pojos.CreateIncident;
import week3.day2.servicenow.pojos.CreateIncidentSerializer;

public class ToUnderStandTheSerialization {

	public static void main(String[] args) {
		
		Sample sample = new Sample();
		List<Addreess> list = new ArrayList<Addreess>();

		Addreess prement = new Addreess();
		prement.setStreetName("new street");
		prement.setCountry("India");
		Addreess temp = new Addreess();
		temp.setStreetName("2nd street");
		temp.setCountry("India");

		list.add(prement);
		list.add(temp);		
		
		sample.setFirstName("Karthikeyan");
		sample.setAddreess(list);
		
		System.out.println(new Gson().toJson(sample));
		
		CreateIncident incident = new CreateIncident();
		incident.setShortDescription("RESTAPIOCT2025");
		incident.setCategory("harware");
		
		Gson gson = new GsonBuilder()
		      .registerTypeAdapter(CreateIncident.class, new CreateIncidentSerializer())
		      .create();
		
		System.out.println(gson.toJson(incident));
		
	}

}