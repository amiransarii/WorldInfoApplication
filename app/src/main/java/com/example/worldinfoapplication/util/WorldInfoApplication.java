package com.example.worldinfoapplication.util;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.worldinfoapplication.receiver.ConnectionStateReceiver;

public class WorldInfoApplication extends Application  {

    private static String TAG=WorldInfoApplication.class.getSimpleName();
    /**
     * create the instance of WorldInfoApplication
     * @param mInstance
     */
    private static WorldInfoApplication mInstance;

    // create the instance of RequestQueue
    private RequestQueue mRequestQueue;



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

    /**
     * set the value of mRequest if its null
     * @return the request
     */
     public RequestQueue getRequestQueue(){
        if (mRequestQueue==null){
            mRequestQueue= Volley.newRequestQueue(getApplicationContext());
        }
         return mRequestQueue;
     }

    /**
     *
     * @param request pass the request
     * @param tag set the tag or default tag
     * @param <T> type
     */
      public <T> void addToRequestQueue(Request<T> request, String tag){
         //set the default value if tag is empty
          request.setTag(AppStringUtils.isEmpty(tag)?TAG:tag);
          getRequestQueue().add(request);

      }

    /**
     *
     * @param request pass the request and set default tag
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> request){
          request.setTag(TAG);
          getRequestQueue().add(request);
       }

    /**
     *
     * @param  tag pass this and cancel tag request
     */
    public void cancelPendingRequest(Object tag){
        if(mRequestQueue!=null){
            mRequestQueue.cancelAll(tag);
        }
       }





}
