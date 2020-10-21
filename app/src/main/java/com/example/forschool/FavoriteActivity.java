package com.example.forschool;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OlympiadAdapter olympiadAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerView = findViewById(R.id.activity_favorite__rv_olympiad_list);



        olympiadAdapter = new OlympiadAdapter(UserProfile.getUserProfile().getFavoriteOlympiads(), new OlympiadAdapter.Listener() {
            @Override
            public void onOlympiadClick(int position) {
                Olympiad selectedOlympiad = UserProfile.getUserProfile().getFavoriteOlympiads().get(position);

                Intent intent = new Intent(FavoriteActivity.this, OlympiadActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Olympiad " + selectedOlympiad.getShortName() + " has selected", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(olympiadAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


    }
}
