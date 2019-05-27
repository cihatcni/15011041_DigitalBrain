package com.cihatcni.a15011041_digitalbrain;

import android.location.Location;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note implements Serializable {
    private String title;
    private String context;
    private String color;
    private Date date;
    private Location location;
    private int priority;
    private boolean isNotifSetted;

    public Note(String title, String context, String color, Date date) {
        this.title = title;
        this.context = context;
        this.color = color;
        this.date = date;
        this.isNotifSetted = false;
        this.priority = 0;
        this.location = null;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isNotifSetted() {
        return isNotifSetted;
    }

    public void setNotifSetted(boolean notifSetted) {
        isNotifSetted = notifSetted;
    }
}
