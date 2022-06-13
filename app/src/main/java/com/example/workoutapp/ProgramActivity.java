package com.example.workoutapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        recyclerView = findViewById(R.id.recyclerView);
        exercisesLists = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        toolbar = findViewById(R.id.workoutToolbar);


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

    private void setFatLoss() {
        toolbar.setTitle(R.string.fat_loss_program);
        setSupportActionBar(toolbar);
        db.collection("fatloss")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                exercisesLists.add(dc.getDocument().toObject(ExercisesList.class));
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void setCalisthenics() {
        toolbar.setTitle(R.string.calisthenics_program);
        setSupportActionBar(toolbar);
        db.collection("calisthenics")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                exercisesLists.add(dc.getDocument().toObject(ExercisesList.class));
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void setYoga() {
        toolbar.setTitle(R.string.yoga_program);
        setSupportActionBar(toolbar);
        db.collection("yoga")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                exercisesLists.add(dc.getDocument().toObject(ExercisesList.class));
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void setCardio() {
        toolbar.setTitle(R.string.cardio_program);
        setSupportActionBar(toolbar);
        db.collection("cardio")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                exercisesLists.add(dc.getDocument().toObject(ExercisesList.class));
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void setStrength() {
        toolbar.setTitle(R.string.strength_program);
        setSupportActionBar(toolbar);
        db.collection("strength")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                exercisesLists.add(dc.getDocument().toObject(ExercisesList.class));
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void setMuscleBuilding() {
        toolbar.setTitle(R.string.muscle_building_program);
        setSupportActionBar(toolbar);
        db.collection("musclebuilding")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                exercisesLists.add(dc.getDocument().toObject(ExercisesList.class));
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ProgramActivity.this, WorkoutDetailsActivity.class);
        intent.putExtra("WorkoutName", exercisesLists.get(position).getExerciseName());
        intent.putExtra("WorkoutDescription", exercisesLists.get(position).getExerciseDescription());
        startActivity(intent);
    }
}