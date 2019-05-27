package com.cihatcni.a15011041_digitalbrain;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NoteManager {
    private static final NoteManager ourInstance = new NoteManager();

    public static NoteManager getInstance() {
        return ourInstance;
    }

    ArrayList<Note> noteList;
    Context context;


    private NoteManager() {
        if(noteList==null)
            noteList = new ArrayList<>();
    }

    void addNoteToList(Note note) {
        noteList.add(note);
    }

    void deleteNoteFromList(Note note) {
        noteList.remove(note);
    }

    ArrayList<Note> getNoteList() {
        return noteList;
    }

    ArrayList<String> getNoteTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for(int i=0 ; i<noteList.size() ; i++)
            titles.add(noteList.get(i).getTitle());
        return titles;
    }

    ArrayList<String> getNoteTimes() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ArrayList<String> times = new ArrayList<>();
        for(int i=0 ; i<noteList.size() ; i++)
            times.add(dateFormat.format(noteList.get(i).getDate()));
        return times;
    }

    ArrayList<Boolean> getNoteNotifSets() {
        ArrayList<Boolean> notifs = new ArrayList<>();
        for(int i=0 ; i<noteList.size() ; i++)
            notifs.add(noteList.get(i).isNotifSetted());
        return notifs;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    void saveInformationsToFile() {

        try {
            FileOutputStream fos = new FileOutputStream(context.getFilesDir()+ "/noteList.dat");
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(noteList);
            outputStream.close();
            System.out.println("BİLGİLER DOSYAYA KAYDEDİLDİ.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void getInformationsFromFile() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(context.getFilesDir()+ "/noteList.dat"));
            this.noteList = (ArrayList<Note>) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println("BİLGİLER DOSYAYA KAYDEDİLDİ");
        } catch (Exception e) {
            e.printStackTrace();
            saveInformationsToFile();
            getInformationsFromFile();
        }
    }




}
