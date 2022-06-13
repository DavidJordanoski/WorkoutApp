package com.example.workoutapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;
import androidx.room.Room;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workoutapp.roomdatabase.User;
import com.example.workoutapp.roomdatabase.UserDAO;
import com.example.workoutapp.roomdatabase.UserDataBase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    EditText editEmail,editPassword;
    Button buttonLogin,changeLanguage;
    TextView textViewRegister;

    UserDAO db;
    UserDataBase dataBase;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadLocale();
        editEmail = findViewById(R.id.editTextEmail);
        editPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        changeLanguage = findViewById(R.id.engLang);
        textViewRegister = findViewById(R.id.textViewRegister);

        mAuth = FirebaseAuth.getInstance();

        dataBase = Room.databaseBuilder(this,UserDataBase.class,"User")
                .allowMainThreadQueries()
                .build();

        db = dataBase.getUserDao();

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             loginRoomDatabase();
             loginFireBase();
            }
        });
        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLanguageDialog();
            }
        });
    }

    private void showLanguageDialog() {
        final String [] listItems = {"English","Macedonian"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
        mBuilder.setTitle("Choose Language");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    setLocale("en");
                    recreate();
                } else if (which == 1) {
                    setLocale("mk");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    private void loadLocale(){
        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_Lang","");
        setLocale(language);
    }

    private void loginFireBase() {
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Failed to connect to Firebase", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginRoomDatabase() {
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        User user = db.getUser(email,password);

        if (user != null) {
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            i.putExtra("User",user);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "UnRegistered User , or incorrect username or password", Toast.LENGTH_SHORT).show();
        }

    }
}