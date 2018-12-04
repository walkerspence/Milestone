package com.example.walker.milestone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    public Intent intent;
    public String daysSober = "4";
    public String substance = "ALCOHOL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final TextView daysView = findViewById(R.id.days_sober);
        daysView.setText(daysSober + " DAYS");
        TextView substanceView = findViewById(R.id.substance);
        substanceView.setText("FROM " + substance);
        ImageButton addDay = findViewById(R.id.add_day);
        ImageButton removeDay = findViewById(R.id.remove_day);


        addDay.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                daysSober = Integer.toString(Integer.parseInt(daysSober) + 1 );
                daysView.setText(daysSober + " DAYS");

            }
        });
        removeDay.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                daysSober = Integer.toString(Integer.parseInt(daysSober) - 1 );
                daysView.setText(daysSober + " DAYS");
            }
        });

    }

    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.homeButton) {
            intent = new Intent(this, Home.class);
            startActivity(intent);
        } else if (id == R.id.calendarButton){
            intent = new Intent(this, UserMilestones.class);
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
        } else if (id == R.id.check_milestones){
            intent = new Intent(this, UserMilestones.class);
            startActivity(intent);
        }
    }
}
