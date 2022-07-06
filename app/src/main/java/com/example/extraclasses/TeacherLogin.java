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

public class TeacherLogin extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        mAuth = FirebaseAuth.getInstance();
    }

    public void changeToRegisterT(View v) {
        Intent intent = new Intent(getApplicationContext(),TeacherRegister.class);
        startActivity(intent);
    }

    public void onLogin2 (View v) {
        final EditText emailView=(EditText)findViewById(R.id.emailT);
        final String email= emailView.getText().toString();
        final EditText passwordView=(EditText)findViewById(R.id.passwordT);
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
                                    Intent(getApplicationContext(),HomeTeacher.class);
                            startActivity(i);
//updateUI(user);
                        } else {
// If sign in fails, display a message to the user.
                            Log.w("AUTH_APP", "signInWithEmail:failure",
                                    task.getException());
                            Toast.makeText(TeacherLogin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//updateUI(null);
                        }
                    }
                });
    }
}