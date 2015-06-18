package ihsan.bal.library.base;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ihsan on 23/05/15.
 */
public class ViewModel extends BeeModel {

    @JsonProperty("title")
    public String title;

    @JsonProperty("name")
    public String name;

    public String age;

    public ViewModel(String tag) {
        super(tag);
    }

    public ViewModel() {
        super("");
    }
}
