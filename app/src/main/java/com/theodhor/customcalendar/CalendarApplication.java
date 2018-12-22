package com.theodhor.customcalendar;

import android.app.Application;

public class CalendarApplication extends Application {

    public static CalendarApplication instance;
    private boolean startFromMonday;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = new CalendarApplication();
    }

    public static CalendarApplication get() {
        if (null == instance) {
            instance = new CalendarApplication();
        }
        return instance;
    }

    public void setStartFromMonday(boolean startFromMonday) {
        this.startFromMonday = startFromMonday;
    }

    public boolean startFromMonday() {
        return startFromMonday;
    }
}
