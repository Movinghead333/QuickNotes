package com.movinghead333.quicknotes;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NoteDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    @Query("DELETE FROM note_table WHERE id == :id")
    void delete(long id);

    @Query("SELECT * FROM note_table")
    LiveData<List<Note>> getAllNotes();

    /*
    @Query("DELETE FROM note_table")
    void clearNotes();

    @Query("SELECT * FROM note_table WHERE id == :id")
    public Note getNoteById(long id);

    @Query("SELECT * FROM note_table WHERE title LIKE :noteName LIMIT 1")
    public LiveData<Note> getNoteByTitle(String noteName);
    */

}
