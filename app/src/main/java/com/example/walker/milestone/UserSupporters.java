package com.example.walker.milestone;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import static java.lang.Math.toIntExact;

public class UserSupporters extends AppCompatActivity {

    private static final String TAG = "UserSupporters";
    public Intent intent;
    private FirebaseUser mAuth;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private ImageView supporterIcon;
    private TextView supporterName;
    public String viceName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_supporters);

        supporterIcon = findViewById(R.id.supporterIcon);
        supporterName = findViewById(R.id.supporterName);

        viceName = getIntent().getStringExtra("viceName");
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        DatabaseReference user = ref.child("users").child(mAuth.getUid()).child("supporterUID");

        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String supporterUID = (String) dataSnapshot.getValue();
                populateSupporterIcon(supporterUID);
                populateSupporterName(supporterUID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    public void populateSupporterIcon(String supporterUID) {
        final DatabaseReference supporterIconRef = ref.child("supporters").
                            child(supporterUID).child("iconImage");
        supporterIconRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int iconImageIndex = toIntExact((long) dataSnapshot.getValue());
                supporterIcon.setImageResource(iconImageIndex);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void populateSupporterName(String supporterUID) {
        final DatabaseReference supporterIconRef = ref.child("supporters").
                child(supporterUID).child("displayName");
        supporterIconRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nameOfSupporter = (String) dataSnapshot.getValue();
                supporterName.setText(nameOfSupporter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
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
        }

    }

    public void onJoinSlack(View view) {
        int id = view.getId();
        String url = "https://join.slack.com/t/milestone-corp/shared_invite/enQtNDk1NTc2NjE1NTExLTBjMmU0NGQyNWVhNWZlOTVjNDdiNDQ0NDE2NWRjZTQ1YTFmYTBmYzk0N2ViMTk3ZmQ2OGQxN2VjMmFkMjY2Mzk";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void onShareCode(View view) {
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        if (mAuth == null) {
            Log.d(TAG, "null user");
        }
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("supporter code", mAuth.getUid());
        clipboard.setPrimaryClip(clip);
    }
}
