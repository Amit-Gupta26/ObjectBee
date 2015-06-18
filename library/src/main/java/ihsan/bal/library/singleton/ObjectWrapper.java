package ihsan.bal.library.singleton;

import android.content.Context;

import java.io.IOException;

import ihsan.bal.library.base.BeeModel;

/**
 * Created by ihsan on 04/06/15.
 */
public class ObjectWrapper {

    static volatile ObjectWrapper singleton = null;
    private final Context context;
    final Bee bee;
    final Class classes;

    public static ObjectWrapper with(Context context,Bee bee,Class classes) {
        if (singleton == null) {
            synchronized (ObjectWrapper.class) {
                if (singleton == null) {
                    singleton = new Builder(context,bee,classes).build();
                }
            }
        }
        return singleton;
    }

    ObjectWrapper(Context context, Bee bee, Class classes){
        this.context = context;
        this.bee = bee;
        this.classes = classes;
    }


    /**
     * Only save classes not starting activity
     * */
    public void converToObjectAndPush(String jsonString) throws IOException {
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        Object obj = mapper.readValue(jsonString, classes);
        bee.been((BeeModel) obj).push();
    }

    public static class Builder {
        private final Context context;
        private final Bee bee;
        private final Class classes;

        /**
         * Start building a new {@link ObjectWrapper} instance.
         */
        public Builder(Context context, Bee bee,Class classes) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.bee = bee;
            this.context = context.getApplicationContext();
            this.classes = classes;
        }

        /**
         * Create the {@link ObjectWrapper} instance.
         */
        public ObjectWrapper build() {
            Context context = this.context;
            Bee bee = this.bee;
            Class classes = this.classes;
            return new ObjectWrapper(context, bee, classes);
        }
    }
}
