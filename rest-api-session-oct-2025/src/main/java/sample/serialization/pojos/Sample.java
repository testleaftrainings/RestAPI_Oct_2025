package sample.serialization.pojos;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sample {
	
	@SerializedName("first name")
	@Expose
	private String firstName;
	@SerializedName("addreess")
	@Expose
	private List<Addreess> addreess = new ArrayList<Addreess>();	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public List<Addreess> getAddreess() {
        return addreess;
    }

    public void setAddreess(List<Addreess> addreess) {
        this.addreess = addreess;
    }

}