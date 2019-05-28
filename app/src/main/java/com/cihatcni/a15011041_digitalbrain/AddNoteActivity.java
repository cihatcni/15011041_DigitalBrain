package com.cihatcni.a15011041_digitalbrain;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    TextView contentText;
    TextView noteTitle;
    ConstraintLayout layout;
    Note note;
    int position;
    String NOTE_COLOR = "#A7FFEB";
    AlarmManager alarmMgr;
    PendingIntent pendingIntent;
    Calendar noteNotifTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        contentText = findViewById(R.id.contentText);
        noteTitle = findViewById(R.id.titleText);
        layout = findViewById(R.id.noteEditLayout);
        Intent intent = getIntent();


        position = intent.getIntExtra("selectedNotePosition",-1);
        if(position!=-1) {
            note = NoteManager.getInstance().getNoteList().get(position);
            noteTitle.setText(note.getTitle());
            contentText.setText(note.getContext());
            NOTE_COLOR = note.getColor();
            layout.setBackgroundColor(Color.parseColor(note.getColor()));
        }

    }

    public void addNotification(View view) {
        Toast.makeText(this, "Alarm Ayarlandı.", Toast.LENGTH_SHORT).show();
        noteNotifTime = Calendar.getInstance();
        noteNotifTime.add(Calendar.SECOND,15);
    }

    public void addDocument(View view) {
    }

    public void addLocation(View view) {
    }

    public void addMedia(View view) {
    }

    public void saveNote(View view) {
        Date date = Calendar.getInstance().getTime();

        if(position==-1) {
            note = new Note(noteTitle.getText().toString(), contentText.getText().toString(),NOTE_COLOR, date);
            NoteManager.getInstance().noteList.add(note);
        }
        else {
            note.setContext(contentText.getText().toString());
            note.setDate(date);
            note.setColor(NOTE_COLOR);
        }


        if(noteTitle.getText().toString().equals(""))
            note.setTitle("Başlıksız Not");
        else
            note.setTitle(noteTitle.getText().toString());

        if(noteNotifTime!=null) {
            startAlarmManager(noteNotifTime);
            note.setNotifSetted(true);
        }

        Toast.makeText(this, "Not kaydediliyor.", Toast.LENGTH_SHORT).show();
        NoteManager.getInstance().saveInformationsToFile();

        finish();
    }

    public void deleteNote(View view) {
        if(position!=-1)
            NoteManager.getInstance().noteList.remove(position);
        finish();
    }

    public void setColor(View view) {
        final String selectedColor;
        final String[] colors =
                {"#A7FFEB","#f44336","#e91e63","#9c27b0","#2196f3","#4caf50","#ffff00","#795548"};
        final CharSequence[] items =
                {"Teal","Kırmızı","Pembe","Mor","Mavi","Yeşil","Sarı","Kahverengi"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Not Rengini Seçiniz : ");
        builder.setCancelable(false);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                NOTE_COLOR = colors[i];
                layout.setBackgroundColor(Color.parseColor(colors[i]));
            }
        });

        builder.create().show();

    }

    public void setPriority(View view) {
    }

    void startAlarmManager(Calendar alarmCalendar)
    {
        Intent dialogIntent = new Intent(this, AlarmReceiver.class);

        alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 100, dialogIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmMgr.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmCalendar.getTimeInMillis(), pendingIntent);
        System.out.println("ALARM KURULDU........................................................");
    }
}
