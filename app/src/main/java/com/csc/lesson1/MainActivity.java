package com.csc.lesson1;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    final String key = "key";

    final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    final int cacheSize = maxMemory / 8;

    ImageView imageView;
    DownloadImageTask downloadImageTask;
    private LruCache<String, Bitmap> mMemoryCache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);


        downloadImageTask = new DownloadImageTask(imageView, mMemoryCache);
        downloadImageTask.execute("https://pp.vk.me/c319923/v319923808/2976/Zhm07YmfT14.jpg");

        /*
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };

        image = (Bitmap) getLastNonConfigurationInstance();
        if (image == null) {
            downloadImageTask = new DownloadImageTask(imageView, mMemoryCache);
            downloadImageTask.execute("https://pp.vk.me/c319923/v319923808/2976/Zhm07YmfT14.jpg");
        } else {
            imageView.setImageBitmap(image);
        }

        if (getLastNonConfigurationInstance() != null) {
            mMemoryCache = (LruCache) getLastNonConfigurationInstance();
        }
        downloadImageTask = new DownloadImageTask(imageView, mMemoryCache);
*/
    }

    public Object onRetainCustomNonConfigurationInstance() {
        return ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        //return mMemoryCache;
    }

    protected void onResume() {
        super.onResume();
/*
        Bitmap bmp = downloadImageTask.loadFromCache();
        if (bmp != null) {
            imageView.setImageBitmap(bmp);
        } else {
            downloadImageTask.execute("https://pp.vk.me/c319923/v319923808/2976/Zhm07YmfT14.jpg");
        }
        */
        Log.d(LOG_TAG, "onResume ");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
