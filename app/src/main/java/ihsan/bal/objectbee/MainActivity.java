package ihsan.bal.objectbee;

import android.app.Activity;
import android.os.Bundle;

import ihsan.bal.library.base.BaseModel;
import ihsan.bal.library.bee.PushObject;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushObject pushObject = new PushObject();
        BaseModel model = new BaseModel();
        model.title = "Pushed A model";
        pushObject.pushBeeObject(this,model,DetailActivity.class);
    }

}
