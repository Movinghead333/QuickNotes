package com.movinghead333.quicknotes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel{

    private NoteRepository noteRepository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(Application application){
        super(application);

        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();

    }

    LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    public void insertNote(Note note){
        noteRepository.insertNote(note);
    }

    public Note getNoteByTitle(String title){
        return noteRepository.getNoteByTitle(title).getValue();
    }
}