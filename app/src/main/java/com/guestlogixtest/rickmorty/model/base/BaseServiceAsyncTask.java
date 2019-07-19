package com.guestlogixtest.rickmorty.model.base;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * Generic AsyncTask class to perform network calls and parse the result on something that implements JSONSerializable
 */

public abstract class BaseServiceAsyncTask<T> extends AsyncTask<Void, Void, T> {

    private final BaseServiceListener listener;
    private final String endpointURL;
    protected final Class<T> resultClass;

    public BaseServiceAsyncTask(Class<T> theClass, String endpointURL, BaseServiceListener listener) {
        this.listener = listener;
        this.endpointURL = endpointURL;
        resultClass = theClass;
    }

    protected String callServer() {

        BufferedReader reader = null;

        try {

            URL url = new URL(endpointURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();

            int statusCode = conn.getResponseCode();
            if (statusCode != 200){
                return null;
            }

            InputStream inputStream = conn.getInputStream();
            StringBuilder buffer = new StringBuilder();

            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            return buffer.toString();

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        } finally {

            Log.d("msg", "the finally is being executed");

            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void onPostExecute(T response) {
        super.onPostExecute(response);

        if (response != null) {
            listener.onFinished(response);
        }
        else {
            listener.onError();
        }

    }

}