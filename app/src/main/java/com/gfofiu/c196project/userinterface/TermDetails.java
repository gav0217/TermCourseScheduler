package com.gfofiu.c196project.userinterface;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gfofiu.c196project.R;
import com.gfofiu.c196project.database.TermCourseRepository;
import com.gfofiu.c196project.entities.Course;
import com.gfofiu.c196project.entities.Term;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TermDetails extends AppCompatActivity {

    EditText editTerm;
    EditText editDate;
    EditText editDateB;
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarStart = Calendar.getInstance();

    int termID;
    int numCourse;
    Term currentTerm;
    String termTitle;
    String termStart;
    String termEnd;
    Term term;
    TermCourseRepository termCourseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);
        editTerm = findViewById(R.id.termname);
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editDate = findViewById(R.id.termstart);
        editDateB = findViewById(R.id.termend);
        termID = getIntent().getIntExtra("termID", -1);
        termTitle = getIntent().getStringExtra("term");
        termStart = getIntent().getStringExtra("term start");
        termEnd = getIntent().getStringExtra("term end");
        if(termID==-1) {
            editDate.setText(sdf.format(new Date()));
            editDateB.setText(sdf.format(new Date()));
        }else {
            editDate.setText(termStart);
            editDateB.setText(termEnd);
        }
        editTerm.setText(termTitle);
        termCourseRepository = new TermCourseRepository(getApplication());
        RecyclerView recyclerView = findViewById(R.id.courserecyclerview);
        termCourseRepository = new TermCourseRepository(getApplication());
        final CourseAdapter courseAdopter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdopter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Course> filteredCourse = new ArrayList<>();
        for (Course p : termCourseRepository.getAllCourse()) {
            if (p.getTermID() == termID) filteredCourse.add(p);
        }
        courseAdopter.setCourse(filteredCourse);
        Button button = findViewById(R.id.saveterm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (termID == -1) {
                    term = new Term(0, editTerm.getText().toString(), editDate.getText().toString(), editDateB.getText().toString());
                    termCourseRepository.insert(term);
                    Intent intent = new Intent(TermDetails.this, TermScheduler.class);
                    startActivity(intent);
                } else {
                    term = new Term(termID, editTerm.getText().toString(), editDate.getText().toString(), editDateB.getText().toString());
                    termCourseRepository.update(term);
                    Intent intent = new Intent(TermDetails.this, TermScheduler.class);
                    startActivity(intent);
                }
            }
        });

        button = findViewById(R.id.coursesbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermDetails.this, CourseDetails.class);
                intent.putExtra("termID", termID);
                startActivity(intent);
            }
        });

        editDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date date;
                //get value from other screen,but I'm going to hard code it right now
                String info = editDate.getText().toString();
                if (info.equals("")) info = "02/10/22";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(TermDetails.this, startDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editDateB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date date;
                //get value from other screen,but I'm going to hard code it right now
                String info = editDateB.getText().toString();
                if (info.equals("")) info = "02/10/22";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(TermDetails.this, endDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        startDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabelStart();
            }

        };

        endDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabelEnd();
            }
        };
    }

    private void updateLabelStart() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editDate.setText(sdf.format(myCalendarStart.getTime()));
    }

    private void updateLabelEnd() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editDateB.setText(sdf.format(myCalendarStart.getTime()));
    }

    @Override
    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.courserecyclerview);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Course> filteredCourse = new ArrayList<>();
        for (Course p : termCourseRepository.getAllCourse()) {
            if (p.getTermID() == termID) filteredCourse.add(p);
        }
        courseAdapter.setCourse(filteredCourse);

        //Toast.makeText(ProductDetails.this,"refresh list",Toast.LENGTH_LONG).show();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_course, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deletecourse:
                for (Term ter : termCourseRepository.getAllTerms()) {
                    if (ter.getTermID() == termID) currentTerm = ter;
                }

                numCourse = 0;
                for (Course course : termCourseRepository.getAllCourse()) {
                    if (course.getTermID() == termID) ++numCourse;
                }

                if (numCourse == 0) {
                    termCourseRepository.delete(currentTerm);
                    Toast.makeText(TermDetails.this, currentTerm.getTermTitle() + " was deleted", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(TermDetails.this, "Can't delete a Term with Courses", Toast.LENGTH_LONG).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

