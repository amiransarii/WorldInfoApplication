package com.example.worldinfoapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.worldinfoapplication.R;
import com.example.worldinfoapplication.enitity.Sports;
import java.util.List;

public class SportsAdapter extends AppBaseAdapter<Sports> {
    private ViewHolder viewHolder;

    /**
     * @param context
     * @param resource
     * @param itemList
     */
    public SportsAdapter(@NonNull Context context, int resource, List<Sports> itemList) {
        super(context, resource, itemList);
    }

    @Override
    public View getAppView(int position, View convertView, ViewGroup parent, Sports sports,LayoutInflater inflater) {
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_sports_layout, parent, false);
            viewHolder.textView_sportsId = (TextView) convertView.findViewById(R.id.txt_sports_Id);
            viewHolder.textView_sportsName = (TextView) convertView.findViewById(R.id.txt_sports_name);
            viewHolder.textView_salary = (TextView) convertView.findViewById(R.id.txt_sports_salary);
            viewHolder.textView_location = (TextView) convertView.findViewById(R.id.txt_sports_location);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /**
         * set the value
         */
          viewHolder.textView_sportsId.setText(String.valueOf(sports.getSportsID()));
          viewHolder.textView_sportsName.setText(sports.getSportsName());
          viewHolder.textView_salary.setText(String.valueOf(sports.getCoachSalary()));
          viewHolder.textView_location.setText(sports.getSportsLocation());

        return convertView;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView textView_sportsId;
        TextView textView_sportsName;
        TextView textView_salary;
        TextView textView_location;

    }
}
