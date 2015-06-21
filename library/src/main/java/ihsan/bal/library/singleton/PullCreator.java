package ihsan.bal.library.singleton;

import android.content.Context;

import ihsan.bal.library.bee.PullObject;

/**
 * Created by ihsan on 04/06/15.
 */
public class PullCreator {

    static volatile PullCreator singleton = null;
    private final Context context;
    final Bee bee;
    final Class aClass;

    public static PullCreator with(Context context, Bee bee, Class data) {
        if (singleton == null) {
            synchronized (PullCreator.class) {
                if (singleton == null) {
                    singleton = new Builder(context, bee, data).build();
                }
            }
        }
        return singleton;
    }

    PullCreator(Context context, Bee bee, Class data) {
        this.context = context;
        this.bee = bee;
        this.aClass = data;
    }

    /**
     * Get pushed object
     */
    public Object pull() {
        PullObject pullObject = new PullObject();
        return pullObject.pullBeeObject(context, aClass);
    }

    /**
     * Get pushed object and delete it.
     */
    public Object pull(boolean delete) {
        PullObject pullObject = new PullObject();
        return pullObject.pullBeeObject(context, aClass, delete);
    }

    /**
     * Get pushed object with tag name
     */
    public Object pull(String tag) {
        PullObject pullObject = new PullObject();
        return pullObject.pullBeeObject(context, aClass);
    }

    /**
     * Get pushed object with tag and dalete it.
     */
    public Object pull(boolean delete, String tag) {
        PullObject pullObject = new PullObject();
        return pullObject.pullBeeObject(context, aClass, delete, tag);
    }

    public static class Builder {
        private final Context context;
        private final Bee bee;
        private final Class aClass1;

        /**
         * Start building a new {@link PullCreator} instance.
         */
        public Builder(Context context, Bee bee, Class data) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.bee = bee;
            this.context = context.getApplicationContext();
            this.aClass1 = data;
        }

        /**
         * Create the {@link PullCreator} instance.
         */
        public PullCreator build() {
            Context context = this.context;
            Bee bee = this.bee;
            Class data = this.aClass1;
            return new PullCreator(context, bee, data);
        }
    }
}
