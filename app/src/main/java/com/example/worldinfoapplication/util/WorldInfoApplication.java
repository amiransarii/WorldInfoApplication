package com.example.worldinfoapplication.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.worldinfoapplication.BaseActivity;
import com.example.worldinfoapplication.R;
import com.example.worldinfoapplication.receiver.ConnectionStateReceiver;

import java.util.UUID;

public class WorldInfoApplication extends Application  {

    private static String TAG=WorldInfoApplication.class.getSimpleName();

    private BaseActivity baseActivity;

    /**
     * create the instance of WorldInfoApplication
     * @param mInstance
     */
    private static WorldInfoApplication mInstance;

    // create the instance of RequestQueue
    private RequestQueue mRequestQueue;


    /**
     * Multiplication mark
     */
    private final static String MULTIPLY = "×";


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


    /**
     * Returns the user agent.
     *
     * @param connName ConnName network name ( "3G" or "WiFi" or "UNKNOWN")
     * @return user agent
     */
    public String getUserAgent(String connName) throws PackageManager.NameNotFoundException {
        String appVersion=this.getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        StringBuilder sb = new StringBuilder("iSPEED/");
        sb.append(appVersion + "(");
        sb.append(android.os.Build.MODEL + "/");
        sb.append(android.os.Build.PRODUCT + "; ");
        sb.append("Android/");
        sb.append(android.os.Build.VERSION.RELEASE);
        sb.append(";");
        sb.append("network=" + connName + ";");
        sb.append("uid=" + getUuid(this));
        sb.append(")");
        return sb.toString();
    }


    private String getUuid(Context context) {
        String uuid=null;
        if (uuid == null) {
            SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(this);
         //   uuid = spf.getString(getString(R.string.pref_uuid), null);

            if (uuid == null) {
                uuid = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = spf.edit();
               // editor.putString(getString(R.string.pref_uuid), uuid);
                editor.commit();
            }
        }
        return uuid;

    }

<<<<<<< HEAD


=======
    /**
     * To get the screen resolution.
     *
     * @return Horizontal x vertical
     */
    @SuppressWarnings("deprecation")
    private String getDispSize() {
        // Get an instance of window manager
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        // Generation instance of display

        Display disp = wm.getDefaultDisplay();

        // getWidth () / getHeight () of display class is a warning out for deprecated in API level 13 or later.

        // (API level 13 or later getSize (Point point) are prepared.)

        StringBuilder sb = new StringBuilder();
        sb.append(disp.getWidth());
        sb.append(MULTIPLY);
        sb.append(disp.getHeight());
        return sb.toString();
    }




    public void setBaseActivity(BaseActivity bAct) {
        baseActivity = bAct;
    }

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }
>>>>>>> 7cdcd59ddf6b37e8fedd2e1e1704396976c4da0e
}
