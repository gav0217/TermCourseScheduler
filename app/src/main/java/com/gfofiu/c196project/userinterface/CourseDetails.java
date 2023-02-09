package com.gfofiu.c196project.userinterface;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gfofiu.c196project.R;
import com.gfofiu.c196project.database.TermCourseRepository;
import com.gfofiu.c196project.entities.Assessments;
import com.gfofiu.c196project.entities.Course;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CourseDetails extends AppCompatActivity {

    EditText editCourseName;
    EditText editDate;
    EditText editDateB;
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarStart = Calendar.getInstance();
    EditText editMentor;
    EditText editMentorPhone;
    EditText editMentorEmail;
    EditText editNote;

    int courseID;
    String courseName;
    String courseStart;
    String courseEnd;
    String mentor;
    String mentorPhone;
    String mentorEmail;
    String note;
    int termId;
    Course course;
    Button button;
    String status;
    TermCourseRepository termCourseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        editCourseName = findViewById(R.id.courseName);
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editDate = findViewById(R.id.courseStart);
        editDateB = findViewById(R.id.courseEnd);
        editMentor = findViewById(R.id.mentorName);
        editMentorPhone = findViewById(R.id.mentorPhone);
        editMentorEmail = findViewById(R.id.mentorEmail);
        editNote = findViewById(R.id.editnote);
        courseID = getIntent().getIntExtra("courseID", -1);
        termId = getIntent().getIntExtra("termID", -1);
        courseName = getIntent().getStringExtra("course");
        courseStart = getIntent().getStringExtra("course start");
        courseEnd = getIntent().getStringExtra("course end");
        mentor = getIntent().getStringExtra("mentor name");
        mentorPhone = getIntent().getStringExtra("mentor phone");
        mentorEmail = getIntent().getStringExtra("mentor email");
        status = getIntent().getStringExtra("status");
        note = getIntent().getStringExtra("note");
        if (courseID == -1) {
            editDate.setText(sdf.format(new Date()));
            editDateB.setText(sdf.format(new Date()));
        } else {
            editDate.setText(courseStart);
            editDateB.setText(courseEnd);
        }
        editCourseName.setText(courseName);
        editMentor.setText(mentor);
        editMentorPhone.setText(mentorPhone);
        editMentorEmail.setText(mentorEmail);
        editNote.setText(note);
        termCourseRepository = new TermCourseRepository(getApplication());
        RecyclerView recyclerView = findViewById(R.id.assessmentslist);
        termCourseRepository = new TermCourseRepository(getApplication());
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Assessments> filteredAssessment = new ArrayList<>();
        for (Assessments p : termCourseRepository.getAllAssessments()) {
            if (p.getCourseID() == courseID) filteredAssessment.add(p);
        }
        assessmentAdapter.setAssessments(filteredAssessment);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        int position = 0;
        if(status == null) status = "In Progress";
        switch (status) {
            case "In Progress":
                position = 0;
                break;
            case "Completed":
                position = 1;
                break;
            case "Dropped":
                position = 2;
                break;
            case "Plan to Take":
                position = 3;
                break;
        }
        spinner.setSelection(position);
        button = findViewById(R.id.saveCourse);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (courseID == -1) {
                    course = new Course(0, termId, editCourseName.getText().toString(), editDate.getText().toString(),
                            editDateB.getText().toString(), editMentor.getText().toString(), editMentorPhone.getText().toString(),
                            editMentorEmail.getText().toString(), spinner.getSelectedItem().toString(), editNote.getText().toString());

                    termCourseRepository.insert(course);
                    finish();
                } else {
                    course = new Course(courseID, termId, editCourseName.getText().toString(), editDate.getText().toString(),
                            editDateB.getText().toString(), editMentor.getText().toString(), editMentorPhone.getText().toString(),
                            editMentorEmail.getText().toString(), spinner.getSelectedItem().toString(), editNote.getText().toString());
                    termCourseRepository.update(course);
                    finish();
                }
            }
        });

        button = findViewById(R.id.assessments);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseDetails.this, AssessmentDetails.class);
                intent.putExtra("courseID", courseID);
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
                new DatePickerDialog(CourseDetails.this, startDate, myCalendarStart
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
                new DatePickerDialog(CourseDetails.this, endDate, myCalendarStart
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course_details, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        String dateFromScreen;
        String myFormat;
        Date myDate;
        AlarmManager alarmManager;
        SimpleDateFormat sdf;
        Long trigger;
        Intent intent;
        PendingIntent sender;

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, editNote.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Message Title");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            case R.id.notifystart:
                dateFromScreen = editDate.getText().toString();
                myFormat = "MM/dd/yy"; //In which you need put here
                sdf = new SimpleDateFormat(myFormat, Locale.US);
                myDate = null;
                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                trigger = myDate.getTime();
                intent = new Intent(CourseDetails.this, MyNewReceiver.class);
                intent.putExtra("key", dateFromScreen + " Course Start Date");
                sender = PendingIntent.getBroadcast(CourseDetails.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.notifyend:
                dateFromScreen = editDateB.getText().toString();
                myFormat = "MM/dd/yy"; //In which you need put here
                sdf = new SimpleDateFormat(myFormat, Locale.US);
                myDate = null;
                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                trigger = myDate.getTime();
                intent = new Intent(CourseDetails.this, MyNewReceiver.class);
                intent.putExtra("key", dateFromScreen + " Course End Date");
                sender = PendingIntent.getBroadcast(CourseDetails.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.deletethecourse:
                for (Course cou : termCourseRepository.getAllCourse()) {
                    if (cou.getCourseID() == courseID) {
                        termCourseRepository.delete(cou);
                        finish();
                    }
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.assessmentslist);
        termCourseRepository = new TermCourseRepository(getApplication());
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Assessments> filteredAssessment = new ArrayList<>();
        for (Assessments p : termCourseRepository.getAllAssessments()) {
            if (p.getCourseID() == courseID) filteredAssessment.add(p);
        }
        assessmentAdapter.setAssessments(filteredAssessment);

        //Toast.makeText(ProductDetails.this,"refresh list",Toast.LENGTH_LONG).show();
    }
}
