package com.example.forschool;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;
import java.util.Iterator;


import static com.example.forschool.MainActivity.CONTEXT_MENU_FAVORITE;
import static com.example.forschool.MainActivity.CONTEXT_MENU_SHARE;
import static com.example.forschool.MainActivity.mDataBaseid;
import static com.example.forschool.MainActivity.olympiads;

public class OlympiadActivity extends AppCompatActivity {
    Toolbar toolbar;
    public static ArrayList<Olympiad> test = new ArrayList<>();
    public static boolean added = true;
    TextView name_of_olympic;
    TextView context_of_olympiad;
    TextView organizator;
    CheckBox cb;
    ConstraintLayout l;
    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle(R.string.recycle_view_context_menu_title);
        contextMenu.add(0, 0, 0, R.string.recycle_view_context_menu_favorite);
        contextMenu.add(0, 1, 0, R.string.recycle_view_context_menu_share);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olympiad);
        toolbar = findViewById(R.id.toolbar1);
        //set toolbar to our activity
        setSupportActionBar(toolbar);
        name_of_olympic = findViewById(R.id.name_of_olympiad);
        organizator = findViewById(R.id.organizator_of_olympiad);
        context_of_olympiad = findViewById(R.id.context_of_olympic);
        cb = findViewById(R.id.checkBox2);
        l = findViewById(R.id.constraint_layout);
        registerForContextMenu(l);
        for(Olympiad ol:UserProfile.favoriteOlympiads){
            if(ol.getShortName().equals(olympiads.get(MainActivity.number_of_olympiad).getShortName()) == true){
                cb.setChecked(true);
                break;
            }else{
                cb.setChecked(false);
            }
        }
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked){
                    add_to_favorite();
                }else{
                    delete_favorite();
                }
            }
        });
        if(UserProfile.favoriteOlympiads.size() == 0) {
            Toast.makeText(this, Integer.toString(UserProfile.favoriteOlympiads.size()), Toast.LENGTH_LONG).show();
            //mDataBaseid.push().setValue(new Olympiad("test","test","test12",12));
        }
        name_of_olympic.setText(olympiads.get(MainActivity.number_of_olympiad).getShortName());
        organizator.setText(olympiads.get(MainActivity.number_of_olympiad).getOrganizer());
        context_of_olympiad.setText(olympiads.get(MainActivity.number_of_olympiad).getOrganizer());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Log.d("name", "test");
        if (item.getItemId() == CONTEXT_MENU_FAVORITE) {
            Toast.makeText(this, "favorite", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == CONTEXT_MENU_SHARE) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Do you want take part in "+ olympiads.get(MainActivity.number_of_olympiad).getShortName());
            sendIntent.setType("text/plain");
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        }

        return super.onContextItemSelected(item);
    }

    public void add_to_favorite() {
        UserProfile.addOlympiadToFavorite(olympiads.get(MainActivity.number_of_olympiad));
    }

    public void delete_favorite() {

        Iterator <Olympiad> it = UserProfile.favoriteOlympiads.iterator();
        while (it.hasNext()){
            Olympiad ol = it.next();
            if(ol.getShortName().equals(olympiads.get(MainActivity.number_of_olympiad).getShortName())){
                it.remove();
            }
        }
        mDataBaseid.removeValue();
        ArrayList<Olympiad> a = UserProfile.favoriteOlympiads;
        for(Olympiad ol:a){
            ol.btmp = null;
            mDataBaseid.push().setValue(ol);
        }
    }
}
