package com.example.forschool;

import android.os.Bundle;
import android.view.Menu;
import android.widget.CheckBox;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OlympiadActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olympiad);
        //toolbar = findViewById(R.id.toolbar1);
        //set toolbar to our activity
        //setSupportActionBar(toolbar);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

}
