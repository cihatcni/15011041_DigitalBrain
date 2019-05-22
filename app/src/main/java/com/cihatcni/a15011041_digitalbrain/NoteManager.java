package com.cihatcni.a15011041_digitalbrain;

import java.util.ArrayList;

public class NoteManager {
    private static final NoteManager ourInstance = new NoteManager();

    public static NoteManager getInstance() {
        return ourInstance;
    }

    ArrayList<Note> noteList;

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


}
