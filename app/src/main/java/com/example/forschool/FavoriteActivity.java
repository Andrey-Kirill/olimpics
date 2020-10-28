package com.example.forschool;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static com.example.forschool.MainActivity.mDataBaseid;
import static com.example.forschool.OlympiadActivity.test;

public class FavoriteActivity extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager;
    OlympiadAdapter olympiadAdapter;
    public static int pos;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        RecyclerView recyclerView = findViewById(R.id.activity_favorite__rv_olympiad_list);

        olympiadAdapter = new OlympiadAdapter(UserProfile.getUserProfile().getFavoriteOlympiads(), new OlympiadAdapter.Listener() {
            @Override
            public void onOlympiadClick(int position) {
                Olympiad selectedOlympiad = UserProfile.getUserProfile().getFavoriteOlympiads().get(position);
                pos = position;
                Intent intent = new Intent(FavoriteActivity.this,FavoriteCheckActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Olympiad " + selectedOlympiad.getShortName() + " has selected", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(olympiadAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        UserProfile.favoriteOlympiads = Firebase.get_about_olympics(UserProfile.getFavoriteOlympiads(), mDataBaseid,olympiadAdapter);


    }
}
