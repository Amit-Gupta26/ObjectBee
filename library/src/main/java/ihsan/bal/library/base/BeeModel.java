package ihsan.bal.library.base;

import java.io.Serializable;

/**
 * Created by ihsan on 23/05/15.
 */
public class BeeModel implements Serializable {

    public boolean deletepullobject;

    public String referencesname;
    /*
    * default ok_response code 100
    * error_response code 99
    * */
    public int responsecode = 100;

    public String responsemessage;

    public BeeModel(String referencesname){
        if (referencesname!=null)
        this.referencesname = referencesname;
    }

}
