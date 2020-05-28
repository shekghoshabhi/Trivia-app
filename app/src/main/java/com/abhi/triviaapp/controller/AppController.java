package com.abhi.triviaapp.controller;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

// !!!!!most of the boiler plate code is taken from https://developer.android.com/training/volley/requestqueue.html#singleton

// this is a singleton class created to maintain a lagfree RequestQueue
//because of this singleton class each time something needs to be added to the
// "RequestQueue" a new RequestQueue is not created rather only this one is used
//synchronised keyword is used to maintain a constant as well as good thread pool

public class AppController extends Application {

    public final String TAG = AppController.class.getSimpleName() ;

    private static AppController instance;
    private RequestQueue requestQueue;



    public static synchronized AppController getInstance() {
//        if (instance == null) {
//            instance = new AppController(context);
//        }
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance=this;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req,String tag) {
        req.setTag(TextUtils.isEmpty(tag)?TAG:tag) ;
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG) ;
        getRequestQueue().add(req);
    }


}
