package com.theodhor.customcalendar.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.theodhor.customcalendar.R;

public class WeekAdapter extends BaseAdapter {

    private final int WEEK_SIZE = 7;
    private boolean startFromMonday;

    public WeekAdapter(boolean startFromMonday) {
        this.startFromMonday = startFromMonday;
    }

    @Override
    public int getCount() {
        return WEEK_SIZE;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.week_day_item, null);
        TextView dayTextView = view.findViewById(R.id.week_day_name);
        dayTextView.setText(getWeekDays(startFromMonday)[position]);
        return view;
    }

    private String[] getWeekDays(boolean startFromMonday) {
        if (startFromMonday) {
            return new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        } else {
            return new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        }
    }
}
