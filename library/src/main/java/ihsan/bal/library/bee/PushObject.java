package ihsan.bal.library.bee;

import android.content.Context;
import android.content.Intent;

import ihsan.bal.library.engine.ObjectEngine;

/**
 * Created by ihsan on 02/05/15.
 */
public class PushObject {

    public void pushBeeObject(Context context, Object object, Class activity) {
        boolean beenned = ObjectEngine.savePushedObject(context, object);
        if (beenned)
            context.startActivity(new Intent(context, activity).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public boolean pushBeeObject(Context context, Object object) {
        return ObjectEngine.savePushedObject(context, object);
    }

}
