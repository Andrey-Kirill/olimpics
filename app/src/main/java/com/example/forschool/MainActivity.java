package com.example.forschool;

import android.annotation.SuppressLint;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
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
    OlympiadAdapter olympiadAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<Olympiad> olympiads = new ArrayList<>();
    public ArrayList<User> data;

    public  DatabaseReference mDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        //set toolbar to our activity
        setSupportActionBar(toolbar);
        // this is fab button
        mDataBase  = FirebaseDatabase.getInstance().getReference(Constants.MATH);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        data = new ArrayList<>();
        drawer = findViewById(R.id.drawer_layout);

        //add navigation view
        NavigationView navigationView = findViewById(R.id.nav_view);
        // set listener to our navigation view
        navigationView.setNavigationItemSelectedListener(this);

        olympiads.add(new Olympiad("test", "test", "test", R.drawable.ic_launcher_background));

        getdata();
        recyclerView = findViewById(R.id.activity_main__rv_olympiad_list);
        olympiadAdapter = new OlympiadAdapter(olympiads, new OlympiadAdapter.Listener() {
            @Override
            public void onOlympiadClick(int position) {
                Olympiad selectedOlympiad = olympiads.get(position);
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
        return true;
    }
    public void getdata(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(data.size()>0){
                    data.clear();
                }
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    User user = ds.getValue(User.class);
                    assert user != null;
                    data.add(user);
                }
                Toast.makeText(getApplicationContext(),Integer.toString(data.size()),Toast.LENGTH_SHORT).show();
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
        if(id == R.id.add_new){

        }
         return true;
    }
//navigation view listener
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        // if you clicked to this button something must be done

        if(id == R.id.math){
            mDataBase = FirebaseDatabase.getInstance().getReference(Constants.MATH);

        }
        if(id == R.id.physics){
            mDataBase = FirebaseDatabase.getInstance().getReference(Constants.PHYSICS);
            Toast.makeText(getApplicationContext(),"Physics olympics",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.informatics){
            mDataBase = FirebaseDatabase.getInstance().getReference(Constants.INFORMATICS);
            Toast.makeText(getApplicationContext(),"Informatics olympics",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.russian_language){
            mDataBase = FirebaseDatabase.getInstance().getReference(Constants.RUSSIAN_LANGUAGE);
            Toast.makeText(getApplicationContext(),"Russian language olympics",Toast.LENGTH_SHORT).show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}