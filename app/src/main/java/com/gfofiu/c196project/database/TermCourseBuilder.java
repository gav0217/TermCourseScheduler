package com.gfofiu.c196project.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gfofiu.c196project.dao.AssessmentsDAO;
import com.gfofiu.c196project.dao.CourseDAO;
import com.gfofiu.c196project.dao.TermDAO;
import com.gfofiu.c196project.entities.Assessments;
import com.gfofiu.c196project.entities.Course;
import com.gfofiu.c196project.entities.Term;

@Database(entities = {Term.class, Course.class, Assessments.class}, version = 19, exportSchema = false)
public abstract class TermCourseBuilder extends RoomDatabase {
    public abstract TermDAO termDAO();

    public abstract CourseDAO courseDAO();

    public abstract AssessmentsDAO assessmentsDAO();

    private static volatile TermCourseBuilder INSTANCE;

    static TermCourseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TermCourseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TermCourseBuilder.class, "TermCourseBuilder.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }

        }
        return INSTANCE;
    }

}
