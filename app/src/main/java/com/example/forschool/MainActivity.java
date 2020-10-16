package com.example.forschool;

import android.annotation.SuppressLint;
import android.database.DataSetObserver;
import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterforschool;
    ArrayList<String> activitiesforchill = new ArrayList<>();
    ArrayList<String> activiesforschool = new ArrayList<>();
    TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        listView = findViewById(R.id.listview);
        activitiesforchill.add("Okkkk");
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,activitiesforchill);
        listView.setAdapter(adapter);
        tx = findViewById(R.id.textView3);
        int a = activitiesforchill.size();
        String str = Integer.toString(a);
        String str2 = "Activities:"+str;
        tx.setText(str2);
        adapterforschool= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,activiesforschool);
        activiesforschool.add("OL");
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void countofactivities(){
        int a = activitiesforchill.size();
        String str = Integer.toString(a);
        String str2 = "Activities:"+str;
        tx.setText(str2);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.add_new){
            Toast.makeText(this,"ok",Toast.LENGTH_LONG).show();
            activitiesforchill.add("ok");
            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);
            countofactivities();
        }
         return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            listView.setAdapter(adapterforschool);
        }
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}