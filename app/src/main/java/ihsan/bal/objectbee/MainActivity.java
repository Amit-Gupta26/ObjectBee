package ihsan.bal.objectbee;

import android.app.Activity;
import android.os.Bundle;

import ihsan.bal.library.base.ViewModel;
import ihsan.bal.library.bee.PushObject;
import ihsan.bal.library.singleton.Bee;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushObject pushObject = new PushObject();
        ViewModel model = new ViewModel();
        model.referencesname = "tag1";
        model.title = "Pushed A model";
        model.age = "25";
        model.name = "ihsan";

        Bee.with(this).been(model).pushAndStart(DetailActivity.class);

    }

}
