package com.cihatcni.a15011041_digitalbrain;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainListViewAdapter mainListViewAdapter;
    ListView mainView;
    Activity activity;
    ArrayList<Note> noteList;
    Drawable notifActive, notifPassive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainView = findViewById(R.id.mainListView);
        activity = this;
        notifActive = getDrawable(R.drawable.notif);
        notifPassive = getDrawable(R.drawable.notifblack);
        NoteManager.getInstance().setContext(this);
        NoteManager.getInstance().getInformationsFromFile();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addNote = new Intent(getApplicationContext(),AddNoteActivity.class);
                startActivity(addNote);
            }
        });

        mainView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent noteInfo = new Intent(getApplicationContext(),AddNoteActivity.class);
                noteInfo.putExtra("selectedNotePosition", position);
                startActivity(noteInfo);
            }
        });
    }

    //Sol üst menü
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Sol üst menü
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAdapter();
    }

    void updateAdapter() {
        ArrayList<Drawable> notifImages = new ArrayList<>();
        noteList = NoteManager.getInstance().getNoteList();

        for(int i=0 ; i<noteList.size() ; i++)
            if(noteList.get(i).isNotifSetted())
                notifImages.add(notifActive);
            else
                notifImages.add(notifPassive);

        mainListViewAdapter = new MainListViewAdapter(activity,NoteManager.getInstance().getNoteTitles(),notifImages);
        mainView.setAdapter(mainListViewAdapter);

    }
}
