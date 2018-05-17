package com.movinghead333.quicknotes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;
    private LiveData<Note> currentNote;

    NoteRepository(Application application){
        NoteDatabase db = NoteDatabase.getDatabase(application);
        noteDao = db.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public LiveData<Note> getNoteByTitle(String title){
        return noteDao.getNoteByTitle(title);
    }

    public void insertNote(Note note){
        new insertAsyncTaskDao(noteDao).execute(note);
    }

    private static class insertAsyncTaskDao extends AsyncTask<Note, Void, Void> {

        private NoteDao mAsyncTaskDao;

        insertAsyncTaskDao(NoteDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params){
            mAsyncTaskDao.insertNote(params[0]);
            return null;
        }
    }
}
