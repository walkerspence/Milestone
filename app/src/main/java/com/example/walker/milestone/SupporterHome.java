package com.example.walker.milestone;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class SupporterHome extends AppCompatActivity implements View.OnClickListener{

    public Intent intent;
    private static final String TAG = "SupporterHome";

    private TextView name;
    private TextView substanceView;
    private Button calendarButton, settingsButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private String userID;
    private String userName;

    public String daysSober = "4";
    public String substance = "ALCOHOL";
    public String viceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supporter_home);

        viceName = getIntent().getStringExtra("viceName");

        final TextView daysView = findViewById(R.id.days_sober);
        daysView.setText(daysSober + " DAYS");
        substanceView = findViewById(R.id.substance);

        // Set components
        name = findViewById(R.id.name);
        calendarButton = findViewById(R.id.calendarButton);
        settingsButton = findViewById(R.id.settingsButton);

        // Set listeners
        calendarButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference();

        FirebaseUser supporter = mAuth.getCurrentUser();
        if (supporter != null) {
            // User exists
            DatabaseReference supporterRef = ref.child("supporters").child(supporter.getUid())
                    .child("supporterCode");

            supporterRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    userID = (String) dataSnapshot.getValue();
                    populateUserName(ref, userID);
                    getUserSubstance(ref, userID);
                }

                @Override
                public void onCancelled(DatabaseError firebaseError) {
                }
            });
        } else {
            // User is signed out
            Log.d(TAG, "User is null");
        }
    }

    public void populateUserName(DatabaseReference ref, String userID) {
        DatabaseReference user = ref.child("users").child(userID).child("displayName");

        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userName = (String) dataSnapshot.getValue();
                name.setText(userName);
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
            }
        });
    }

    public void getUserSubstance(DatabaseReference ref, String userID) {
        DatabaseReference vice = ref.child("users").child(userID).child("vice");

        vice.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                viceName = (String) dataSnapshot.getValue();
                substanceView.setText("FROM " + viceName.toUpperCase());
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
//        if(id == R.id.homeButton) {
//            intent = new Intent(this, SupporterHome.class);
//            startActivity(intent);
//        } else
        if (id == R.id.calendarButton){
            intent = new Intent(this, SupporterCalendar.class);
            intent.putExtra("viceName", viceName);
            startActivity(intent);
        } else if (id == R.id.settingsButton){
            intent = new Intent(this, SupporterSettings.class);
            intent.putExtra("viceName", viceName);
            startActivity(intent);
        }
    }

    public void onLoadTips(View view) {
        int id = view.getId();
        if (id == R.id.seeMoreTips) {
            String url = "https://www.foundationsrecoverynetwork.com/6-ways-help-someone-love-stay-sober/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }

    }

}
