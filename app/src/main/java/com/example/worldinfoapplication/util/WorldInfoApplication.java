package com.example.worldinfoapplication.util;

import android.app.Application;

import com.example.worldinfoapplication.receiver.ConnectionStateReceiver;

public class WorldInfoApplication extends Application  {
    /**
     * create the instance of WorldInfoApplication
     * @param mInstance
     */
    private static WorldInfoApplication mInstance;

    /**
     * set the current instance
     * onCreate method
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    /**create the instance and return it
     * @return current instance
     */
    public static synchronized WorldInfoApplication getInstance(){
        return mInstance;
    }

    public void setConnectivityListener(ConnectionStateReceiver.Observer observer){
        ConnectionStateReceiver.observer=observer;
    }


}
