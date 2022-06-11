package com.example.workoutapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ProgramActivity extends AppCompatActivity implements OnClickListener {

    private ArrayList<ExercisesList> exercisesLists;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        recyclerView = findViewById(R.id.recyclerView);
        exercisesLists = new ArrayList<>();
        Intent intent = getIntent();
        String muscle = intent.getStringExtra("muscle");
        String strength = intent.getStringExtra("strength");
        String cardio = intent.getStringExtra("cardio");
        String yoga = intent.getStringExtra("yoga");
        String calisthenics = intent.getStringExtra("calisthenics");
        String fatLoss = intent.getStringExtra("fatLoss");
        if (muscle != null) {
            setMuscleBuilding();
        } else if (strength != null) {
            setStrength();
        } else if (cardio != null) {
            setCardio();
        } else if (yoga != null) {
            setYoga();
        } else if (calisthenics != null) {
            setCalisthenics();
        } else if (fatLoss != null) {
            setFatLoss();
        }

        setAdapter();
    }

    private void setAdapter() {
        WorkoutAdapter adapter = new WorkoutAdapter(exercisesLists, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setFatLoss() {
        exercisesLists.add(new ExercisesList("Monday", "HIIT Cardio I"));
        exercisesLists.add(new ExercisesList("Rest", "Rest"));
        exercisesLists.add(new ExercisesList("Wednesday", "HIIT Cardio II"));
        exercisesLists.add(new ExercisesList("Rest", "Rest"));
        exercisesLists.add(new ExercisesList("Friday", "HIIT Cardio III"));
    }

    private void setCalisthenics() {
        exercisesLists.add(new ExercisesList("Monday", "HIIT Cardio I"));
        exercisesLists.add(new ExercisesList("Rest", "Rest"));
        exercisesLists.add(new ExercisesList("Wednesday", "HIIT Cardio II"));
        exercisesLists.add(new ExercisesList("Rest", "Rest"));
        exercisesLists.add(new ExercisesList("Friday", "HIIT Cardio III"));
    }

    private void setYoga() {
        exercisesLists.add(new ExercisesList("Day 1", "Yoga I"));
        exercisesLists.add(new ExercisesList("Day 2", "Yoga II"));
        exercisesLists.add(new ExercisesList("Day 3", "Yoga III"));
        exercisesLists.add(new ExercisesList("Day 4", "Yoga IV"));
        exercisesLists.add(new ExercisesList("Day 5", "Rest Day"));
        exercisesLists.add(new ExercisesList("Day 6", "Yoga V"));
        exercisesLists.add(new ExercisesList("Day 7", "Yoga VI"));
        exercisesLists.add(new ExercisesList("Day 8", "Yoga VII"));
        exercisesLists.add(new ExercisesList("Day 9", "Yoga VIII"));
        exercisesLists.add(new ExercisesList("Day 10", "Yoga IX"));
    }

    private void setCardio() {
        exercisesLists.add(new ExercisesList("Monday", "HIIT Cardio I"));
        exercisesLists.add(new ExercisesList("Rest", "Rest"));
        exercisesLists.add(new ExercisesList("Wednesday", "HIIT Cardio II"));
        exercisesLists.add(new ExercisesList("Rest", "Rest"));
        exercisesLists.add(new ExercisesList("Friday", "HIIT Cardio III"));
    }

    private void setStrength() {
        exercisesLists.add(new ExercisesList("Day 1", "Lower Strength"));
        exercisesLists.add(new ExercisesList("Day 2", "Upper Strength"));
        exercisesLists.add(new ExercisesList("Day 3", "Rest Day"));
        exercisesLists.add(new ExercisesList("Day 4", "Lower Hypertrophy"));
        exercisesLists.add(new ExercisesList("Day 5", "Upper Hypertrophy"));
    }

    private void setMuscleBuilding() {
        exercisesLists.add(new ExercisesList("Monday", "Chest and Triceps"));
        exercisesLists.add(new ExercisesList("Tuesday", "Back and Biceps"));
        exercisesLists.add(new ExercisesList("Wednesday", "Rest Day/Cardio"));
        exercisesLists.add(new ExercisesList("Thursday", "Shoulders and Forearms"));
        exercisesLists.add(new ExercisesList("Friday", "Legs"));
        exercisesLists.add(new ExercisesList("Saturday", "Rest Day/Cardio"));
        exercisesLists.add(new ExercisesList("Sunday", "Rest"));
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ProgramActivity.this,WorkoutDetailsActivity.class);
        startActivity(intent);
    }
}