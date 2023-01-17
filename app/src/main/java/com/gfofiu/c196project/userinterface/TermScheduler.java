package com.gfofiu.c196project.userinterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.gfofiu.c196project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TermScheduler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_scheduler);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermScheduler.this, TermDetails.class);
                startActivity(intent);
            }
        });
    }
}