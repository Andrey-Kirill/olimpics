package com.example.forschool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Settings extends AppCompatActivity {
    FirebaseAuth mDataBase;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        mDataBase = FirebaseAuth.getInstance();
        user = mDataBase.getCurrentUser();
    }
    public void deleteuser(View v) {
        if (user != null) {
            user.delete();
            user.reload();
            Intent i = new Intent(getApplicationContext(), Autenfication.class);
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(),"Please register in app",Toast.LENGTH_LONG).show();
    }
    }
}
