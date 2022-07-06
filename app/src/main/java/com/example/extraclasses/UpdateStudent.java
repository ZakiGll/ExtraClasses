package com.example.extraclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateStudent extends AppCompatActivity {
    EditText EditNameS;
    EditText EditAgeS;
    Button EditBtnS;
    String uid;
    String nameP,ageP;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid().toString();
        EditNameS = findViewById(R.id.StudentNameEdit);
        EditAgeS = findViewById(R.id.StudentAgeEdit);
        EditBtnS = findViewById(R.id.EditBtnS);
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://extra-classes-e11d1-default-rtdb.firebaseio.com/Users").child(uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String ageD = snapshot.child("age").getValue().toString();
                String nameD = snapshot.child("name").getValue().toString();
                //String ageD = snapshot.child(uid).child("age").getValue().toString();

                EditNameS.setText(nameD);
                EditAgeS.setText(ageD);
//

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });


        EditBtnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://extra-classes-e11d1-default-rtdb.firebaseio.com/Users").child(uid);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //String ageD = snapshot.child(uid).child("age").getValue().toString();

                        nameP = EditNameS.getText().toString();
                        ageP = EditAgeS.getText().toString();
                        databaseReference.child("age").setValue(ageP);
                        databaseReference.child("name").setValue(nameP);
                        Toast.makeText(UpdateStudent.this, "User account updated.",
                                Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }
                });

            }
        });
    }
}