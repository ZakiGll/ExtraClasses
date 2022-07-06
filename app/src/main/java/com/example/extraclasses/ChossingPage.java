package com.example.extraclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChossingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chossing_page);
    }

    public void ToStudent(View view){
        Intent intent = new Intent(getApplicationContext(),Splach.class);
        startActivity(intent);
    }

    public void ToTeacher(View view){
        Intent intent = new Intent(getApplicationContext(),TeacherLogin.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}