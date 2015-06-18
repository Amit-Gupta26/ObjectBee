package ihsan.bal.library.singleton;

import android.content.Context;

import ihsan.bal.library.base.BaseBeeModel;
import ihsan.bal.library.bee.PushObject;

/**
 * Created by ihsan on 04/06/15.
 */
public class PushCreator {

    static volatile PushCreator singleton = null;
    private final Context context;
    final Bee bee;
    final BaseBeeModel data;

    public static PushCreator with(Context context,Bee bee,BaseBeeModel data) {
        if (singleton == null) {
            synchronized (PushCreator.class) {
                if (singleton == null) {
                    singleton = new Builder(context,bee,data).build();
                }
            }
        }
        return singleton;
    }

    PushCreator(Context context, Bee bee, BaseBeeModel data){
        this.context = context;
        this.bee = bee;
        this.data = data;
    }

    /**
     * Save data and start activity
     * */
    public void pushAndStart(Class activityClass){
        PushObject pushObject = new PushObject();
        pushObject.pushBeeObject(context,data,activityClass);
    }

    /**
     * Only save data not starting activity
     * */
    public void push(){
        PushObject pushObject = new PushObject();
        pushObject.pushBeeObject(context,data);
    }

    public static class Builder {
        private final Context context;
        private final Bee bee;
        private final BaseBeeModel data;

        /**
         * Start building a new {@link PushCreator} instance.
         */
        public Builder(Context context, Bee bee,BaseBeeModel data) {
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
            BaseBeeModel data = this.data;
            return new PushCreator(context, bee, data);
        }
    }
}
