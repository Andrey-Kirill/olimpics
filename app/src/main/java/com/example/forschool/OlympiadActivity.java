package com.example.forschool;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
    TextView name_of_olympic;
    TextView context_of_olympiad;
    CheckBox cb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olympiad);
        toolbar = findViewById(R.id.toolbar1);
        //set toolbar to our activity
        setSupportActionBar(toolbar);
        name_of_olympic = findViewById(R.id.name_of_olympiad);
        context_of_olympiad = findViewById(R.id.context_of_olympic);
        cb = findViewById(R.id.checkBox2);
        for(Olympiad ol:UserProfile.favoriteOlympiads){
            if(ol.getOrganizer().equals(MainActivity.olympiads.get(MainActivity.number_of_olympiad).getOrganizer()) == true){
                cb.setChecked(true);
                break;
            }else{
                cb.setChecked(false);
            }
        }
        cb.setOnCheckedChangeListener(new  CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked){
                    add_to_favorite();
                }else{
                    delete_favorite();
                }
            }
        });
        if(UserProfile.favoriteOlympiads.size() == 0) {
            Toast.makeText(this, Integer.toString(UserProfile.favoriteOlympiads.size()), Toast.LENGTH_LONG).show();
            mDataBaseid.push().setValue(new Olympiad("test","test","test12",12));
        }
        name_of_olympic.setText(MainActivity.olympiads.get(MainActivity.number_of_olympiad).getShortName());
        context_of_olympiad.setText(MainActivity.olympiads.get(MainActivity.number_of_olympiad).getOrganizer());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    public void add_to_favorite() {
        UserProfile.addOlympiadToFavorite(MainActivity.olympiads.get(MainActivity.number_of_olympiad));
    }

    public void delete_favorite() {

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
