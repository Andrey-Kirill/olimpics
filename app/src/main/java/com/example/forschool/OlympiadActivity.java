package com.example.forschool;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;


import static com.example.forschool.MainActivity.mDataBaseid;

public class OlympiadActivity extends AppCompatActivity {
    Toolbar toolbar;
    public static ArrayList<Olympiad> test = new ArrayList<>();
    public static boolean added = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olympiad);
        toolbar = findViewById(R.id.toolbar1);
        //set toolbar to our activity
        setSupportActionBar(toolbar);

        if(UserProfile.favoriteOlympiads.size() == 0) {
            Toast.makeText(this, Integer.toString(UserProfile.favoriteOlympiads.size()), Toast.LENGTH_LONG).show();
            mDataBaseid.push().setValue(new Olympiad("test","test","test12",12));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    public void add_to_favorite(View v) {
        UserProfile.addOlympiadToFavorite(MainActivity.olympiads.get(MainActivity.number_of_olympiad));


    }

    public void delete_favorite(View v) {

        Iterator <Olympiad> it = UserProfile.favoriteOlympiads.iterator();
        while (it.hasNext()){
            Olympiad ol = it.next();
            if(ol.getOrganizer().equals(MainActivity.olympiads.get(MainActivity.number_of_olympiad).getOrganizer())){
                it.remove();
            }
        }

        mDataBaseid.removeValue();

        ArrayList<Olympiad> a = UserProfile.favoriteOlympiads;
        for(Olympiad ol:a){
            mDataBaseid.push().setValue(ol);
        }



    }


}
