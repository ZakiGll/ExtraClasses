package com.example.extraclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class register extends AppCompatActivity {



    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();


    }
    @Override
    public void onStart() {
        super.onStart();
// Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
//reload();
            Log.d("AUTH_APP","There is already a user logged in");
        }
    }
    public void onRegister(View v) {
        final EditText
                emailView=(EditText)findViewById(R.id.emailreg);
        final String email= emailView.getText().toString();
        final EditText passwordView=(EditText)findViewById(R.id.ClasssDesciption);
        final String password= passwordView.getText().toString();
        final EditText nameS = (EditText)findViewById(R.id.nameStudent);
        final String name= nameS.getText().toString();
        final EditText AgeS = (EditText)findViewById(R.id.ageStudent);
        final String Age= AgeS.getText().toString();

        if(email.isEmpty()){
            emailView.setError("Enter your email !");
            emailView.requestFocus();
            return;
        }
        if(password.isEmpty()){
            passwordView.setError("Enter your password !");
            passwordView.requestFocus();
            return;
        }
        if(password.length()<6){
            passwordView.setError("Your password lenght must be 6 caracteres or more !");
            passwordView.requestFocus();
            return;
        }
        if(name.isEmpty()){
            nameS.setError("Enter your name !");
            nameS.requestFocus();
            return;
        }
        if(Age.isEmpty()){
            AgeS.setError("Enter your age !");
            AgeS.requestFocus();
            return;
        }
        String type ="student";

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                    Users userr = new Users(email,name,Age,type);
                        FirebaseDatabase.getInstance().getReferenceFromUrl("https://extra-classes-e11d1-default-rtdb.firebaseio.com/Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userr);

                        if (task.isSuccessful()) {
// Sign in success, update UI with the signed-in user's information
                            Log.d("AUTH_APP", "createUserWithEmail:success");
                            Toast.makeText(register.this, "User account added.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new
                                    Intent(getApplicationContext(),home.class);
                            startActivity(i);
//updateUI(user);
                        } else {
// If sign in fails, display a message to the user.
                            Log.w("AUTH_APP", "createUserWithEmail:failure",
                                    task.getException());
                            Toast.makeText(register.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
//updateUI(null);
                        }
                    }
                });
    }
}