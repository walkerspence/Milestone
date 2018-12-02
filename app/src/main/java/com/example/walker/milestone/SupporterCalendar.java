package com.example.walker.milestone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SupporterCalendar extends AppCompatActivity {

    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supporter_calendar);
    }

    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.homeButton) {
            intent = new Intent(this, SupporterHome.class);
            startActivity(intent);
        } else if (id == R.id.calendarButton){
            intent = new Intent(this, SupporterCalendar.class);
            startActivity(intent);
        } else if (id == R.id.settingsButton){
            intent = new Intent(this, SupporterSettings.class);
            startActivity(intent);
        }
    }

}
