package ihsan.bal.library.singleton;

import android.content.Context;
import android.support.annotation.NonNull;

import ihsan.bal.library.base.BaseBeeModel;
import ihsan.bal.library.bee.PullObject;
import ihsan.bal.library.bee.PushObject;
import ihsan.bal.library.engine.ObjectEngine;

/**
 * Created by ihsan on 04/06/15.
 */
public class Bee {

    static volatile Bee singleton = null;
    private final Context context;
    final PullObject pull;
    final PushObject push;
    final ObjectEngine engine;

    public static Bee with(@NonNull Context context) {
        if (singleton == null) {
            synchronized (Bee.class) {
                if (singleton == null) {
                    singleton = new Builder(context).build();
                }
            }
        }
        return singleton;
    }

    Bee(Context context, PullObject pull, PushObject push, ObjectEngine engine){
        this.context = context;
        this.pull = pull;
        this.push = push;
        this.engine = engine;
    }

    public PushCreator been(BaseBeeModel data){
        return new PushCreator(context,singleton,data);
    }

    public void pull(){

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
