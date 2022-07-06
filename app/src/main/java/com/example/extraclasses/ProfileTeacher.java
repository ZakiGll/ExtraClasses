package com.example.extraclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileTeacher extends AppCompatActivity {
    String uid;
    Button logOut;
    FirebaseAuth firebaseAuth;
    LinearLayout EditP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_teacher);
        TextView nameP = findViewById(R.id.nameProfileT);
        TextView emailP = findViewById(R.id.emailProfileT);
        EditP = findViewById(R.id.EditPBtn);
        firebaseAuth = FirebaseAuth.getInstance();
        //TextView ageP = findViewById(R.id.yearProfileStudent);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid().toString();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://extra-classes-e11d1-default-rtdb.firebaseio.com/Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String emailD = snapshot.child(uid).child("email").getValue().toString();
                String nameD = snapshot.child(uid).child("name").getValue().toString();
                //String ageD = snapshot.child(uid).child("age").getValue().toString();
                nameP.setText(nameD);
                emailP.setText(emailD);
//

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

        LinearLayout add = findViewById(R.id.addBtnProfile);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AddCourse.class);
                startActivity(i);
            }
        });
        LinearLayout home = findViewById(R.id.HomeBtnProfileT);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HomeTeacher.class);
                startActivity(intent);
            }
        });
        logOut = findViewById(R.id.LogOutTeacher);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent intent = new Intent(getApplicationContext(),ChossingPage.class);
                startActivity(intent);
                Toast.makeText(ProfileTeacher.this, "Loged out",
                        Toast.LENGTH_SHORT).show();

            }
        });
        EditP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UpdateTeacher.class);
                startActivity(intent);
            }
        });





    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),HomeTeacher.class);
        startActivity(intent);
    }




}