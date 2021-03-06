package com.example.forschool;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
import java.util.UUID;


import static com.example.forschool.UserProfile.favoriteOlympiads;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    final static int CONTEXT_MENU_FAVORITE = 0;
    final static int CONTEXT_MENU_SHARE = 1;
    public static DatabaseReference data_for_firebase = FirebaseDatabase.getInstance().getReference(Constants.USERS);
    DrawerLayout drawer;
    RecyclerView recyclerView;
    public static OlympiadAdapter olympiadAdapter;
    LinearLayoutManager linearLayoutManager;
    public static ArrayList<Olympiad> olympiads = new ArrayList<>();
    public  DatabaseReference mDataBase;
    public static DatabaseReference mDataBaseid;
    public ArrayList<String> idfirebase;
    Toolbar toolbar;
    public SharedPreferences sp;
    public String uniqueID;
    static  String id = "";
    public static String idforclasses;
    public static int number_of_olympiad;
    public static TextView tx;
    public static TextView tx1;
    FirebaseUser user;
    @Override
    public void onStart(){
        loaddata();
        if(id.equals("") == true){
            uniqueID = UUID.randomUUID().toString();
            savedata();
            mDataBaseid = FirebaseDatabase.getInstance().getReference(uniqueID);
            //favoriteOlympiads.add(new Olympiad("test", "test", "test", R.drawable.ic_launcher_background));
           //mDataBaseid.push().setValue(uniqueID);
        }else{
            idforclasses = id;
            mDataBaseid = FirebaseDatabase.getInstance().getReference(id);
            Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
            UserProfile.favoriteOlympiads = Firebase.load_favorite(UserProfile.getFavoriteOlympiads(), mDataBaseid);
        }
        super.onStart();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create toolbar
        toolbar = findViewById(R.id.toolbar);
        //set toolbar to our activity
        setSupportActionBar(toolbar);
        //initialize our database (Math is catalog on our firebase where we save our data in this example maths data)
        mDataBase  = FirebaseDatabase.getInstance().getReference(Constants.PHYSICS);
        // this is fab button
        mDataBaseid = FirebaseDatabase.getInstance().getReference(id);
        idfirebase = new ArrayList<>();
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        user = FirebaseAuth.getInstance().getCurrentUser();
        drawer = findViewById(R.id.drawer_layout);
        //add navigation view
        NavigationView navigationView = findViewById(R.id.nav_view);
        // set listener to our navigation view
        navigationView.setNavigationItemSelectedListener(this);
        View headerview = navigationView.getHeaderView(0);
        LinearLayout header = (LinearLayout) headerview.findViewById(R.id.ok);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Profile.class);
                startActivity(i);
                drawer.closeDrawer(GravityCompat.START);
            }
        });
    if(user.getEmail() != null) {
        tx = headerview.findViewById(R.id.name_user);
        tx1 = headerview.findViewById(R.id.mail);
        tx1.setText(user.getEmail());
        Firebase.load_name_and_surname_for_main();
    }
        recyclerView = findViewById(R.id.activity_main__rv_olympiad_list);
        olympiadAdapter = new OlympiadAdapter(olympiads, new OlympiadAdapter.Listener() {
            @Override
            public void onOlympiadClick(int position) {
                number_of_olympiad = position;
                Olympiad selectedOlympiad = olympiads.get(position);
                Intent intent = new Intent(MainActivity.this, OlympiadActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Olympiad " + selectedOlympiad.getShortName() + " has selected", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(olympiadAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        olympiads = Firebase.get_about_olympics(olympiads,mDataBase,olympiadAdapter);

    }
    public void savedata(){
        sp = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("id",uniqueID);
        ed.commit();
    }
    public void loaddata(){
        sp = getPreferences(MODE_PRIVATE);
        id = sp.getString("id","");
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
            Firebase.get_about_olympics(olympiads,mDataBase,olympiadAdapter);
            toolbar.setTitle("Math");
        }

        if(id == R.id.informatics){
            mDataBase = FirebaseDatabase.getInstance().getReference(Constants.INFORMATICS);
            Toast.makeText(getApplicationContext(),"Informatics olympics",Toast.LENGTH_SHORT).show();
            Firebase.get_about_olympics(olympiads,mDataBase,olympiadAdapter);
            toolbar.setTitle("Informatics");
        }

        if(id == R.id.physics){
            mDataBase = FirebaseDatabase.getInstance().getReference(Constants.PHYSICS);
            Toast.makeText(getApplicationContext(),"Physics olympics",Toast.LENGTH_SHORT).show();
            Firebase.get_about_olympics(olympiads,mDataBase,olympiadAdapter);
            toolbar.setTitle("Physics");
        }
        if(id == R.id.russian_language){
            mDataBase = FirebaseDatabase.getInstance().getReference(Constants.RUSSIAN_LANGUAGE);
            Toast.makeText(getApplicationContext(),"Russian language olympics",Toast.LENGTH_SHORT).show();
            Firebase.get_about_olympics(olympiads,mDataBase,olympiadAdapter);
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
        if(id == R.id.calendar){
            Intent i = new Intent(getApplicationContext(),Calendaractivity.class);
            startActivity(i);
            loaddata();

           Toast.makeText(getApplicationContext(), mDataBaseid.getKey(),Toast.LENGTH_LONG).show();
        }
        if(id == R.id.action_settings1){
            Intent i = new Intent(MainActivity.this,FavoriteActivity.class);
            startActivity(i);
        }
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}