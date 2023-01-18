package com.gfofiu.c196project.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.gfofiu.c196project.entities.Course;
import com.gfofiu.c196project.entities.Term;

import java.util.List;

@Dao
public interface CourseDAO   {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Course course);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM course ORDER BY courseID ASC")
    List<Course> getAllCourse();
}

