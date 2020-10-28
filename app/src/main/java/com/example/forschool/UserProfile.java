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

    String name;
    String surname;
    String id;
    String password;
    String email;

    private static UserProfile userProfile;

     UserProfile(String name, String surname, String email, String password, String id) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.id = id;
    }

    UserProfile() {

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
                for(Olympiad ol:test) {
                    if (ol.getShortName().equals(olympiad.getShortName()) == true) {
                        OlympiadActivity.added = true;
                        break;
                    }else{
                        OlympiadActivity.added = false;
                    }
                }

                //notify our adapter if we got new data
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDataBaseid.addValueEventListener(vListener);

        if(OlympiadActivity.added == false) {
            UserProfile.favoriteOlympiads.add(olympiad);
            mDataBaseid.removeValue();

            ArrayList<Olympiad> a = UserProfile.favoriteOlympiads;
            for (Olympiad ol : a) {
                mDataBaseid.push().setValue(ol);
            }
            OlympiadActivity.added = true;
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

    public static  ArrayList<Olympiad> getFavoriteOlympiads() {
        return favoriteOlympiads;
    }

    public static UserProfile getUserProfile() {
        return userProfile;
    }

    public static void setUserProfile(String name, String surname, String password, String email, String id) {
        userProfile = new UserProfile(name, surname, password, email, id);
    }



    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setFavoriteOlympiads(ArrayList<Olympiad> favoriteOlympiads) {
        this.favoriteOlympiads = favoriteOlympiads;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
