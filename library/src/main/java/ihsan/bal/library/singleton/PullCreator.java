package ihsan.bal.library.singleton;

import android.content.Context;
import ihsan.bal.library.base.BeeModel;
import ihsan.bal.library.bee.PullObject;
import ihsan.bal.library.bee.PushObject;

/**
 * Created by ihsan on 04/06/15.
 */
public class PullCreator {

    static volatile PullCreator singleton = null;
    private final Context context;
    final Bee bee;
    final BeeModel data;

    public static PullCreator with(Context context, Bee bee, BeeModel data) {
        if (singleton == null) {
            synchronized (PullCreator.class) {
                if (singleton == null) {
                    singleton = new Builder(context, bee, data).build();
                }
            }
        }
        return singleton;
    }

    PullCreator(Context context, Bee bee, BeeModel data) {
        this.context = context;
        this.bee = bee;
        this.data = data;
    }

    /**
     * Save data and start activity
     */
    public Object pull(Class baseModelClass) {
        PullObject pullObject = new PullObject();
        return pullObject.pullBeeObject(context, baseModelClass);
    }

    public Object pull(Class baseModelClass, boolean delete) {
        PullObject pullObject = new PullObject();
        return pullObject.pullBeeObject(context, baseModelClass, delete);
    }

    /**
     * Only save data not starting activity
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
         * Start building a new {@link PullCreator} instance.
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
         * Create the {@link PullCreator} instance.
         */
        public PullCreator build() {
            Context context = this.context;
            Bee bee = this.bee;
            BeeModel data = this.data;
            return new PullCreator(context, bee, data);
        }
    }
}
