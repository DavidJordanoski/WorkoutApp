package com.example.workoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class WorkoutDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details);

        String workoutName = getIntent().getStringExtra("WorkoutName");
        TextView workoutNameTextView = findViewById(R.id.workoutName);
        workoutNameTextView.setText(workoutName);

        String workoutDescription = getIntent().getStringExtra("WorkoutDescription");
        TextView workoutDescriptionTextView = findViewById(R.id.workoutDescription);
        workoutDescriptionTextView.setText(workoutDescription);


    }
}