package ihsan.bal.library.singleton;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;

import java.util.ArrayList;

import ihsan.bal.library.base.BeeModel;
import ihsan.bal.library.bee.PushObject;
import ihsan.bal.library.engine.ObjectEngine;
import ihsan.bal.library.engine.PreferencesEngine;

/**
 * Created by ihsan on 04/06/15.
 */
public class PushCreator {

    static volatile PushCreator singleton = null;
    private final Context context;
    final Bee bee;
    final BeeModel data;

    public static PushCreator with(Context context, Bee bee, BeeModel data) {
        if (singleton == null) {
            synchronized (PushCreator.class) {
                singleton = new Builder(context, bee, data).build();
            }
        }
        return singleton;
    }

    PushCreator(Context context, Bee bee, BeeModel data) {
        this.context = context;
        this.bee = bee;
        this.data = data;
    }

    /**
     * Save aClass and start activity
     */
    public void pushAndStart(Class activityClass) {
        PushObject pushObject = new PushObject();
        pushObject.pushBeeObject(context, data, activityClass);
    }

    /**
     * Only save aClass not starting activity
     */
    public void push() {
        PushObject pushObject = new PushObject();
        pushObject.pushBeeObject(context, data);
    }

    public static class Builder {
        private final Context context;
        private final Bee bee;
        private final BeeModel data;

        /**
         * Start building a new {@link PushCreator} instance.
         */
        public Builder(Context context, Bee bee, BeeModel data) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.bee = bee;
            this.context = context.getApplicationContext();
            this.data = data;
        }

        /**
         * Create the {@link PushCreator} instance.
         */
        public PushCreator build() {
            Context context = this.context;
            Bee bee = this.bee;
            BeeModel data = this.data;
            return new PushCreator(context, bee, data);
        }
    }
}
