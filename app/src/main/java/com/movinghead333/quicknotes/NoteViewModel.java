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

    public void deleteNoteById(long id){
        noteRepository.deleteNoteById(id);
    }

    LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    public void insertNote(Note note){
        noteRepository.insertNote(note);
    }
}
