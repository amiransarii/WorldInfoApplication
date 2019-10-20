package com.example.worldinfoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.worldinfoapplication.adapter.SportsAdapter;
import com.example.worldinfoapplication.enitity.CustomInterfaces;
import com.example.worldinfoapplication.enitity.Sports;
import com.example.worldinfoapplication.util.CustomJSONObjectRequest;
import com.example.worldinfoapplication.util.WorldInfoApplication;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SportsActivity extends BaseActivity implements CustomInterfaces.VolleyResponse {
    private String TAG=SportsActivity.class.getSimpleName();
    private int mCurLayoutId = R.layout.custom_sports_layout;

    //Listview
    private ListView listView_Sports;


    private List<Sports> sportsList;

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

        sportsList= new ArrayList<Sports>();

        /**
         * impleiments of Listview
         */

        for(int i=1;i<5;i++){
            sportsList.add(new Sports(i,"Sports"+i,7373.91+i, "Mumbai"+i));
        }
        SportsAdapter sportsAdapter= new SportsAdapter(this,R.layout.custom_sports_layout,sportsList);
        listView_Sports =(ListView)findViewById(R.id.lv_sports);
        listView_Sports.setAdapter(sportsAdapter);
        listView_Sports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Item Click Pos"+position,Toast.LENGTH_LONG).show();
            }
        });



       // listView_Sports.setAdapter(sportsAdapter);






    }

    @Override
    public void onResponse(JSONObject object, String tag) {
        System.out.println("Received JSON Object Response "+object.toString());

    }

    @Override
    public void onError(VolleyError error, String tag) {

    }


    //get the value from intent
    @Override
    protected void intentProcess(Intent intent) {
   /*     if (intent.hasExtra(getString(R.string.key_holding))) {
            _mActivityName = intent.getStringExtra(getString(R.string.key_holding));
        }*/

    }
}
