package ihsan.bal.library.bee;

import android.content.Context;

import ihsan.bal.library.engine.ObjectEngine;

/**
 * Created by ihsan on 02/05/15.
 */
public class PullObject {

    public Object pullBeeObject(Context context, Class classses) {
        Object object = ObjectEngine.getPullObject(context, classses);
        return object;
    }

    public Object pullBeeObject(Context context, Class classses,boolean clearcache) {
        Object object = ObjectEngine.getPullObject(context, classses,clearcache);
        return object;
    }

}
