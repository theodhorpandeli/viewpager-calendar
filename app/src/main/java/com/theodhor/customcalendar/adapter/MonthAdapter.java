package com.theodhor.customcalendar.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.theodhor.customcalendar.CalendarApplication;
import com.theodhor.customcalendar.R;
import com.theodhor.customcalendar.utils.Constants;

import java.util.Calendar;
import java.util.Locale;

public class MonthAdapter extends BaseAdapter {

    private final String TAG = MonthAdapter.class.getSimpleName();
    private int TODAY = 0, NOT_TODAY = 1;
    private int firstDayOfMonth, lastDayOfMonth;
    private int currentDay = 1, today = 0, currentYear;
    private String currentMonth;
    private Context context;
    private boolean startFromMonday;

    public MonthAdapter(Context context, int indexOfNextMonth) {
        this.context = context;
        startFromMonday = CalendarApplication.get().startFromMonday();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, indexOfNextMonth);
        if (indexOfNextMonth == 0) {
            today = calendar.get(Calendar.DAY_OF_MONTH);
        }
        calendar.set(Calendar.DATE, 1);
        currentMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        currentYear = calendar.get(Calendar.YEAR);
        firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        /*
        * Fixing indexes starting indexes of Week on default Start-from-sunday calendar
        * */
        if (firstDayOfMonth == 0 && !startFromMonday) {
            currentDay = 0;
        }

        /*
        * Fixing week index on Start-from-monday calendar
        * In case of a negative shift, we set the default start
        * */
        if (startFromMonday) {
            firstDayOfMonth--;
            if (firstDayOfMonth == 0) {
                currentDay = 0;
            } else if (firstDayOfMonth <= 0) {
                firstDayOfMonth = 1;
            }
        }
        lastDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int getCount() {
        /*
        * Using the minimum grid size to display 4-weeks
        * */
        return Constants.MAX_GRID_SIZE;
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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.month_day_item, null);
        TextView dayTextView = view.findViewById(R.id.day_text_view);
        if (position >= firstDayOfMonth && currentDay <= lastDayOfMonth) {
            dayTextView.setText(String.valueOf(currentDay));
            if (currentDay == today) {
                view.setBackground(context.getResources().getDrawable(R.drawable.item_today, null));
                dayTextView.setTextColor(context.getResources().getColor(R.color.colorAccent, null));
                dayTextView.setTypeface(dayTextView.getTypeface(), Typeface.BOLD);
            }
            currentDay++;
        }

        return view;
    }

    public String getCurrentMonthYear() {
        return String.format(Locale.getDefault(), "%s %d", currentMonth, currentYear);
    }
}
