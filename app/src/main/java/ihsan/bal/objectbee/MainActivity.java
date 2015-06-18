package ihsan.bal.objectbee;

import android.app.Activity;
import android.os.Bundle;

import java.io.IOException;

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
        model.referencesname = ":tag1";
        model.title = "Pushed A model";

        //Bee.with(this).been(model).pushAndStart(DetailActivity.class);
        try {
            Bee.with(this).mapper(ViewModel.class).converToObjectAndPush("{\\\"name\\\":\\\"Bob\\\", \\\"age\\\":13}");
        } catch (IOException e) {


        }
    }

}
