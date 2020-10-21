package com.example.forschool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static OlympiadAdapter olympiadAdapter;
    LinearLayoutManager linearLayoutManager;
    public static DatabaseReference mdata;
    UserProfile up = new UserProfile("OK");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        mdata =  FirebaseDatabase.getInstance().getReference(MainActivity.idforclasses);
        recyclerView = findViewById(R.id.activity_favorite__rv_olympiad_list);
        get_data_about_favorite_olympics();

        olympiadAdapter = new OlympiadAdapter(up.getFavoriteOlympiads(), new OlympiadAdapter.Listener() {
            @Override
            public void onOlympiadClick(int position) {
                Olympiad selectedOlympiad = up.getFavoriteOlympiads().get(position);

                Intent intent = new Intent(FavoriteActivity.this, OlympiadActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Olympiad " + selectedOlympiad.getShortName() + " has selected", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(olympiadAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
    public void get_data_about_favorite_olympics(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(up.favoriteOlympiads.size()>0){
                    up.favoriteOlympiads.clear();
                }
                //if we received something then delete old data and new data from firebase
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Olympiad user = ds.getValue(Olympiad.class);
                    assert user != null;
                    up.favoriteOlympiads.add(user);
                }
                //notify our adapter if we got new data
                olympiadAdapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        };
        mdata.addValueEventListener(vListener);
    }

}
