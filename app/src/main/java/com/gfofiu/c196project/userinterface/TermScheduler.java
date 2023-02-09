package com.gfofiu.c196project.userinterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gfofiu.c196project.R;
import com.gfofiu.c196project.database.TermCourseRepository;
import com.gfofiu.c196project.entities.Course;
import com.gfofiu.c196project.entities.Term;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TermScheduler extends AppCompatActivity {

    private TermCourseRepository termCourseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_scheduler);
        RecyclerView recyclerView = findViewById(R.id.termrecyclerview);
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termCourseRepository = new TermCourseRepository(getApplication());
        List<Term> allTerms = termCourseRepository.getAllTerms();
        Button button = findViewById(R.id.termAddButton);
        termAdapter.setTerms(allTerms);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermScheduler.this, TermDetails.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Term> allTerms = termCourseRepository.getAllTerms();
        RecyclerView recyclerView = findViewById(R.id.termrecyclerview);
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(allTerms);
    }
}