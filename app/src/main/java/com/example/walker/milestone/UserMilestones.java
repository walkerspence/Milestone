package com.example.walker.milestone;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserMilestones extends AppCompatActivity {

    public Intent intent;
    String milestoneText;
    String milestoneDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_milestones);

        Button addMilestone = findViewById(R.id.add_milestone);
        addMilestone.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.Theme_AppCompat);
                builder.setTitle("Add a milestone");

                // Set up the input
                final EditText input = new EditText(v.getContext());
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                final EditText date = new EditText(v.getContext());
                date.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(date);
                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        milestoneText = input.getText().toString();
                        milestoneDate = date.getText().toString();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.homeButton) {
            intent = new Intent(this, User.class);
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
}
