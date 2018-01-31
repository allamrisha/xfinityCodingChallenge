package com.xfinity.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.xfinity.network.AppController;


public class ConnectivityReceiver extends BroadcastReceiver {
    public static ConnectivityReceiverListener connectivityReceiverListener;

    public ConnectivityReceiver() {
        super();
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager   =   (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo  = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        boolean isConnected  =  activeNetworkInfo !=  null
                && activeNetworkInfo.isConnectedOrConnecting();

        if (connectivityReceiverListener !=  null) {
            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        }
    }
    public static boolean isConnected() {
        ConnectivityManager
                connectivityManager  =  (ConnectivityManager) AppController.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork  = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetwork !=  null
                && activeNetwork.isConnectedOrConnecting();
    }


    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }
}
