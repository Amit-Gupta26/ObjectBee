package ihsan.bal.library.engine;

import android.content.Context;

import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import ihsan.bal.library.base.BeeModel;

/**
 * Created by ihsan on 02/05/15.
 */
public class ObjectEngine {

    public static boolean savePushedObject(Context context, Object object) {
        String fileName = null;
        fileName = object.getClass().getSimpleName();
        BeeModel model = (BeeModel) object;
        if (model != null && model.referencesname != null && !model.referencesname.equals("")) {
            fileName += model.referencesname;
        }
        final File suspend_f = new File(context.getCacheDir(), fileName);

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean keep = true;

        try {
            fos = new FileOutputStream(suspend_f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
        } catch (Exception e) {
            keep = false;
        } finally {
            try {
                if (oos != null) oos.close();
                if (fos != null) fos.close();
                if (keep == false) suspend_f.delete();
            } catch (Exception e) {
                keep = false;
            }
        }
        if (keep){
            PreferencesEngine engine = new PreferencesEngine(context);
            try {
                engine.addObjctToPreferences(fileName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return keep;
    }

    public static boolean deleteObjects(Context context, String objectName) {
        String fileName = null;
        fileName = objectName;

        final File suspend_f = new File(context.getCacheDir(), fileName);
        if (suspend_f.exists()){
            suspend_f.delete();
            return true;
        }else
        return false;
    }

    public static Object getPullObject(Context context, Class objectClass, String tag) {
        String fileName = null;
        fileName = objectClass.getSimpleName();
        if (tag != null && !tag.equals("")) {
            fileName += tag;
        }
        final File suspend_f = new File(context.getCacheDir(), fileName);

        Object simpleClass = null;
        FileInputStream fis = null;
        ObjectInputStream is = null;

        try {
            fis = new FileInputStream(suspend_f);
            is = new ObjectInputStream(fis);
            simpleClass = (Object) is.readObject();
        } catch (Exception e) {
            String val = e.getMessage();
        } finally {
            try {
                if (fis != null) fis.close();
                if (is != null) is.close();
            } catch (Exception e) {
            } finally {
                if (simpleClass != null) {
                    BeeModel beeModel = (BeeModel) simpleClass;
                    if (beeModel.deletepullobject)
                        suspend_f.delete();
                } else
                    return null;
            }
        }

        return simpleClass;
    }

    public static Object getPullObject(Context context, Class objectClass) {
        final File suspend_f = new File(context.getCacheDir(), objectClass.getSimpleName());

        Object simpleClass = null;
        FileInputStream fis = null;
        ObjectInputStream is = null;

        try {
            fis = new FileInputStream(suspend_f);
            is = new ObjectInputStream(fis);
            simpleClass = (Object) is.readObject();
        } catch (Exception e) {
            String val = e.getMessage();
        } finally {
            try {
                if (fis != null) fis.close();
                if (is != null) is.close();
            } catch (Exception e) {
            } finally {
                if (simpleClass != null) {
                    BeeModel beeModel = (BeeModel) simpleClass;
                    if (beeModel.deletepullobject)
                        suspend_f.delete();
                } else
                    return null;
            }
        }

        return simpleClass;
    }

    public static Object getPullObject(Context context, Class objectClass, boolean clearcache) {
        final File suspend_f = new File(context.getCacheDir(), objectClass.getSimpleName());

        Object simpleClass = null;
        FileInputStream fis = null;
        ObjectInputStream is = null;

        try {
            fis = new FileInputStream(suspend_f);
            is = new ObjectInputStream(fis);
            simpleClass = (Object) is.readObject();
        } catch (Exception e) {
            String val = e.getMessage();
        } finally {
            try {
                if (fis != null) fis.close();
                if (is != null) is.close();
            } catch (Exception e) {
            } finally {
                if (simpleClass != null) {
                    BeeModel beeModel = (BeeModel) simpleClass;
                    if (beeModel.deletepullobject || clearcache)
                        suspend_f.delete();
                } else
                    return null;
            }
        }

        if (simpleClass != null){
            PreferencesEngine engine = new PreferencesEngine(context);
            try {
                ArrayList<String> prefList = new ArrayList<>(engine.getSavedObjectReference());
                int i = 0;
                for (String item : prefList){
                    if (item.equals(objectClass.getSimpleName())){
                        engine.removeSelectedItem(item,i);
                    }
                    i += 1;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return simpleClass;
    }

    public static Object getPullObject(Context context, Class objectClass, boolean clearcache, String tag) {
        String fileName = null;
        fileName = objectClass.getSimpleName();
        if (tag != null && !tag.equals("")) {
            fileName += tag;
        }
        final File suspend_f = new File(context.getCacheDir(), fileName);

        Object simpleClass = null;
        FileInputStream fis = null;
        ObjectInputStream is = null;

        try {
            fis = new FileInputStream(suspend_f);
            is = new ObjectInputStream(fis);
            simpleClass = (Object) is.readObject();
        } catch (Exception e) {
            String val = e.getMessage();
        } finally {
            try {
                if (fis != null) fis.close();
                if (is != null) is.close();
            } catch (Exception e) {
            } finally {
                if (simpleClass != null) {
                    BeeModel beeModel = (BeeModel) simpleClass;
                    if (beeModel.deletepullobject || clearcache)
                        suspend_f.delete();
                } else
                    return null;
            }
        }

        if (simpleClass != null){
            PreferencesEngine engine = new PreferencesEngine(context);
            try {
                ArrayList<String> prefList = new ArrayList<>(engine.getSavedObjectReference());
                int i = 0;
                for (String item : prefList){
                    if (item.equals(fileName)){
                        engine.removeSelectedItem(item,i);
                    }
                    i += 1;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return simpleClass;
    }

}
