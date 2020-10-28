package com.example.forschool;

import android.view.ContextMenu;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import static com.example.forschool.MainActivity.mDataBaseid;

public class Firebase {

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

        public static ArrayList<UserProfile> load_user(final ArrayList<UserProfile> list_users,DatabaseReference database){
            ValueEventListener vListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (list_users.size() > 0) {
                        list_users.clear();
                    }
                    //if we received something then delete old data and new data from firebase
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        UserProfile user = ds.getValue(UserProfile.class);
                        assert user != null;
                        list_users.add(user);
                    }

                    //notify our adapter if we got new data
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            database.addValueEventListener(vListener);

          return list_users;
        }
    public static void load_name_and_surname(){
        final ArrayList<UserProfile> up = new ArrayList<>();
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (up.size() > 0) {
                    up.clear();
                }
                //if we received something then delete old data and new data from firebase
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    UserProfile user = ds.getValue(UserProfile.class);
                    assert user != null;
                    up.add(user);
                }
                Iterator<UserProfile> iter = up.iterator();
                while (iter.hasNext()){
                    UserProfile opr = iter.next();
                    if(opr.id.equals(MainActivity.idforclasses)){
                        MainActivity.tx.setText(opr.name+" "+opr.surname);
                        Profile.name.setText(opr.name);
                        Profile.second_name.setText(opr.surname);
                    }
                }

                //notify our adapter if we got new data
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
       MainActivity.data_for_firebase.addValueEventListener(vListener);

    }
    public static void load_name_and_surname_for_main(){
        final ArrayList<UserProfile> up = new ArrayList<>();
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (up.size() > 0) {
                    up.clear();
                }
                //if we received something then delete old data and new data from firebase
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    UserProfile user = ds.getValue(UserProfile.class);
                    assert user != null;
                    up.add(user);
                }
                Iterator<UserProfile> iter = up.iterator();
                while (iter.hasNext()){
                    UserProfile opr = iter.next();
                    if(opr.id.equals(MainActivity.idforclasses)){
                        MainActivity.tx.setText(opr.name+" "+opr.surname);
                    }
                }

                //notify our adapter if we got new data
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        MainActivity.data_for_firebase.addValueEventListener(vListener);

    }

}