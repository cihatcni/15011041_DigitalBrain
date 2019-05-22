package com.cihatcni.a15011041_digitalbrain;

import android.graphics.Color;
import android.location.Location;

import java.util.Calendar;

public class Note {
    String title;
    Color color;
    Calendar tarih;
    Location location;
    int priority;

    public Note(String title, Color color, Calendar tarih) {
        this.title = title;
        this.color = color;
        this.tarih = tarih;
    }

    

}
