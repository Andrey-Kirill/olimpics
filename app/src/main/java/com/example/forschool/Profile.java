package com.example.forschool;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;

import static com.example.forschool.MainActivity.mDataBaseid;
import static com.example.forschool.MainActivity.tx;
import static com.example.forschool.MainActivity.tx1;

public class Profile extends AppCompatActivity {
    public static EditText name;
    public static EditText second_name;
    public SharedPreferences sp;
    public String nameload;
    public String secondnameload;
    UserProfile user;
    DatabaseReference mdatabase;
    ArrayList<UserProfile> users;
    boolean save = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        mdatabase = FirebaseDatabase.getInstance().getReference(Constants.USERS);
        name = findViewById(R.id.name);
        second_name = findViewById(R.id.second_name);
        users = new ArrayList<>();
        Firebase.load_name_and_surname();
        users = Firebase.load_user(users,mdatabase);
        nameload = name.getText().toString();
        secondnameload = second_name.getText().toString();
        if((nameload.equals("") || secondnameload.equals("")) == true) {
            Toast.makeText(getApplicationContext(), "Please enter your data", Toast.LENGTH_LONG).show();
        }
    }
    public void save(View v){
        Iterator<UserProfile> iter = users.iterator();
        while (iter.hasNext()){
            UserProfile up = iter.next();
            if(up.id.equals(MainActivity.idforclasses)){
                save = false;
                break;
            }else{
                save = true;
            }
        }
        Toast.makeText(getApplicationContext(),Integer.toString(users.size()),Toast.LENGTH_LONG).show();
        if(save == true) {
            String name = this.name.getText().toString();
            String second_name = this.second_name.getText().toString();
            if ((name.equals("") || second_name.equals("")) == false) {
                if ((Autenfication.email.getText().toString().equals("") || Autenfication.passwod.getText().toString().equals("")) == false) {
                    user = new UserProfile(name, second_name, Autenfication.email.getText().toString(), Autenfication.passwod.getText().toString(), MainActivity.idforclasses);
                    mdatabase.push().setValue(user);
                } else {
                    Toast.makeText(getApplicationContext(), "Please register", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please enter your data", Toast.LENGTH_LONG).show();
            }
        }
        /*
        if((this.name.getText().toString().equals("") || this.second_name.getText().toString().equals("")) == false) {

        }else{
            Toast.makeText(getApplicationContext(),"Please enter your data",Toast.LENGTH_LONG).show();
        }
         */
    }
}