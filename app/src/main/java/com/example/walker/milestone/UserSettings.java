package com.example.walker.milestone;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserSettings extends AppCompatActivity {

    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        Button[] buttons = new Button[3];
        Button b1 = findViewById(R.id.milestone_reached);
        buttons[0] = b1;
        Button b2 = findViewById(R.id.supporter_request);
        buttons[1] = b1;
        Button b3 = findViewById(R.id.daily_update);
        buttons[2] = b3;

        for (int i = 0; i < buttons.length; i++){
            buttons[i].setTag("on");
            buttons[i].setBackgroundResource(R.drawable.notification_on);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.homeButton) {
            intent = new Intent(this, Home.class);
            startActivity(intent);
        } else if (id == R.id.calendarButton){
            intent = new Intent(this, UserCalendar.class);
            startActivity(intent);
        } else if (id == R.id.supportersButton){
            intent = new Intent(this, UserSupporters.class);
            startActivity(intent);
        } else if (id == R.id.settingsButton){
            intent = new Intent(this, UserSettings.class);
            startActivity(intent);
        } else if (id == R.id.resourcesButton){
            intent = new Intent(this, UserResources.class);
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
