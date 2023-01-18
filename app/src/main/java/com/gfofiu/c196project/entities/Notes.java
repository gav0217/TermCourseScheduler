package com.gfofiu.c196project.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    private int noteId;
    private int courseID;
    private String noteName;
    private String noteContent;

    public Notes(int noteId, int courseID, String noteName, String noteContent) {
        this.noteId = noteId;
        this.courseID = courseID;
        this.noteName = noteName;
        this.noteContent = noteContent;
    }

    public Notes() {
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}
