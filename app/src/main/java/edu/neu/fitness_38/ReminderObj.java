package edu.neu.fitness_38;

import java.util.UUID;

public class ReminderObj {
    public int id;
    public String description;
    public String date;
    public String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ReminderObj() {
        id = 0;
        description = "";
        date = "";
        time = "";
    }

    public ReminderObj(int id, String description,  String date,
                       String time) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.time = time;

    }
}