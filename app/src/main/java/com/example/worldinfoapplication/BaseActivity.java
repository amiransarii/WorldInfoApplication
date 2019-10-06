package com.example.worldinfoapplication;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.worldinfoapplication.receiver.ConnectionStateReceiver;
import com.example.worldinfoapplication.util.LogUtils;
import com.example.worldinfoapplication.util.SharedPreferenceUtil;
import com.example.worldinfoapplication.util.WorldInfoApplication;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.worldinfoapplication.util.AppsNetworkUtils.getNetworkString;
import static com.example.worldinfoapplication.util.AppsNetworkUtils.isConnected;
import static com.example.worldinfoapplication.util.AppsNetworkUtils.isNotConnected;

/**
 * <code>BaseActivity</code>
 *  class defines BaseActivity
 *  which will be extends from multiple activity
 *  this one is used to reduce the coding
 * @author Amir Ansari
 */
public abstract class BaseActivity extends AppCompatActivity implements ConnectionStateReceiver.Observer{
    private static LogUtils log=new LogUtils(BaseActivity.class.getSimpleName(),true);
    protected abstract int getContentView();
    protected abstract void networkConnected(boolean isConnected);
    protected abstract void getNetworkType(String networkType);
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    //get the instance of Main Activity
    private static MainActivity mainActivity=null;

    //create the instance of shared Preference
    private SharedPreferenceUtil sharedPreferenceUtil;

    private String loginUserName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        onViewReady(savedInstanceState, getIntent());
        sharedPreferenceUtil= new SharedPreferenceUtil(this);

        //get the main activity instance
       // mainActivity=MainActivity.getInstance();
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        //To be used by child activities
    }

    //connected
    @Override
    public void onConnect() {
        networkConnected(true);

    }
    //network type
    @Override
    public void onDisconnect() {
        networkConnected(false);
    }

    @Override
    public void   networkType(String networkType){
        getNetworkType(networkType);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WorldInfoApplication.getInstance().setConnectivityListener(this);
    }

    /**
     * check Network connected
     * @return true when network is connected
     */
    protected boolean isNetworkConnected(){
        return isConnected(this);
    }

    /**
     * network is not connected
     * @return isNetworkNotConnected()
     */
    protected boolean isNetworkNotConnected(){
        return isNotConnected(this);
    }

    /**
     * find the network type
     * @return getNetworkString(this);
     */
    protected String getNetworkType(){
        return getNetworkString(this);
    }

    /**
     *
     * @param menu add the global menu items
     * @return return the infilated menu items
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    /**
     *
     * @param item
     * @return onclick item in menu file
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         switch (item.getItemId()){
             case android.R.id.home:
                 finish();
                 return true;

                 //add other menu items
                 /*case R.id.xyz:
                 gotoactivity();
                 return true;*/
             default:return super.onOptionsItemSelected(item);
         }
    }


    /**
     *
     * @param layoutId send the layoutId from activity
     * @param message send the message whatever want to show
     * @param color set the color of the text
     */
    protected void showSnackBarErrorMessage(int layoutId,String message,int color){
        Snackbar snackbar=Snackbar.make(findViewById(layoutId),message,Snackbar.LENGTH_LONG);
        View sbView=snackbar.getView();
        TextView textView=(TextView)sbView.findViewById(com.google.android.material.R.id.snackbar_text);
         textView.setTextColor(color);
    }

    /**
     * set the back arrow in activity which you want
     */
    protected void showBackArrow() {
        ActionBar supportActionBar=getSupportActionBar();
        if(supportActionBar!=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
     }

    /**
     *
     * @param title set the title of alert dialog
     * @param message set the message of alert dialog which want to dsiplay
     * @param secondbtn  set the ok button title like yes or Ok
     * @param firtbtn set the cancel button title like Cancel or Dismiss
     * @return true if ok is clicked
     */
       protected void showAlertDialog(String title,String message,String firtbtn,String secondbtn){
           AlertDialog.Builder builder= new AlertDialog.Builder(this);
           builder.setTitle(title);
           builder.setMessage(message)
                   .setCancelable(false)
                   .setPositiveButton(secondbtn, new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                            if(mainActivity!=null){
                               // mainActivity.afterOkClicked();
                            }

                       }
                   })
                   .setNegativeButton(firtbtn, new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           dialog.cancel();
                       }
                   });
           //Creating dialog box
           AlertDialog alertDialog = builder.create();
           //setting the title manually
           alertDialog.setTitle(title);
           alertDialog.show();
       }


    protected boolean checkAndRequestPermissions(String loginUserName) {
           this.loginUserName=loginUserName;

        int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int writePermssion = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionRecordAudio = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }

        if (writePermssion != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (permissionRecordAudio != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECORD_AUDIO);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        log.d("Permission callback called-------");

        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<>();
                // Initialize the map with both permissions
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.RECORD_AUDIO, PackageManager.PERMISSION_GRANTED);

                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++) {
                        perms.put(permissions[i], grantResults[i]);
                        // Check for both permissions
                        if (perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                                && perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                                && perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                                && perms.get(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {

                            log.d( "sms & location services permission granted");
                            // process the normal flow
                            Intent intent_Menu = new Intent(BaseActivity.this, MenuActivity.class);
                            intent_Menu.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent_Menu);
                            finish();
                            saveStringValue(getString(R.string.pref_login_user_name_key),loginUserName);
                        } else {
                            log.d("Some permissions are not granted ask again ");

                            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)
                                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
                                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
                                showDialogOK("Service Permissions are required for this app",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                switch (which) {
                                                    case DialogInterface.BUTTON_POSITIVE:
                                                        checkAndRequestPermissions(loginUserName);
                                                        break;
                                                    case DialogInterface.BUTTON_NEGATIVE:
                                                        finish();
                                                        break;
                                                }
                                            }
                                        });
                            } else {
                                explain("You need to give some mandatory permissions to continue. Do you want to go to app settings?");
                            }
                        }
                    }
                }

            }


        }
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }

    private void explain(String msg) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(msg)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        //  permissionsclass.requestPermission(type,code);
                        startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:com.exampledemo.parsaniahardik.marshmallowpermission")));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        finish();
                    }
                });
        dialog.show();
    }

     //save String type value in shared Preference;
     protected void saveStringValue(String key,String value){
           log.i("Save String Key and Value "+ key +" "+ value);
           sharedPreferenceUtil.savePrefString(key,value);
     }

     protected String getStringValue(String key){
           return sharedPreferenceUtil.loadPrefString(key);
     }

    //save int type value in shared Preference;
    protected void saveIntValue(String key,int value){
        sharedPreferenceUtil.savePrefInt(key,value);
    }

    protected int getIntValue(String key){
        return sharedPreferenceUtil.loadPrefInt(key);
    }

    //save boolean type value in shared Preference;
    protected void saveBooleanValue(String key,boolean value){
        sharedPreferenceUtil.savePrefBoolean(key,value);
    }

    protected Boolean getBooleanValue(String key){
        return sharedPreferenceUtil.loadPrefBoolean(key);
    }




}
