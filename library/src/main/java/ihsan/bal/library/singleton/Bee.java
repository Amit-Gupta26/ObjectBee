package ihsan.bal.library.singleton;

import android.content.Context;

import org.json.JSONException;

import java.util.ArrayList;

import ihsan.bal.library.base.BeeModel;
import ihsan.bal.library.bee.PullObject;
import ihsan.bal.library.bee.PushObject;
import ihsan.bal.library.engine.ObjectEngine;
import ihsan.bal.library.engine.PreferencesEngine;

/**
 * Created by ihsan on 04/06/15.
 */
public class Bee {

    static volatile Bee singleton = null;
    private final Context context;
    final PullObject pull;
    final PushObject push;
    final ObjectEngine engine;

    /**
     * Create singletone builder
     * @param context
     * */
    public static Bee with(Context context) {
        if (singleton == null) {
            synchronized (Bee.class) {
                singleton = new Builder(context).build();
            }
        }
        return singleton;
    }

    Bee(Context context, PullObject pull, PushObject push, ObjectEngine engine) {
        this.context = context;
        this.pull = pull;
        this.push = push;
        this.engine = engine;
    }

    /**
     * push object instance
     * @param data
     * */
    public PushCreator been(BeeModel data) {
        return new PushCreator(context, singleton, data);
    }

    /**
     * pull object instance
     * @param classes
     * */
    public PullCreator been(Class classes) {
        return new PullCreator(context, singleton, classes);
    }

    /**
     * Clear all cached objects
     */
    public void clear() {
        PreferencesEngine preferencesEngine = new PreferencesEngine(context);
        ArrayList<String> objectList = null;
        try {
            objectList = new ArrayList<String>(preferencesEngine.getSavedObjectReference());
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            for (String item : objectList) {
                ObjectEngine.deleteObjects(context, item);
            }
        }
    }

    public static class Builder {
        private final Context context;

        /**
         * Start building a new {@link Bee} instance.
         */
        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.context = context.getApplicationContext();
        }

        /**
         * Create the {@link Bee} instance.
         */
        public Bee build() {
            Context context = this.context;
            PullObject pull = new PullObject();
            PushObject push = new PushObject();
            ObjectEngine engine = new ObjectEngine();
            return new Bee(context, pull, push, engine);
        }
    }
}
