package com.example.extraclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splach extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
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

    public void changeToRegister(View v) {
        Intent intent = new Intent(getApplicationContext(),register.class);
        startActivity(intent);
    }

    public void onLogin (View v) {
        final EditText emailView=(EditText)findViewById(R.id.emaillog);
        final String email= emailView.getText().toString();
        final EditText passwordView=(EditText)findViewById(R.id.passwordlog);
        final String password= passwordView.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
// Sign in success, update UI with the signed-in user's information
                            Log.d("AUTH_APP", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new
                                    Intent(Splach.this,home.class);
                            startActivity(i);
//updateUI(user);
                        } else {
// If sign in fails, display a message to the user.
                            Log.w("AUTH_APP", "signInWithEmail:failure",
                                    task.getException());
                            Toast.makeText(Splach.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//updateUI(null);
                        }
                    }
                });
    }
}