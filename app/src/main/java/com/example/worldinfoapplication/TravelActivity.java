package com.example.worldinfoapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.worldinfoapplication.enitity.CustomInterfaces;
import com.example.worldinfoapplication.util.CustomJSONObjectRequest;
import com.example.worldinfoapplication.util.CustomStringRequest;
import com.example.worldinfoapplication.util.WorldInfoApplication;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TravelActivity extends BaseActivity implements CustomInterfaces.VolleyStringResponse {
    private static String TAG=TravelActivity.class.getSimpleName();
    private Map<String, String> mParams;

    @Override
    protected int getContentView() {
        return R.layout.activity_travel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        CustomStringRequest customStringRequest= new CustomStringRequest();
        String response=customStringRequest.getStringResponse("https://api.androidhive.info/volley/person_array.json","amir");
        System.out.println("String Response "+response);

        //String response=CustomStringRequest
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

    }

    @Override
    protected void networkConnected(boolean isConnected) {

    }

    @Override
    protected void getNetworkType(String networkType) {

    }

    @Override
    public void onStringResponse(String response, String tag) {
        System.out.println("Received String Response "+response);
    }

    @Override
    public void onStringResponseError(VolleyError error, String tag) {

    }
}
