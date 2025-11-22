package week3.day2.servicenow.response.pojos;

import com.google.gson.annotations.SerializedName;

public class Result {
	
	@SerializedName("sys_id")
	private String sysId;

	public String getSysId() {
		return sysId;
	}	

}