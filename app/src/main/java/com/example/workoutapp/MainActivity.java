package com.example.workoutapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.workoutapp.roomdatabase.User;
import com.example.workoutapp.roomdatabase.UserDAO;
import com.example.workoutapp.roomdatabase.UserDataBase;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private User user;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        user = (User) getIntent().getSerializableExtra("User");

        Toolbar toolbar = findViewById(R.id.homeToolbar);
        if (user != null) {
            toolbar.setTitle("Welcome, " + user.getUserName());
        }
        setSupportActionBar(toolbar);

        ImageView muscle = findViewById(R.id.muscleBuildingImg);
        muscle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String muscleBuildingProgram = "MuscleBuilding";
                Intent intent = new Intent(MainActivity.this, ProgramActivity.class);
                intent.putExtra("muscle", muscleBuildingProgram);
                startActivity(intent);
            }
        });

        ImageView strength = findViewById(R.id.strengthImg);
        strength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strengthProgram = "Strength";
                Intent intent = new Intent(MainActivity.this, ProgramActivity.class);
                intent.putExtra("strength", strengthProgram);
                startActivity(intent);
            }
        });

        ImageView cardio = findViewById(R.id.cardioImg);
        cardio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardioProgram = "Cardio";
                Intent intent = new Intent(MainActivity.this, ProgramActivity.class);
                intent.putExtra("cardio", cardioProgram);
                startActivity(intent);
            }
        });

        ImageView yoga = findViewById(R.id.yogaImg);
        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yogaProgram = "Yoga";
                Intent intent = new Intent(MainActivity.this, ProgramActivity.class);
                intent.putExtra("yoga", yogaProgram);
                startActivity(intent);
            }
        });

        ImageView calisthenics = findViewById(R.id.calisthenicsImg);
        calisthenics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String calisthenicsProgram = "Calisthenics";
                Intent intent = new Intent(MainActivity.this, ProgramActivity.class);
                intent.putExtra("calisthenics", calisthenicsProgram);
                startActivity(intent);
            }
        });

        ImageView fatLoss = findViewById(R.id.fatLossImg);
        fatLoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fatLossProgram = "FatLoss";
                Intent intent = new Intent(MainActivity.this, ProgramActivity.class);
                intent.putExtra("fatLoss", fatLossProgram);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}