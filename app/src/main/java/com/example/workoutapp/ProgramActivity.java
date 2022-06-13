package com.example.workoutapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ProgramActivity extends AppCompatActivity implements OnClickListener {

    private ArrayList<ExercisesList> exercisesLists;
    private RecyclerView recyclerView;
    FirebaseFirestore db;
    WorkoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        recyclerView = findViewById(R.id.recyclerView);
        exercisesLists = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        adapter = new WorkoutAdapter(exercisesLists, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

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


    }

    private void setAdapter() {
        WorkoutAdapter adapter = new WorkoutAdapter(exercisesLists, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setFatLoss() {

    }

    private void setCalisthenics() {

        db.collection("musclebuilding")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){
                                exercisesLists.add(dc.getDocument().toObject(ExercisesList.class));
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

        /*exercisesLists.add(new ExercisesList("Monday", "HIIT Cardio I"));
        exercisesLists.add(new ExercisesList("Rest", "Rest"));
        exercisesLists.add(new ExercisesList("Wednesday", "HIIT Cardio II"));
        exercisesLists.add(new ExercisesList("Rest", "Rest"));
        exercisesLists.add(new ExercisesList("Friday", "HIIT Cardio III"));*/
    }

    private void setYoga() {

    }

    private void setCardio() {

    }

    private void setStrength() {

    }

    private void setMuscleBuilding() {

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ProgramActivity.this,WorkoutDetailsActivity.class);
        startActivity(intent);
    }
}