package com.example.walker.milestone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SupporterCalendar extends AppCompatActivity {

    public Intent intent;
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
        setContentView(R.layout.activity_supporter_calendar);

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
