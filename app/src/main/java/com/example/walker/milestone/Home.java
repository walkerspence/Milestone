package com.example.walker.milestone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {

    public Intent intent;
    public String daysSober = "4";
    public String substance = "ALCOHOL";

    private static final String TAG = "Home";


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private TextView substanceView;

    private String userID;
    private String userName;

    public String viceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viceName = getIntent().getStringExtra("viceName");

        final TextView daysView = findViewById(R.id.days_sober);
        daysView.setText(daysSober + " DAYS");
        substanceView = findViewById(R.id.substance);
        ImageButton addDay = findViewById(R.id.add_day);
        ImageButton removeDay = findViewById(R.id.remove_day);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // User exists
            DatabaseReference supporterRef = ref.child("users").child(mAuth.getCurrentUser().getUid()).child("vice");

            supporterRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    viceName = (String) dataSnapshot.getValue();
                    System.out.println(viceName + "@@@");
                }

                @Override
                public void onCancelled(DatabaseError firebaseError) {
                }
            });
        } else {
            // User is signed out
            Log.d(TAG, "User is null");
        }

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
            intent.putExtra("viceName", viceName);
            startActivity(intent);
        } else if (id == R.id.calendarButton){
            intent = new Intent(this, UserMilestones.class);
            intent.putExtra("viceName", viceName);
            startActivity(intent);
        } else if (id == R.id.supportersButton){
            intent = new Intent(this, UserSupporters.class);
            intent.putExtra("viceName", viceName);
            startActivity(intent);
        } else if (id == R.id.settingsButton){
            intent = new Intent(this, UserSettings.class);
            intent.putExtra("viceName", viceName);
            startActivity(intent);
        } else if (id == R.id.resourcesButton){
            intent = new Intent(this, UserResources.class);
            intent.putExtra("viceName", viceName);
            startActivity(intent);
        } else if (id == R.id.check_milestones){
            intent = new Intent(this, UserMilestones.class);
            intent.putExtra("viceName", viceName);
            startActivity(intent);
        }
    }
}
