
package week4.day2.sample.response.pojos;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class CategoryHardware {

    @SerializedName("result")
    @Expose
    private List<Result> result = new ArrayList<Result>();
    @SerializedName("addreess")
    @Expose
    private List<Addreess> addreess = new ArrayList<Addreess>();

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public List<Addreess> getAddreess() {
        return addreess;
    }

    public void setAddreess(List<Addreess> addreess) {
        this.addreess = addreess;
    }

}
