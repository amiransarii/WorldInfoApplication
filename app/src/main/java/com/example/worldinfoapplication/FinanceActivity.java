package com.example.worldinfoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.VolleyError;
import com.example.worldinfoapplication.enitity.CustomInterfaces;
import com.example.worldinfoapplication.util.CustomJSONArrayObject;
import com.example.worldinfoapplication.util.WorldInfoApplication;

import org.json.JSONArray;

public class FinanceActivity extends BaseActivity implements CustomInterfaces.VolleyJsonArrayResponse{
    private String TAG=FinanceActivity.class.getSimpleName();

    @Override
    protected int getContentView() {
        return R.layout.activity_finance;
    }


    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        String url="https://api.androidhive.info/volley/person_array.json";
        CustomJSONArrayObject customJSONArrayObject= new CustomJSONArrayObject(url,TAG,this);
        WorldInfoApplication.getInstance().addToRequestQueue(customJSONArrayObject.getJsonArrayRequest());

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
    public void onJsonArrayResponse(JSONArray object, String tag) {
        System.out.println("JSONArray Response Recived "+object.toString());

    }

    @Override
    public void onJsonArrayError(VolleyError error, String tag) {

    }
}
