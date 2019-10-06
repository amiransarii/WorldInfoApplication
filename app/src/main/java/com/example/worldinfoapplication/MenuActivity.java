package com.example.worldinfoapplication;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import com.example.worldinfoapplication.adapter.MenuAdapter;
import com.example.worldinfoapplication.enitity.MenuData;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends BaseActivity {
   private RecyclerView mRecyclerView;
   private List<MenuData> menuDataList;

    @Override
    protected int getContentView() {
        return R.layout.activity_menu;
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
        showBackArrow();
        mRecyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(MenuActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        menuDataList = new ArrayList<>();

        menuDataList.add(new MenuData(getString(R.string.sports), getString(R.string.sports_key),
                R.drawable.sports));
        menuDataList.add(new MenuData(getString(R.string.finance), getString(R.string.finance_key),
                R.drawable.finance));

        menuDataList.add( new  MenuData(getString(R.string.travel), getString(R.string.travel_key),
                R.drawable.travel));

        menuDataList.add(new MenuData(getString(R.string.entertainment), getString(R.string.entertainment_key),
                R.drawable.entertainment));

        menuDataList.add( new MenuData(getString(R.string.location), getString(R.string.location_key),
                R.drawable.location));

        menuDataList.add(new MenuData(getString(R.string.science), getString(R.string.science_key),
                R.drawable.science));

        menuDataList.add( new MenuData(getString(R.string.food), getString(R.string.food_key),
                R.drawable.food));

        menuDataList.add( new MenuData(getString(R.string.jobs), getString(R.string.jobs_key),
                R.drawable.jobs));

        MenuAdapter myAdapter = new  MenuAdapter(MenuActivity.this, menuDataList);
        mRecyclerView.setAdapter(myAdapter);
    }
}
