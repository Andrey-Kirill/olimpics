package com.example.forschool;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.forschool.MainActivity.mDataBaseid;

public class Firebase {
    public static  int i =0;

    public static ArrayList<Olympiad> get_about_olympics(final ArrayList<Olympiad> olympics, DatabaseReference database, final OlympiadAdapter adapter) {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (olympics.size() > 0) {
                    olympics.clear();
                }
                //if we received something then delete old data and new data from firebase
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Olympiad user = ds.getValue(Olympiad.class);
                    assert user != null;
                    olympics.add(user);
                }

                adapter.notifyDataSetChanged();
                //notify our adapter if we got new data

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        database.addValueEventListener(vListener);

        return olympics;
    }

    public static ArrayList<Olympiad> load_favorite(final ArrayList<Olympiad> olympics, DatabaseReference database) {

        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (olympics.size() > 0) {
                    olympics.clear();
                }
                //if we received something then delete old data and new data from firebase
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Olympiad user = ds.getValue(Olympiad.class);
                    assert user != null;
                    olympics.add(user);
                }


                //notify our adapter if we got new data

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        database.addValueEventListener(vListener);

        return olympics;
        }

    }

