package com.guestlogixtest.rickmorty.model.base;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloadAsyncTask extends AsyncTask<ImageView, Void, Bitmap> {

    private ImageView imageView = null;
    private String imageURL;

    public ImageDownloadAsyncTask(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    protected Bitmap doInBackground(ImageView... imageViews) {
        this.imageView = imageViews[0];
        return downloadImage();
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }

    //downloads the image
    private Bitmap downloadImage() {
        Bitmap bmp = null;
        try {
            URL url = new URL(imageURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            InputStream is = con.getInputStream();
            bmp = BitmapFactory.decodeStream(is);
            return bmp;
        } catch (Exception e) {
            Log.d("msg", "Exception whil download image");
            return null;
        }
    }
}