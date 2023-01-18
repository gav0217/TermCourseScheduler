package com.gfofiu.c196project.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessments")
public class Assessments {
    @PrimaryKey(autoGenerate = true)
    private int assessmentId;
    private int courseID;
    private String assessmentName;
    private int type;
    private String assessmentDue;
    private boolean alert;

    public Assessments(int assessmentId, int courseID, String assessmentName, int type, String assessmentDue, boolean alert) {
        this.assessmentId = assessmentId;
        this.courseID = courseID;
        this.assessmentName = assessmentName;
        this.type = type;
        this.assessmentDue = assessmentDue;
        this.alert = alert;
    }

    public Assessments() {
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAssessmentDue() {
        return assessmentDue;
    }

    public void setAssessmentDue(String assessmentDue) {
        this.assessmentDue = assessmentDue;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }
}
