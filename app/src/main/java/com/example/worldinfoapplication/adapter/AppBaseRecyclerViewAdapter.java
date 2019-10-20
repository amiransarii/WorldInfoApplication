package com.example.worldinfoapplication.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class AppBaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<T> items;

    public abstract RecyclerView.ViewHolder setViewHolder(ViewGroup parent);

    public abstract void onBindData(RecyclerView.ViewHolder holder, T val,int position);

    public AppBaseRecyclerViewAdapter(Context context, List<T> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = setViewHolder(parent);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindData(holder,items.get(position),position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems( List<T> savedCardItemz){
        items = savedCardItemz;
        this.notifyDataSetChanged();
    }

    public T getItem(int position){
        return items.get(position);
    }
}
