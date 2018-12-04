package com.example.walker.milestone;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserMilestones extends AppCompatActivity {

    public Intent intent;
    String milestoneText;
    String milestoneDate;
    String dummy1text = "first milestone";
    String dummy1date = "12-05-2018";
    String dummy2text = "Second milestone";
    String dummy2date = "12-04-2018";
    String dummy3text = "third milestone";
    String dummy3date = "12-30-2018";
    List<Milestone> milestones = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_milestones);

//        Button addMilestone = findViewById(R.id.add_milestone);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        milestones.add(new Milestone(dummy1text, dummy1date));
        milestones.add(new Milestone(dummy2text, dummy2date));
        milestones.add(new Milestone(dummy3text, dummy3date));

        milestones.sort(new Comparator<Milestone>() {
            @Override
            public int compare(Milestone o1, Milestone o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        MilestoneRVAdapter adapter = new MilestoneRVAdapter(milestones);
        rv.setAdapter(adapter);

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
        }
    }
}
