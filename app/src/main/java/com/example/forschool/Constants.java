package com.example.forschool;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.forschool.MainActivity.olympiadAdapter;
import static com.example.forschool.MainActivity.olympiadAdapterINFORMATICS;
import static com.example.forschool.MainActivity.olympiadAdapterPHYSICS;
import static com.example.forschool.MainActivity.olympiadAdapterRUSSIAN_LANGUAGE;
import static com.example.forschool.MainActivity.olympiads;
import static com.example.forschool.MainActivity.olympiadsINFORMATICS;
import static com.example.forschool.MainActivity.olympiadsPHYSICS;
import static com.example.forschool.MainActivity.olympiadsRUSSIAN_LANGUAGE;

public class Constants {
    public static String MATH = "matholympics";
    public static String INFORMATICS = "informaticsolympics";
    public static String PHYSICS = "physicsolympics";
    public static String RUSSIAN_LANGUAGE = "russianlanguageolympics";

}
