package com.cihatcni.a15011041_digitalbrain;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainListViewAdapter extends ArrayAdapter<String> {

    //private final ArrayList<Note> noteList;
    private final ArrayList<Drawable> notifImages;
    private final Activity context;


    public MainListViewAdapter(Activity context, ArrayList<String> noteNames,ArrayList<Drawable> notifImages) {
        super(context, R.layout.activity_main_custom_view,noteNames);
        this.notifImages = notifImages;
        this.context = context;
        //this.noteList = NoteManager.getInstance().getNoteList();

    }


    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View mainListView = layoutInflater.inflate(R.layout.activity_main_custom_view,null,true);

        TextView noteTitle = mainListView.findViewById(R.id.titleTextMainView);
        TextView noteTime = mainListView.findViewById(R.id.timeTextMainView);
        TextView background = mainListView.findViewById(R.id.backgroundTextCustomView);
        ImageView notifImage = mainListView.findViewById(R.id.notifImageCustomView);
        Note note = NoteManager.getInstance().getNoteList().get(position);

        noteTitle.setText(note.getTitle());
        noteTime.setText(note.getDate());
        notifImage.setImageDrawable(notifImages.get(position));
        background.setBackgroundColor(Color.parseColor(note.getColor()));

        return mainListView;
    }
}
