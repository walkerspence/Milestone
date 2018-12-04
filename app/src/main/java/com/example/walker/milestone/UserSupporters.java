package com.example.walker.milestone;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserSupporters extends AppCompatActivity {

    private static final String TAG = "UserSupporters";
    public Intent intent;
    private FirebaseUser mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_supporters);
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

    public void onJoinSlack(View view) {
        int id = view.getId();
        String url = "https://join.slack.com/t/milestone-corp/shared_invite/enQtNDk1NTc2NjE1NTExLTBjMmU0NGQyNWVhNWZlOTVjNDdiNDQ0NDE2NWRjZTQ1YTFmYTBmYzk0N2ViMTk3ZmQ2OGQxN2VjMmFkMjY2Mzk";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void onShareCode(View view) {
        // TODO: can u set "code" to the username
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        if (mAuth == null) {
            Log.d(TAG, "null user");
        }
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("supporter code", mAuth.getUid());
        clipboard.setPrimaryClip(clip);
    }
}
