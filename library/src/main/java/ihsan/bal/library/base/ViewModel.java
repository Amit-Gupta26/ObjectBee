package ihsan.bal.library.base;


/**
 * Created by ihsan on 23/05/15.
 */
public class ViewModel extends BeeModel {

    public String title;

    public String name;

    public String age;

    public ViewModel(String tag) {
        super(tag);
    }

    public ViewModel() {
        super("");
    }
}
