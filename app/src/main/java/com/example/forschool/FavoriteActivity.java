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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        RecyclerView recyclerView = findViewById(R.id.activity_favorite__rv_olympiad_list);

        getdata();
        OlympiadAdapter olympiadAdapter = new OlympiadAdapter(UserProfile.getUserProfile().getFavoriteOlympiads(), new OlympiadAdapter.Listener() {
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
    public void getdata(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(UserProfile.favoriteOlympiads.size()>0){
                    UserProfile.favoriteOlympiads.clear();
                }
                //if we received something then delete old data and new data from firebase
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Olympiad user = ds.getValue(Olympiad.class);
                    assert user != null;
                    UserProfile.favoriteOlympiads.add(user);
                }


                //notify our adapter if we got new data
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDataBaseid.addValueEventListener(vListener);
    }
}
