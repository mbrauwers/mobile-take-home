package com.guestlogixtest.rickmorty.model.base;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BaseServiceListAsyncTask<T extends JSONSerializable> extends BaseServiceAsyncTask<List<T>> {

    private Class<T> singleObjectClass;

    public BaseServiceListAsyncTask(Class<List<T>> theClass, String endpointURL, BaseServiceListener listener) {
        super(theClass, endpointURL, listener);
    }

    public BaseServiceListAsyncTask(Class<List<T>> theClass, Class<T> singleObjectClass, String endpointURL, BaseServiceListener listener) {
        super(theClass, endpointURL, listener);
        this.singleObjectClass = singleObjectClass;
    }


    @Override
    protected List<T> doInBackground(Void... params) {

        //lets call our server
        String response = callServer();

        Log.d("msg", "list characters response is " + response);

        try {
            //here we use reflection to instantiate the generic class
            //and call the fromJSON method from the JSONSerializable interface
            JSONArray jsonArray = new JSONArray(response);

            Log.d("msg", "json array is " + jsonArray);

            if (jsonArray != null) {
                List<T> list = new ArrayList<T>();
                for (int i = 0; i < jsonArray.length(); ++i) {

                    T obj = singleObjectClass.newInstance();
                    obj.fromJSON(jsonArray.getJSONObject(i));
                    list.add(obj);

                }

                return list;
            }
            else {
                return null;
            }

        }
        catch (Exception e) {
            //exception instatiating or parsing object
            Log.d("msg", "Error parsing/instantiating json: " + e);
            return null;
        }

    }

}

