package com.example.worldinfoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.worldinfoapplication.constant.AppConstant;

/**
 * <code>MainActivity</code>
 *  class defines Splash Screen Activity.
 *  and go to Login Activity After 3 seconds
 * @author Amir Ansari
 */

public class MainActivity extends BaseActivity {
     // create the handler
   // private Handler mHandler;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /**
                 * Go to Login Activity
                 */
                Intent intent_Login= new Intent(MainActivity.this,LoginActivity.class);
                intent_Login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent_Login);
                finish();
            }
        }, AppConstant.SPLASH_SCREEN_TIME_OUT);
    }

    //get the network connection using broadcast
    @Override
    protected void networkConnected(boolean isConnected) {
    }

    //get the network type using broadcast
    @Override
    protected void getNetworkType(String networkType) {
    }



}
