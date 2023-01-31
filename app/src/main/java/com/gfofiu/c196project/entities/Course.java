package com.gfofiu.c196project.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private String termID;
    private String courseTitle;
    private String courseStart;
    private String courseEnd;
    private String mentorsName;
    private String mentorsNumber;
    private String mentorsEmail;

    public Course(int courseID, String termID, String courseTitle, String courseStart, String courseEnd,
                  String mentorsName, String mentorsNumber, String mentorsEmail) {
        this.courseID = courseID;
        this.termID = termID;
        this.courseTitle = courseTitle;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.mentorsName = mentorsName;
        this.mentorsNumber = mentorsNumber;
        this.mentorsEmail = mentorsEmail;
    }

    public Course() {
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getTermID() {
        return termID;
    }

    public void setTermID(String termID) {
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

    public String getMentorsName() {
        return mentorsName;
    }

    public void setMentorsName(String mentorsName) {
        this.mentorsName = mentorsName;
    }

    public String getMentorsNumber() {
        return mentorsNumber;
    }

    public void setMentorsNumber(String mentorsNumber) {
        this.mentorsNumber = mentorsNumber;
    }

    public String getMentorsEmail() {
        return mentorsEmail;
    }

    public void setMentorsEmail(String mentorsEmail) {
        this.mentorsEmail = mentorsEmail;
    }
}
