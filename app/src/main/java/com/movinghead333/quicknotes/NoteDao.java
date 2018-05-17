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

    //no conflict stratgey necessary as there is a unique id for every entry
    @Insert/*(onConflict = OnConflictStrategy.ABORT)*/
    public void insertNote(Note note);

    @Delete
    public void delete(Note note);

    @Query("DELETE FROM note_table")
    void clearNotes();

    @Query("SELECT * FROM note_table WHERE title LIKE :noteName LIMIT 1")
    public LiveData<Note> getNoteByTitle(String noteName);

    @Query("SELECT * FROM note_table")
    public LiveData<List<Note>> getAllNotes();
}
