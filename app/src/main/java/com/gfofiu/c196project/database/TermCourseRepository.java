package com.gfofiu.c196project.database;

import android.app.Application;

import com.gfofiu.c196project.dao.AssessmentsDAO;
import com.gfofiu.c196project.dao.CourseDAO;
import com.gfofiu.c196project.dao.TermDAO;
import com.gfofiu.c196project.entities.Assessments;
import com.gfofiu.c196project.entities.Course;
import com.gfofiu.c196project.entities.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TermCourseRepository {
    private final TermDAO mTermDAO;
    private final CourseDAO mCourseDAO;
    private final AssessmentsDAO mCourseAssessmentsDAO;
    private List<Term> mAllTerms;
    private List<Course> mAllCourse;
    private List<Assessments> mAllCourseAssessments;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public TermCourseRepository(Application application) {
        TermCourseBuilder db = TermCourseBuilder.getDatabase(application);
        mCourseDAO = db.courseDAO();
        mTermDAO = db.termDAO();
        mCourseAssessmentsDAO = db.assessmentsDAO();
    }

    public List<Term> getAllTerms() {
        databaseExecutor.execute(() -> {
            mAllTerms = mTermDAO.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }

    public void insert(Term term) {
        databaseExecutor.execute(() -> {
            mTermDAO.insert(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Term term) {
        databaseExecutor.execute(() -> {
            mTermDAO.update(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Term term) {
        databaseExecutor.execute(() -> {
            mTermDAO.delete(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Course> getAllCourse() {
        databaseExecutor.execute(() -> {
            mAllCourse = mCourseDAO.getAllCourse();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourse;
    }

    public void insert(Course course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.insert(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Course course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.update(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Course course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.delete(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Assessments> getAllAssessments() {
        databaseExecutor.execute(() -> {
            mAllCourseAssessments = mCourseAssessmentsDAO.getCourseAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourseAssessments;
    }

    public void insert(Assessments assessments) {
        databaseExecutor.execute(() -> {
            mCourseAssessmentsDAO.insert(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Assessments assessments) {
        databaseExecutor.execute(() -> {
            mCourseAssessmentsDAO.update(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Assessments assessments) {
        databaseExecutor.execute(() -> {
            mCourseAssessmentsDAO.delete(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
