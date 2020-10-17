package com.example.forschool;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Autenfication extends AppCompatActivity {
    EditText email;
    EditText passwod;
    FirebaseAuth autenfication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autenfication_activity);
        email = findViewById(R.id.editTextTextEmailAddress);
        passwod = findViewById(R.id.editTextTextPassword);
        autenfication = FirebaseAuth.getInstance();
    }


    public void signup(View v) {
        if ((email.getText().toString().isEmpty() && passwod.getText().toString().isEmpty()) == false) {
            autenfication.createUserWithEmailAndPassword(email.getText().toString(), passwod.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = autenfication.getCurrentUser();
                        Toast.makeText(getApplicationContext(),"Welcome "+user.getEmail(),Toast.LENGTH_SHORT).show();
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(getApplicationContext(),"Can not create account",Toast.LENGTH_SHORT).show();
                    }
                    // ...
                }
            });
        }else{
            Toast.makeText(this,"Please enter your data",Toast.LENGTH_SHORT).show();
        }
    }
    public void signin(View v){

    }
}

