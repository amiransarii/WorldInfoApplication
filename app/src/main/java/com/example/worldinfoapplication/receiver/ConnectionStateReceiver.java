package com.example.worldinfoapplication.receiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

import com.example.worldinfoapplication.util.LogUtils;
import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * <code>ConnectionStateReceiver</code> is a Broadcast
 * Receiver class that monitors the network connection
 * status of the terminal.
 * @author Amir Ansari
 */

public class ConnectionStateReceiver extends BroadcastReceiver {
    private static LogUtils log= new LogUtils(ConnectionStateReceiver.class.getSimpleName(),true);

    /**
     * observer object
     */
    public static Observer observer;

    /**
     * String that indicates the 3G
     */
    public static final String TYPE_3G="3G";

    /**
     * String that indicates the Wifi
     */
    public static final String TYPE_WIFI="WiFi";

    /**
     * String indicating that the destination is unknown;
     */
    public static final String TYPE_UNKNOWN="UNKNOWN";



    public interface Observer {
        /**
         * Changes to the online notification
         */
        void onConnect();

        /**
         * Changes to the offline notification
         */
        void onDisconnect();

        /**
         * get the network type
         */
        void networkType(String netType);
    }

    /**
     * @param context context of the activity
     * @param intent  call the intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        //check online
        if(isConnected(context)){
            //online
            observer.onConnect();
        }
        else {
            //offline
            observer.onDisconnect();
        }
        //network type
        observer.networkType(getNetworkString(context));
    }

    /**
     * Conform whether you can connect to the internet to the network
     *
     * @param context
     * @return true if connection is ok
     */
    public boolean isConnected(Context context) {

        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            if (cm != null) {
                if (Build.VERSION.SDK_INT < 23) {
                    final NetworkInfo ni = cm.getActiveNetworkInfo();
                    if (ni != null)
                        return (ni.isConnected() && (ni.getType() ==
                                ConnectivityManager.TYPE_WIFI || ni.getType() == ConnectivityManager.TYPE_MOBILE));
                } else {
                    final Network n = cm.getActiveNetwork();
                    if (n != null) {
                        final NetworkCapabilities nc = cm.getNetworkCapabilities(n);
                        return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                    }
                }
            }
            return false;

        } catch (NullPointerException e){
            log.e("Network Connection Error "+e.getMessage());
        }
        return false;
    }

    public static String getNetworkString(Context context){
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            if (cm != null) {
                if (Build.VERSION.SDK_INT < 23) {
                    final NetworkInfo ni = cm.getActiveNetworkInfo();
                    if (ni != null) {
                        if (ni.isConnected()) {
                            if (ni.getType() == ConnectivityManager.TYPE_MOBILE) {
                                return TYPE_3G;
                            } else if (ni.getType() == ConnectivityManager.TYPE_WIFI) {
                                return TYPE_WIFI;
                            }
                        }
                    }
                } else {
                    final Network n = cm.getActiveNetwork();
                    if (n != null) {
                        final NetworkCapabilities nc = cm.getNetworkCapabilities(n);
                        if (nc != null) {
                            if (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                                return TYPE_3G;
                            } else if (nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                                return TYPE_WIFI;
                            }
                        }
                    }
                }
            }

        } catch (NullPointerException e){
            log.e("Network null Pointer Exception "+e.getMessage());
        }
        return TYPE_UNKNOWN;
    }

}
