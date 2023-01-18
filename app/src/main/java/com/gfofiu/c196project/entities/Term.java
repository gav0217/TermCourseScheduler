package com.gfofiu.c196project.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "term")
public class Term {
    @PrimaryKey(autoGenerate = true)
    private int termID;
    private String courseTitle;
    private String courseStart;
    private String courseEnd;

    public Term(int termID, String courseTitle, String courseStart, String courseEnd) {
        this.termID = termID;
        this.courseTitle = courseTitle;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
    }

    public Term() {
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(String courseStart) {
        this.courseStart = courseStart;
    }

    public String getCourseEnd() {
        return courseEnd;
    }

    public void setCourseEnd(String courseEnd) {
        this.courseEnd = courseEnd;
    }
}
