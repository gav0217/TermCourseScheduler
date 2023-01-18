package com.gfofiu.c196project.userinterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.gfofiu.c196project.R;
import com.gfofiu.c196project.database.TermCourseRepository;
import com.gfofiu.c196project.entities.Course;
import com.gfofiu.c196project.entities.Term;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.frontpagebutton);
        //Adding Test Data
        //Term term= new Term(0, "History", "01/01/2023","01/30/2023");
        //TermCourseRepository termCourseRepository = new TermCourseRepository(getApplication());
        //termCourseRepository.insert(term);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TermScheduler.class);
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addTestData:
                Term Term = new Term (0, "HistoryTerm", "01/01/2023", "01/31/2023");
                TermCourseRepository termCourseRepository = new TermCourseRepository(getApplication());
                termCourseRepository.insert(Term);
                Course course = new Course(0, 1, "MathCourse", "01/01/2023", "01/31/2023", true, 1,
                        "Gavril Fofiu", "442-600-9686", "gav217@wgu.edu");
                termCourseRepository.insert(course);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}