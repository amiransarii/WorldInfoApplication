package com.example.worldinfoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.worldinfoapplication.enitity.CustomInterfaces;
import com.example.worldinfoapplication.util.CustomJSONObjectRequest;
import com.example.worldinfoapplication.util.WorldInfoApplication;

import org.json.JSONObject;

public class SportsActivity extends BaseActivity implements CustomInterfaces.VolleyResponse {
    private String TAG=SportsActivity.class.getSimpleName();

    @Override
    protected int getContentView() {
        return R.layout.activity_sports;
    }

    @Override
    protected void networkConnected(boolean isConnected) {

    }

    @Override
    protected void getNetworkType(String networkType) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        String url="https://api.androidhive.info/volley/person_object.json";
        CustomJSONObjectRequest request = new CustomJSONObjectRequest(Request.Method.GET, url,
                new JSONObject(), TAG, this);
        WorldInfoApplication.getInstance().addToRequestQueue(request.getJsonObjectRequest());

    }

    @Override
    public void onResponse(JSONObject object, String tag) {
        System.out.println("Received JSON Object Response "+object.toString());

    }

    @Override
    public void onError(VolleyError error, String tag) {

    }
}
