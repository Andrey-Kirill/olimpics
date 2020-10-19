package com.example.forschool;

import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Autenfication extends AppCompatActivity {
    EditText email;
    EditText passwod;
    FirebaseAuth autenfication;
    DatabaseReference mDataBase;
    String USER_KEY = "matholympics";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autenfication_activity);
        email = findViewById(R.id.editTextTextEmailAddress);
        passwod = findViewById(R.id.editTextTextPassword);
        autenfication = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }


    public void signup(final View v) {
        if ((email.getText().toString().isEmpty() && passwod.getText().toString().isEmpty()) == false) {
            autenfication.createUserWithEmailAndPassword(email.getText().toString(), passwod.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = autenfication.getCurrentUser();
                        Toast.makeText(getApplicationContext(),"Welcome "+user.getEmail(),Toast.LENGTH_SHORT).show();
                        Intent i  = new Intent(v.getContext(),MainActivity.class);
                        startActivity(i);
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
    public void signin(final View v) {
        if ((email.getText().toString().isEmpty() && passwod.getText().toString().isEmpty()) == false) {
            autenfication.signInWithEmailAndPassword(email.getText().toString(), passwod.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful() == true) {
                        FirebaseUser user = autenfication.getCurrentUser();
                        Toast.makeText(getApplicationContext(), "You signed as " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(v.getContext(), MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(v.getContext(), "You can not sign in", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Please enter your data", Toast.LENGTH_SHORT).show();
        }

    }

    public void push(View v){

        String id = mDataBase.getKey();
        String txt = email.getText().toString();
        int poster = R.drawable.ic_launcher_background;
        Olympiad user = new Olympiad(id,txt,"test",poster);

        mDataBase.push().setValue(user);
        Toast.makeText(v.getContext(),"ok",Toast.LENGTH_SHORT).show();

   }



}

