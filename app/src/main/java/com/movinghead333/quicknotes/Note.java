package com.movinghead333.quicknotes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    public String noteTitle;

    @ColumnInfo(name = "description")
    public String description;

    public Note(@NonNull String noteTitle,  String description){
        this.noteTitle = noteTitle;
        this.description = description;
    }
}
