package com.theodhor.customcalendar.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.theodhor.customcalendar.CalendarApplication;
import com.theodhor.customcalendar.R;
import com.theodhor.customcalendar.adapter.MonthPagerAdapter;
import com.theodhor.customcalendar.utils.Constants;

public class MainActivity extends AppCompatActivity {

    private MenuItem startFromMondayMenuItem, startFromSundayMenuItem;
    private ViewPager monthViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        CalendarApplication.get().setStartFromMonday(false);

        monthViewPager = findViewById(R.id.months_view_pager);
        monthViewPager.setAdapter(new MonthPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calendar_menu, menu);
        boolean startFromMonday = CalendarApplication.get().startFromMonday();
        startFromMondayMenuItem = menu.findItem(R.id.start_from_monday);
        startFromSundayMenuItem = menu.findItem(R.id.start_from_sunday);

        startFromMondayMenuItem.setVisible(!startFromMonday);
        startFromSundayMenuItem.setVisible(startFromMonday);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedMonthPage = monthViewPager.getCurrentItem();
        switch (item.getItemId()) {
            case R.id.go_to_today:
                moveCalendarToPage(Constants.HOME);
                break;
            case R.id.start_from_monday:
                CalendarApplication.get().setStartFromMonday(true);
                startFromMondayMenuItem.setVisible(false);
                startFromSundayMenuItem.setVisible(true);
                monthViewPager.setAdapter(null);
                monthViewPager.setAdapter(new MonthPagerAdapter(getSupportFragmentManager()));
                moveCalendarToPage(selectedMonthPage);
                break;
            case R.id.start_from_sunday:
                CalendarApplication.get().setStartFromMonday(false);
                startFromSundayMenuItem.setVisible(false);
                startFromMondayMenuItem.setVisible(true);
                monthViewPager.setAdapter(null);
                monthViewPager.setAdapter(new MonthPagerAdapter(getSupportFragmentManager()));
                moveCalendarToPage(selectedMonthPage);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void moveCalendarToPage(int position) {
        int current = monthViewPager.getCurrentItem();
        int sign = current > position ? -1 : 1;
        while (monthViewPager.getCurrentItem() != position) {
            monthViewPager.setCurrentItem(monthViewPager.getCurrentItem() + sign, true);
        }
    }
}
