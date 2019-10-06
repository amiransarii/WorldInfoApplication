package com.example.worldinfoapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldinfoapplication.R;
import com.example.worldinfoapplication.enitity.MenuData;

import java.util.List;

/**
 * Created by Amir Ansari on 06/10/19.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuItemViewHolder> {

    private Context mContext;
    private List<MenuData> menuItemsList;

    public MenuAdapter(Context mContext, List<MenuData> menuItemsList) {
        this.mContext = mContext;
        this.menuItemsList =menuItemsList;
    }

    @Override
    public MenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row_item, parent, false);
        return new MenuItemViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final MenuItemViewHolder holder, final int position) {
        holder.mImage.setImageResource(menuItemsList.get(position).getActivityImage());
        holder.mTitle.setText(menuItemsList.get(position).getActivityName());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   startActivity(mContext,position);
            }
        });
    }



    @Override
    public int getItemCount() {
        return menuItemsList.size();
    }

    /**
     *
     * @param position call the activity
     */
    private  void  startActivity(Context context,int position) {
        //Intent mIntent = new Intent(mContext, DetailActivity.class);
        // mIntent.putExtra("Title", menuItemsList.get(holder.getAdapterPosition()).getActivityName());
        //mIntent.putExtra("Description", menuItemsList.get(holder.getAdapterPosition()).getActivityKey());
        //mIntent.putExtra("Image", menuItemsList.get(holder.getAdapterPosition()).getActivityImage());
        //mContext.startActivity(mIntent);


    }
}

class MenuItemViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle;
    CardView mCardView;

    MenuItemViewHolder(View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mCardView = itemView.findViewById(R.id.cardview);
    }


}

