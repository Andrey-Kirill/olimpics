package com.example.forschool;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    RecyclerView recyclerView;
    public static OlympiadAdapter olympiadAdapter;
    LinearLayoutManager linearLayoutManager;
    public static ArrayList<Olympiad> olympiads = new ArrayList<>();
    public  DatabaseReference mDataBase;
    public static OlympiadAdapter olympiadAdapterINFORMATICS;
    public static OlympiadAdapter olympiadAdapterPHYSICS;
    public static OlympiadAdapter olympiadAdapterRUSSIAN_LANGUAGE;
    public static ArrayList<Olympiad> olympiadsINFORMATICS = new ArrayList<>();
    public static ArrayList<Olympiad> olympiadsPHYSICS = new ArrayList<>();
    public static ArrayList<Olympiad> olympiadsRUSSIAN_LANGUAGE = new ArrayList<>();
    public static UserProfile userProfile;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userProfile = new UserProfile("asdafsa");

        // create toolbar
        toolbar = findViewById(R.id.toolbar);
        //set toolbar to our activity
        setSupportActionBar(toolbar);
        //initialize our database (Math is catalog on our firebase where we save our data in this example maths data)
        mDataBase  = FirebaseDatabase.getInstance().getReference(Constants.PHYSICS);
        // this is fab button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = findViewById(R.id.drawer_layout);

        //add navigation view
        NavigationView navigationView = findViewById(R.id.nav_view);
        // set listener to our navigation view
        navigationView.setNavigationItemSelectedListener(this);

        getdata();

        recyclerView = findViewById(R.id.activity_main__rv_olympiad_list);
        olympiadAdapter = new OlympiadAdapter(olympiads, new OlympiadAdapter.Listener() {
            @Override
            public void onOlympiadClick(int position) {
                Olympiad selectedOlympiad = olympiads.get(position);

                Intent intent = new Intent(MainActivity.this, OlympiadActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Olympiad " + selectedOlympiad.getShortName() + " has selected", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(olympiadAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
    // create menu for toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        CheckBox checkBox = (CheckBox) menu.findItem(R.id.math).getActionView();
        checkBox.setText("Sample Text");
        return true;

    }
    public void getdata(){
        //add firebase listener
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(olympiads.size()>0){
                    olympiads.clear();
                }
                //if we received something then delete old data and new data from firebase
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Olympiad user = ds.getValue(Olympiad.class);
                    assert user != null;
                    olympiads.add(user);
                }
                //notify our adapter if we got new data
                olympiadAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),Integer.toString(olympiads.size()),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        };
        mDataBase.addValueEventListener(vListener);

    }
    // listener of menu for toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        /*
        if(id == R.id.action_settings){
            Intent i = new Intent(getApplicationContext(),Settings.class);
            startActivity(i);
        }
         */
        if(id == R.id.math) {
            mDataBase = FirebaseDatabase.getInstance().getReference(Constants.MATH);
            Toast.makeText(getApplicationContext(),"Math olympics",Toast.LENGTH_SHORT).show();
            getdata();
            toolbar.setTitle("Math");
        }

        if(id == R.id.informatics){
            mDataBase = FirebaseDatabase.getInstance().getReference(Constants.INFORMATICS);
            Toast.makeText(getApplicationContext(),"Informatics olympics",Toast.LENGTH_SHORT).show();
            getdata();
            toolbar.setTitle("Informatics");
        }

        if(id == R.id.physics){
            mDataBase = FirebaseDatabase.getInstance().getReference(Constants.PHYSICS);
            Toast.makeText(getApplicationContext(),"Physics olympics",Toast.LENGTH_SHORT).show();
            getdata();
            toolbar.setTitle("Physics");
        }
        if(id == R.id.russian_language){
            mDataBase = FirebaseDatabase.getInstance().getReference(Constants.RUSSIAN_LANGUAGE);
            Toast.makeText(getApplicationContext(),"Russian language olympics",Toast.LENGTH_SHORT).show();
            getdata();
            toolbar.setTitle("Russian language");
        }
        return true;
    }
    //navigation view listener

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        // if you clicked to this button something must be done
        if(id == R.id.action_settings){
            Intent i = new Intent(getApplicationContext(),Settings.class);
            startActivity(i);
        }
        if (id == R.id.favorite) {
            Intent i = new Intent(getApplicationContext(), FavoriteActivity.class);
            startActivity(i);
        }
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}