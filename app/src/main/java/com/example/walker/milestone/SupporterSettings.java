package com.example.walker.milestone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SupporterSettings extends AppCompatActivity {

    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supporter_settings);

        Button[] buttons = new Button[2];
        Button b1 = findViewById(R.id.milestone_reached);
        buttons[0] = b1;
        Button b2 = findViewById(R.id.sobriety_reset);
        buttons[1] = b1;

        for (int i = 0; i < buttons.length; i++){
            buttons[i].setTag("on");
            buttons[i].setBackgroundResource(R.drawable.notification_on);
        }
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

    public void onOffNotification(View view) {
        int id = view.getId();
        Button b = findViewById(id);
        if(b.getTag() == "off") {
            b.setBackgroundResource(R.drawable.notification_on);
            b.setTag("on");
        } else {
            b.setBackgroundResource(R.drawable.notification_off);
            b.setTag("off");
        }
    }



}
