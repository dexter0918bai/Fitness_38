package edu.neu.fitness_38;

import java.util.UUID;

public class ReminderObj {
    public String id;
    public String description;
    public String date;
    public String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        id = UUID.randomUUID().toString();
        description = "";
        date = "";
        time = "";
    }

    public ReminderObj(String description,  String date,
                    String time) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.date = date;
        this.time = time;

    }
}