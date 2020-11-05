package com.example.forschool;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.forschool.MainActivity.mDataBaseid;
import static com.example.forschool.OlympiadActivity.test;

public class UserProfile {

    public static ArrayList<Olympiad> favoriteOlympiads = new ArrayList<>();

   public  String name;

    public  String surname;
    public  String id;
    public  String password;
    public  String email;



    public UserProfile() {

    }

    public  UserProfile(String name, String surname, String email, String password, String id) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.id = id;
    }



/*
        favoriteOlympiads.add(new Olympiad("test", "test", "test", R.drawable.ic_launcher_background));
        favoriteOlympiads.add(new Olympiad("test", "test", "test", R.drawable.ic_launcher_background));
        favoriteOlympiads.add(new Olympiad("test", "test", "test", R.drawable.ic_launcher_background));
        favoriteOlympiads.add(new Olympiad("test", "test", "test", R.drawable.ic_launcher_background));
 */


    public static void addOlympiadToFavorite(final Olympiad olympiad) {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(test.size()>0){
                    test.clear();
                }
                //if we received something then delete old data and new data from firebase
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Olympiad user = ds.getValue(Olympiad.class);

                    test.add(user);
                }


                //notify our adapter if we got new data
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDataBaseid.addValueEventListener(vListener);

        if(OlympiadActivity.added == true) {
            mDataBaseid.push().setValue(olympiad);
        }

    }



    public void removeOlympiadFromFavorite(Olympiad olympiad) {
        favoriteOlympiads.remove(olympiad);
    }

    public void updateOlympiad(Olympiad olympiad) {
        if (favoriteOlympiads.contains(olympiad)) {
            favoriteOlympiads.remove(olympiad);
        } else {
            favoriteOlympiads.add(olympiad);
        }
    }

    public static boolean isFavoriteOlympiad(String name) {
        for (Olympiad olympiad : favoriteOlympiads) {
            if (olympiad.getShortName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static  ArrayList<Olympiad> getFavoriteOlympiads() {
        return favoriteOlympiads;
    }






    public static void setName(String name) { name = name;
    }

    public static void setSurname(String surname) {
        surname = surname;
    }

    public static void setPassword(String password) {
        password = password;
    }

    public static void setEmail(String email) {
        email = email;
    }
}
