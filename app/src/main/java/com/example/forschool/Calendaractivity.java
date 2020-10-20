
package com.example.forschool;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class Calendaractivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.calendar);
            CalendarView calendarView = findViewById(R.id.calendarView);
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                @Override
                public void onSelectedDayChange(CalendarView view, int year,
                                                int month, int dayOfMonth) {
                    int mYear = year;
                    int mMonth = month;
                    int mDay = dayOfMonth;
                    String selectedDate = new StringBuilder().append(mMonth+1)
                            .append("-").append(mDay).append("-").append(mYear)
                            .append(" ").toString();
                    Toast.makeText(getApplicationContext(), selectedDate, Toast.LENGTH_LONG).show();
                }
            });
            Calendar caledar = Calendar.getInstance();
            caledar.set(
                    2020,
                    10,
                    11
            );
          calendarView.setDate(caledar.getTimeInMillis(),true,true);
        }
    }


