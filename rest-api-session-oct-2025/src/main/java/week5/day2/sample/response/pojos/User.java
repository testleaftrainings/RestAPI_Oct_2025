
package week5.day2.sample.response.pojos;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")    
    private Integer id;
    @SerializedName("name")   
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}