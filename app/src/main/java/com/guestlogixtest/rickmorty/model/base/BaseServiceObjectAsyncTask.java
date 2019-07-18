package com.guestlogixtest.rickmorty.model.base;

import android.util.Log;

import org.json.JSONObject;

public class BaseServiceObjectAsyncTask<T extends JSONSerializable> extends BaseServiceAsyncTask<T> {

    public BaseServiceObjectAsyncTask(Class<T> theClass, String endpointURL, BaseServiceListener listener) {
        super(theClass, endpointURL, listener);
    }

    @Override
    protected T doInBackground(Void... params) {

        //lets call our server
        String response = callServer();

        try {
            //here we use reflection to instantiate the generic class
            //and call the fromJSON method from the JSONSerializable interface
            T obj = mResultClass.newInstance();
            obj.fromJSON(new JSONObject(response));
            return obj;
        }
        catch (Exception e) {
            //exception instatiating or parsing object
            Log.d("msg", "Error parsing/instantiating json: " + e);
            return null;
        }

    }

}
