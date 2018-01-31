package com.xfinity.network;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.xfinity.util.ConnectivityReceiver;

/**
 * Created by risha on 1/30/2018.
 */

public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    public RequestQueue getRequestQueue() {
        if(mRequestQueue == null)
            mRequestQueue =  Volley.newRequestQueue(getApplicationContext());
        return mRequestQueue;
    }
    public static AppController getInstance() {
        return mInstance;
    }
    public <T> void addToRequestQueue(Request<T> request, String tag){
        request.setTag(TextUtils.isEmpty(tag)?TAG:tag);
        getRequestQueue().add(request);
    }
    public <T> void addToRequestQueue(Request<T> request){
        request.setTag(TAG);
        getRequestQueue().add(request);
    }
    public void cancelPendingRequests(Object tag){
        if(mRequestQueue != null)
            mRequestQueue.cancelAll(tag);
    }

    //set the connectivity listener
    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener  =  listener;
    }
}
