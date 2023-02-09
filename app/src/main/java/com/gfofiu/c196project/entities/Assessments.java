package com.gfofiu.c196project.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessments")
public class Assessments {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private int courseID;
    private String assessmentName;
    private String assessmentStart;
    private String assessmentDue;


    public Assessments(int assessmentID, int courseID, String assessmentName, String assessmentStart, String assessmentDue) {
        this.assessmentID = assessmentID;
        this.courseID = courseID;
        this.assessmentName = assessmentName;
        this.assessmentStart = assessmentStart;
        this.assessmentDue = assessmentDue;
    }

    public Assessments() {
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }


    public String getAssessmentDue() {
        return assessmentDue;
    }

    public void setAssessmentDue(String assessmentDue) {
        this.assessmentDue = assessmentDue;
    }

    public String getAssessmentStart() {
        return assessmentStart;
    }

    public void setAssessmentStart(String assessmentStart) {
        this.assessmentStart = assessmentStart;
    }

}

