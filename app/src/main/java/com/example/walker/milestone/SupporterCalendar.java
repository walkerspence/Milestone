package com.example.walker.milestone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupporterCalendar extends AppCompatActivity {

    public Intent intent;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "SupporterHome";
    private String userID;
    private String viceName;


    String alc1text = "One week sober";
    String alc1date = "12-09-2018";
    String alc2text = "Sober new years";
    String alc2date = "01-01-2019";
    String alc3text = "One month sober";
    String alc3date = "01-02-2019";
    String alc4text = "Sober valentines day";
    String alc4date = "02-14-2019";
    ArrayList<Milestone> milestonesAlc = new ArrayList<>();

    String cig1text = "One week sober";
    String cig1date = "12-09-2018";
    String cig2text = "Heart attack risk drops";
    String cig2date = "03-02-2019";
    String cig3text = "One month sober";
    String cig3date = "01-02-2019";
    String cig4text = "Normal blood oxygen";
    String cig4date = "12-3-2018";
    ArrayList<Milestone> milestonesCig = new ArrayList<>();

    String bob1text = "One week boba-free";
    String bob1date = "12-09-2018";
    String bob2text = "Saved $100";
    String bob2date = "12-27-2018";
    String bob3text = "One month boba-free";
    String bob3date = "01-02-2019";;
    ArrayList<Milestone> milestonesBob = new ArrayList<>();

    Map<String, ArrayList<Milestone>> milestoneLists = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supporter_calendar);

        viceName = getIntent().getStringExtra("viceName");

        milestonesAlc.add(new Milestone(alc1text, alc1date));
        milestonesAlc.add(new Milestone(alc2text, alc2date));
        milestonesAlc.add(new Milestone(alc3text, alc3date));
        milestonesAlc.add(new Milestone(alc4text, alc4date));

        milestonesCig.add(new Milestone(cig1text, cig1date));
        milestonesCig.add(new Milestone(cig2text, cig2date));
        milestonesCig.add(new Milestone(cig3text, cig3date));
        milestonesCig.add(new Milestone(cig4text, cig4date));

        milestonesBob.add(new Milestone(bob1text, bob1date));
        milestonesBob.add(new Milestone(bob2text, bob2date));
        milestonesBob.add(new Milestone(bob3text, bob3date));

        milestonesAlc.sort(new Comparator<Milestone>() {
            @Override
            public int compare(Milestone o1, Milestone o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        milestonesCig.sort(new Comparator<Milestone>() {
            @Override
            public int compare(Milestone o1, Milestone o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        milestonesBob.sort(new Comparator<Milestone>() {
            @Override
            public int compare(Milestone o1, Milestone o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        final RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        milestoneLists.put("alcohol", milestonesAlc);
        milestoneLists.put("boba", milestonesBob);
        milestoneLists.put("cigarette", milestonesCig);


        MilestoneRVAdapter adapter = new MilestoneRVAdapter(milestoneLists.get(viceName));
        rv.setAdapter(adapter);
    }

    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.homeButton) {
            intent = new Intent(this, SupporterHome.class);
            intent.putExtra("viceName", viceName);
            startActivity(intent);
        } else if (id == R.id.calendarButton){
            intent = new Intent(this, SupporterCalendar.class);
            intent.putExtra("viceName", viceName);
            startActivity(intent);
        } else if (id == R.id.settingsButton){
            intent = new Intent(this, SupporterSettings.class);
            intent.putExtra("viceName", viceName);
            startActivity(intent);
        }
    }

}
