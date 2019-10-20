package com.example.worldinfoapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worldinfoapplication.FinanceActivity;
import com.example.worldinfoapplication.R;
import com.example.worldinfoapplication.SportsActivity;
import com.example.worldinfoapplication.TravelActivity;
import com.example.worldinfoapplication.enitity.MenuData;

import java.util.List;

public class WorldMenuInfoAdapter extends AppBaseRecyclerViewAdapter<MenuData> {
    private Context context;
    private List<MenuData> menuItemsList;

    public WorldMenuInfoAdapter(Context context, List<MenuData> menuItemsList) {
        super(context, menuItemsList);
        this.context = context;
        this.menuItemsList = menuItemsList;
    }

    @Override
    public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
        final View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row_item, parent, false);
        WorldMenuInfoViewHolder viewHolder = new WorldMenuInfoViewHolder(context, view);
        return viewHolder;
    }

    @Override
    public void onBindData(RecyclerView.ViewHolder holder, MenuData menuData,final int position) {
        WorldMenuInfoViewHolder menu1RecyclerViewHolder = (WorldMenuInfoViewHolder) holder;
        menu1RecyclerViewHolder.mImage.setImageResource(menuData.getActivityImage());
        menu1RecyclerViewHolder.mTitle.setText(menuData.getActivityName());
        menu1RecyclerViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(context,position);
            }
        });
    }

    class WorldMenuInfoViewHolder extends RecyclerView.ViewHolder {
        ImageView mImage;
        TextView mTitle;
        CardView mCardView;

        public WorldMenuInfoViewHolder(Context context, View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.ivImage);
            mTitle = itemView.findViewById(R.id.tvTitle);
            mCardView = itemView.findViewById(R.id.cardview);
        }
    }

    private  void  startActivity(Context context,int position) {
        Intent mIntent=null;
        switch (position){
            case 0:
                mIntent=new Intent(context, SportsActivity.class);
                break;
            case 1:
                mIntent= new Intent(context, FinanceActivity.class);
                break;
            case 2:
                mIntent= new Intent(context, TravelActivity.class);

            default:
        }
        context.startActivity(mIntent);

        //Intent mIntent = new Intent(mContext, DetailActivity.class);
        // mIntent.putExtra("Title", menuItemsList.get(holder.getAdapterPosition()).getActivityName());
        //mIntent.putExtra("Description", menuItemsList.get(holder.getAdapterPosition()).getActivityKey());
        //mIntent.putExtra("Image", menuItemsList.get(holder.getAdapterPosition()).getActivityImage());
        //mContext.startActivity(mIntent);


    }
}