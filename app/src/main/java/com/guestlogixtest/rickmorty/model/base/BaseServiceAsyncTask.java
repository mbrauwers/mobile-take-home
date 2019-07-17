package com.guestlogixtest.rickmorty.model.base;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * Interface for async calls callback
 */
interface BaseServiceListener<T> {
    void onFinished(T result);
    void onError();
}

/*
 * Generic AsyncTask class to perform network calls
 */

public class BaseServiceAsyncTask<T extends JSONSerializable> extends AsyncTask<Void, Void, T> {

    private final Context mContext;
    private final BaseServiceListener mListener;
    private final String mEndpointURL;
    private final Class<T> mResultClass;

    public BaseServiceAsyncTask(Context context, String endpointURL, BaseServiceListener listener, Class<T> theClass) {
        mContext = context;
        mListener = listener;
        mEndpointURL = endpointURL;
        mResultClass = theClass;
    }

    @Override
    protected T doInBackground(Void... params) {
        BufferedReader reader = null;

        try {

            URL url = new URL(mEndpointURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();

            int statusCode = conn.getResponseCode();
            if (statusCode != 200){
                return null;
            }

            InputStream inputStream = conn.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            String response = buffer.toString();

            try {
                //here we use reflaction to instantiate the appropriate entity
                T obj = mResultClass.newInstance();
                obj.fromJSON(response);
                return obj;
            }
            catch (Exception e) {
                //exception instatiating or parsing object
                return null;
            }


        } catch (IOException e) {

            e.printStackTrace();
            return null;

        } finally {
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
            mListener.onFinished(response);
        }
        else {
            mListener.onError();
        }

    }

    /*
    private GiphyServiceResponse deserializeServiceResponse(String response){

        GiphyServiceResponse giphyServiceResponse = null;

        try {
            JSONObject responseJson = new JSONObject(response);
            JSONArray dataJson = responseJson.getJSONArray("data");

            ArrayList<Gif> gifs = new ArrayList<Gif>();

            for (int i = 0; i < dataJson.length(); i++) {

                JSONObject gifJson = dataJson.getJSONObject(i);
                JSONObject imagesJson = gifJson.getJSONObject("images");
                JSONObject fixedHeightImageJson = imagesJson.getJSONObject("fixed_height");
                JSONObject fixedWidthImageJson = imagesJson.getJSONObject("fixed_width");
                JSONObject downsizedImageJson = imagesJson.getJSONObject("downsized");

                Image fixedHeight = new Image(fixedHeightImageJson.getString("url"),
                        fixedWidthImageJson.getInt("width"),
                        fixedHeightImageJson.getInt("height"));

                Image fixedWidth = new Image(fixedWidthImageJson.getString("url"),
                        fixedWidthImageJson.getInt("width"),
                        fixedHeightImageJson.getInt("height"));

                Image downsized = new Image(downsizedImageJson.getString("url"),
                        fixedWidthImageJson.getInt("width"),
                        fixedHeightImageJson.getInt("height"));

                Images images = new Images(fixedHeight, fixedWidth, downsized);

                Gif gif = new Gif(gifJson.getString("id"),
                        gifJson.getString("slug"),
                        gifJson.getString("username"),
                        images);

                gifs.add(gif);
            }

            giphyServiceResponse = new GiphyServiceResponse(gifs);
        } catch (JSONException e){
            e.printStackTrace();

            mListener.onError();
        }

        return giphyServiceResponse;
    }
    */
}