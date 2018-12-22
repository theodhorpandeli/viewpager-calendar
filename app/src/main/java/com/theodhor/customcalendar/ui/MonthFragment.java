package com.theodhor.customcalendar.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.theodhor.customcalendar.CalendarApplication;
import com.theodhor.customcalendar.R;
import com.theodhor.customcalendar.adapter.MonthAdapter;
import com.theodhor.customcalendar.adapter.WeekAdapter;
import com.theodhor.customcalendar.utils.Constants;

public class MonthFragment extends Fragment {

    public static MonthFragment newInstance(int nextMonthIndex) {
        MonthFragment monthFragment = new MonthFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.NEXT_MONTH_INDEX, nextMonthIndex);
        monthFragment.setArguments(args);
        return monthFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.month_fragment, container, false);

        int nextMonthIndex = getArguments().getInt(Constants.NEXT_MONTH_INDEX);

        TextView monthName = root.findViewById(R.id.month_name);

        GridView weekDaysGridView = root.findViewById(R.id.week_days_grid_view);

        WeekAdapter weekAdapter = new WeekAdapter(CalendarApplication.get().startFromMonday());
        weekDaysGridView.setAdapter(weekAdapter);

        GridView monthGridView = root.findViewById(R.id.month_grid_view);
        MonthAdapter monthAdapter = new MonthAdapter(getContext(), nextMonthIndex);
        monthGridView.setAdapter(monthAdapter);
        monthName.setText(monthAdapter.getCurrentMonthYear());
        return root;
    }
}
