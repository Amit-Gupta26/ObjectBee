package ihsan.bal.objectbee;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import ihsan.bal.library.base.BaseModel;
import ihsan.bal.library.bee.PullObject;


public class DetailActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);

        PullObject pullObject = new PullObject();
        Object object = pullObject.pullBeeObject(this, BaseModel.class);
        if (object!=null) {
            BaseModel model = (BaseModel)object;
            textView.setText(model.title);
        }
    }

}
