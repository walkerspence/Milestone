package com.example.walker.milestone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChooseVice extends AppCompatActivity implements View.OnClickListener{

    public Intent intent;
    private static final String TAG = "ChooseVice";
    private Button cig, alc, boba, continueButton;
    private String selected = null;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_vice);

        // Set our buttons
        cig = findViewById(R.id.cig);
        alc = findViewById(R.id.alc);
        boba = findViewById(R.id.boba);
        continueButton = findViewById(R.id.continueButton);

        // Set listeners
        cig.setOnClickListener(this);
        alc.setOnClickListener(this);
        boba.setOnClickListener(this);
        continueButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        intent = new Intent(this, UserCalendar.class);
        int i = view.getId();

        if (i == R.id.cig) {
            selected = "cigarette";
            cig.setBackgroundResource(R.drawable.cigarette_select);
            alc.setBackgroundResource(R.drawable.alcohol);
            boba.setBackgroundResource(R.drawable.boba);
        } else if (i == R.id.alc) {
            selected = "alcohol";
            alc.setBackgroundResource(R.drawable.alcohol_select);
            cig.setBackgroundResource(R.drawable.cigarette);
            boba.setBackgroundResource(R.drawable.boba);
        } else if (i == R.id.boba) {
            selected = "boba";
            boba.setBackgroundResource(R.drawable.boba_select);
            cig.setBackgroundResource(R.drawable.cigarette);
            alc.setBackgroundResource(R.drawable.alcohol);
        } else if (i == R.id.continueButton) {
            if (selected == null) {
                continueButton.setError("Please select a vice.");
            } else {
                // Update database value
                mAuth = FirebaseAuth.getInstance();
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref = database.getReference();

                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    // User exists
                    DatabaseReference usersRef = ref.child("usersWithVice").child(user.getUid());
                    usersRef.child("vice").setValue(selected);
                    startActivity(intent);
                } else {
                    // User is signed out
                    Log.d(TAG, "User is null");
                }
            }
        }
    }

}
