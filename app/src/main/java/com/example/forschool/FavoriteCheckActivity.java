package com.example.forschool;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FavoriteCheckActivity extends AppCompatActivity {
    TextView name;
    TextView context;
    TextView organizator;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_view);
        name = findViewById(R.id.name_of_favorite_olympiad);
        organizator = findViewById(R.id.organizator_of_olympiad);
        context = findViewById(R.id.context_of_olympic);
        name.setText(UserProfile.favoriteOlympiads.get(FavoriteActivity.pos).getFullName());
        organizator.setText(UserProfile.favoriteOlympiads.get(FavoriteActivity.pos).getOrganizer());
        context.setText(UserProfile.favoriteOlympiads.get(FavoriteActivity.pos).getShortName());
    }
}