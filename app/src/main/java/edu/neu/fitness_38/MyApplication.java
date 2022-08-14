package edu.neu.fitness_38;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyApplication extends Application {
    private static List<ReminderObj> reminderList = new ArrayList<>();
    private static int nextId = 0;

    public MyApplication() {
        fillReminderList();
    }

    private void fillReminderList() {
//        reminderList.add(new ReminderObj("play basketball", "Aug20,2022", "19:34"));
//        reminderList.add(new ReminderObj("play basketball", "Aug20,2022", "19:34"));
//        reminderList.add(new ReminderObj("play basketball", "Aug20,2022", "19:34"));
//        reminderList.add(new ReminderObj("play basketball", "Aug20,2022", "19:34"));
//        reminderList.add(new ReminderObj("play basketball", "Aug20,2022", "19:34"));
//        reminderList.add(new ReminderObj("play basketball", "Aug20,2022", "19:34"));

    }

    public static List<ReminderObj> getReminderList() {
        return reminderList;
    }

    public static void setReminderList(List<ReminderObj> reminderList) {
        MyApplication.reminderList = reminderList;
    }
    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        MyApplication.nextId = nextId;
    }
}