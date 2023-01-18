package com.gfofiu.c196project.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.gfofiu.c196project.entities.Assessments;
import com.gfofiu.c196project.entities.Notes;
import com.gfofiu.c196project.entities.Term;

import java.util.List;

@Dao
public interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Notes notes);

    @Update
    void update(Notes notes);

    @Delete
    void delete(Notes notes);

    @Query("SELECT * FROM assessments WHERE courseID= :courseID ORDER BY courseID ASC")
    List<Assessments> getCourseNotes(int courseID);
}
