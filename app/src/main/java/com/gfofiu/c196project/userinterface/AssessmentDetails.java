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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.gfofiu.c196project.R;
import com.gfofiu.c196project.database.TermCourseRepository;
import com.gfofiu.c196project.entities.Assessments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AssessmentDetails extends AppCompatActivity {

    EditText editAssessment;
    EditText editDueDate;
    EditText editStartDate;
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarStart = Calendar.getInstance();
    int numAssessment;
    int assessmentID;
    int courseID;
    Assessments currentAssessment;
    String assessmentName;
    String assessmentStart;
    String assessmentEnd;
    Assessments assessments;
    TermCourseRepository termCourseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assessment_details);
        editAssessment = findViewById(R.id.assessmentNameDetails);
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editStartDate = findViewById(R.id.assessmentStartDetails);
        editDueDate = findViewById(R.id.assessmentEndDetails);
        assessmentID = getIntent().getIntExtra("assessmentID", -1);
        courseID = getIntent().getIntExtra("courseID", -1);
        assessmentName = getIntent().getStringExtra("assessmentName");
        assessmentStart = getIntent().getStringExtra("assessmentStart");
        assessmentEnd = getIntent().getStringExtra("assessmentEnd");
        if (assessmentID == -1) {
            editStartDate.setText(sdf.format(new Date()));
            editDueDate.setText(sdf.format(new Date()));
        } else {
            editStartDate.setText(assessmentStart);
            editDueDate.setText(assessmentEnd);
        }
        editAssessment.setText(assessmentName);
        Button button = findViewById(R.id.saveAssessment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                termCourseRepository = new TermCourseRepository(getApplication());
                if (assessmentID == -1) {
                    assessments = new Assessments(0, courseID, editAssessment.getText().toString(), editStartDate.getText().toString(), editDueDate.getText().toString());
                    termCourseRepository.insert(assessments);
                    finish();
                } else {
                    assessments = new Assessments(assessmentID, courseID, editAssessment.getText().toString(), editStartDate.getText().toString(), editDueDate.getText().toString());
                    termCourseRepository.update(assessments);
                    finish();
                }
            }
        });

        editStartDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date date;
                //get value from other screen,but I'm going to hard code it right now
                String info = editStartDate.getText().toString();
                if (info.equals("")) info = "02/10/22";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(AssessmentDetails.this, startDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editDueDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date date;
                //get value from other screen,but I'm going to hard code it right now
                String info = editDueDate.getText().toString();
                if (info.equals("")) info = "02/10/22";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(AssessmentDetails.this, endDate, myCalendarStart
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

        editStartDate.setText(sdf.format(myCalendarStart.getTime()));
    }

    private void updateLabelEnd() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editDueDate.setText(sdf.format(myCalendarStart.getTime()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.assessments_menu, menu);
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
        termCourseRepository = new TermCourseRepository(getApplication());

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.notifyStartAssessment:
                dateFromScreen = editStartDate.getText().toString();
                myFormat = "MM/dd/yy"; //In which you need put here
                sdf = new SimpleDateFormat(myFormat, Locale.US);
                myDate = null;
                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                trigger = myDate.getTime();
                intent = new Intent(AssessmentDetails.this, MyNewReceiver.class);
                intent.putExtra("key", dateFromScreen + " Assessment Start");
                sender = PendingIntent.getBroadcast(AssessmentDetails.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.notifyEndAssessment:
                dateFromScreen = editDueDate.getText().toString();
                myFormat = "MM/dd/yy"; //In which you need put here
                sdf = new SimpleDateFormat(myFormat, Locale.US);
                myDate = null;
                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                trigger = myDate.getTime();
                intent = new Intent(AssessmentDetails.this, MyNewReceiver.class);
                intent.putExtra("key", dateFromScreen + " Assessment Due");
                sender = PendingIntent.getBroadcast(AssessmentDetails.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.deleteAssessment:
                for (Assessments assess : termCourseRepository.getAllAssessments()) {
                    if (assess.getAssessmentID() == assessmentID) {
                        termCourseRepository.delete(assess);
                        finish();
                    }
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



