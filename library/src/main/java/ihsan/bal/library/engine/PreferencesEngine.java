package ihsan.bal.library.engine;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by N90005979 on 22.06.2015.
 */
public class PreferencesEngine {

    private static final String PREF_LOG = "Object Bee Logger";
    private SharedPreferences preferences;
    private static final String SHARED_CONTEXT_ORIVATE = "OBJECT:BEE:SHARED_PREFERENCES";
    private static final String SHARED_PREF = "OBJECT_JSON";

    public PreferencesEngine (Context context){
        preferences = context.getSharedPreferences(SHARED_CONTEXT_ORIVATE,Context.MODE_PRIVATE);
    }

    public void addObjctToPreferences(String classesName) throws JSONException {
        String jsonArray = preferences.getString(SHARED_PREF,null);
        JSONArray array;
        if (jsonArray != null){
            array = new JSONArray(jsonArray);
            array.put(classesName);
            preferences
                    .edit()
                    .putString(SHARED_PREF, array.toString())
                    .commit();
        }else{
            array = new JSONArray();
            array.put(classesName);
            preferences.edit().putString(SHARED_PREF,array.toString()).commit();
        }
        Log.w(PREF_LOG,"added new object to array:"+classesName);
    }

    public ArrayList<String> getSavedObjectReference() throws JSONException {
        ArrayList<String> stringArrayList = new ArrayList<String>();
        String jsonArray = preferences.getString(SHARED_PREF,null);
        if (jsonArray != null){
            JSONArray arrayObj = new JSONArray(jsonArray);
            for (int i=0;i<arrayObj.length();i++)
                stringArrayList.add(arrayObj.get(i).toString());
        }
        Log.w(PREF_LOG,"return of saved last object list:"+jsonArray);
        return stringArrayList;
    }

    public void removeSelectedItem(String item, int i) throws JSONException {
        String array = preferences.getString(SHARED_PREF, null);
        JSONArray oldArray = null;
        if (array!=null){
            oldArray = new JSONArray(array);
        }
        JSONArray newArray = new JSONArray();

        if (oldArray != null)
        for (int x=0;x<oldArray.length();x++){
            if (x != i){
                newArray.put(oldArray.get(x));
            }else
                Log.w(PREF_LOG,"removed selected object:"+oldArray.get(x).toString());
        }
    }
}
