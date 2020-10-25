package com.example.forschool;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {
    EditText name;
    EditText second_name;
    public SharedPreferences sp;
    public String nameload;
    public String secondnameload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        name = findViewById(R.id.name);
        second_name = findViewById(R.id.second_name);
        load_data();
        if((nameload.equals("") || secondnameload.equals("")) == true){
            Toast.makeText(getApplicationContext(),"Please enter your data",Toast.LENGTH_LONG).show();
        }else{
            name.setText(nameload);
            second_name.setText(secondnameload);
        }

    }
    public void save(View v){
        String name = this.name.getText().toString();
        String second_name  = this.second_name.getText().toString();
        if((this.name.getText().toString().equals("") || this.second_name.getText().toString().equals("")) == false) {
            sp = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();
            ed.putString("name",name);
            ed.putString("second_name",second_name);
            ed.commit();
        }else{
            Toast.makeText(getApplicationContext(),"Please enter your data",Toast.LENGTH_LONG).show();
        }
    }
    public void load_data(){
        sp = getPreferences(MODE_PRIVATE);
        nameload = sp.getString("name","");
        secondnameload = sp.getString("second_name","");
    }
}
