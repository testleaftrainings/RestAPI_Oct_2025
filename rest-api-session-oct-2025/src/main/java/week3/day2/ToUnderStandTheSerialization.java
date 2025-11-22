package week3.day2;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import sample.serialization.pojos.Addreess;
import sample.serialization.pojos.Sample;

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
		
	}

}