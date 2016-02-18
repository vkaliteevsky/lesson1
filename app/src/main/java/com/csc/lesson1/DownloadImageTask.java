package com.csc.lesson1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by vkaliteevsky on 18.02.2016.
 */

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    private LruCache<String, Bitmap> mMemoryCache;

    public DownloadImageTask(ImageView bmImage, LruCache memoryCache) {
        this.mMemoryCache = memoryCache;
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    public Bitmap loadFromCache() {
        if (getBitmapFromMemCache("key") != null) {
            return getBitmapFromMemCache("key");
        }
        return null;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
        //addBitmapToMemoryCache("key", result);
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }
}
