package com.movinghead333.quicknotes;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

    @Database(entities = {Note.class,}, version = 2)
    public abstract class NoteDatabase extends RoomDatabase {

        private static final String DATABASE_NAME = "note_database";

        private static NoteDatabase INSTANCE;

        public static NoteDatabase getDatabase(final Context context){
            if(INSTANCE == null){
                synchronized (NoteDatabase.class){
                    if(INSTANCE == null){
             INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
        NoteDatabase.class, DATABASE_NAME)
                .addCallback(sNoteDatabaseCallback)
                                //.fallbackToDestructiveMigration()
                                .build();
    }
                }
                        }
                        return INSTANCE;
                        }

        public abstract NoteDao noteDao();

        private static RoomDatabase.Callback sNoteDatabaseCallback =
                new RoomDatabase.Callback(){
                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db){
                        super.onOpen(db);
                        new PopulateDbAsync(INSTANCE).execute();
                    }
                };


        private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

            private final NoteDao noteDao;

            PopulateDbAsync(NoteDatabase db){
                noteDao = db.noteDao();
            }

            @Override
            protected Void doInBackground(final Void... params){
                noteDao.clearNotes();

                Note note = new Note("ÜB", "ingenieurmathematik");
                noteDao.insertNote(note);
                note = new Note("note x", "description");
                noteDao.insertNote(note);
                return null;
            }
        }

    }
