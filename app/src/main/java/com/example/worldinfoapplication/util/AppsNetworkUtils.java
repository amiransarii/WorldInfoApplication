package com.example.worldinfoapplication.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * <code>AppsNetworkUtils</code>
 *  class defines network related utility.
 *  The class provides a method to get the connection
 *  type of network and confirm network connection is available or not.
 *
 * @author Amir Ansari
 */

public class AppsNetworkUtils {

    /**
     * create log instance
     */
    private static LogUtils log=new LogUtils(AppsNetworkUtils.class.getSimpleName(),true);
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

    /**
     * Conform whether you connect to the network or not
     * @param context information
     * return true: connection ok
     */
    public static boolean isConnected(Context context) {
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

    /**
     *
     * @param context context information
     * @return  No connection
     */
        public static boolean isNotConnected(Context context){
        return !isNotConnected(context);
        }

    /**
     * Returns the connection type of network. <br>
     * <pre>
     * Return the following values.
     * "3G" or "WiFi" or "UNKNOWN"
     * Return UNKNOWN if it is not 3G or Wifi.
     * </pre>
     *
     * @param context context information
     * @return string that indicates the connection type of network
     */
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
