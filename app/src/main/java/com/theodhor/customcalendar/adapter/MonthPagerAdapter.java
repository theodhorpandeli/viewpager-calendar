package com.theodhor.customcalendar.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.theodhor.customcalendar.ui.MonthFragment;

public class MonthPagerAdapter extends FragmentStatePagerAdapter {

    public MonthPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MonthFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

}