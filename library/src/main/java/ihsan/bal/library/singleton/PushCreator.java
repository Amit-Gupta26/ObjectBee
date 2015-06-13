package ihsan.bal.library.singleton;

import android.content.Context;

import ihsan.bal.library.bee.PullObject;
import ihsan.bal.library.bee.PushObject;
import ihsan.bal.library.engine.ObjectEngine;

/**
 * Created by ihsan on 04/06/15.
 */
public class PushCreator {

    static volatile PushCreator singleton = null;
    private final Context context;
    final PullObject pull;
    final PushObject push;
    final ObjectEngine engine;

    public static PushCreator with(Context context) {
        if (singleton == null) {
            synchronized (PushCreator.class) {
                if (singleton == null) {
                    singleton = new Builder(context).build();
                }
            }
        }
        return singleton;
    }

    PushCreator(Context context, PullObject pull, PushObject push, ObjectEngine engine){
        this.context = context;
        this.pull = pull;
        this.push = push;
        this.engine = engine;
    }

    public void push(){
        push.pushBeeObject(context,null,PushCreator.class);
    }

    public void pull(){

    }

    public static class Builder {
        private final Context context;

        /**
         * Start building a new {@link PushCreator} instance.
         */
        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.context = context.getApplicationContext();
        }

        /**
         * Create the {@link PushCreator} instance.
         */
        public PushCreator build() {
            Context context = this.context;
            PullObject pull = new PullObject();
            PushObject push = new PushObject();
            ObjectEngine engine = new ObjectEngine();
            return new PushCreator(context, pull, push, engine);
        }
    }
}
