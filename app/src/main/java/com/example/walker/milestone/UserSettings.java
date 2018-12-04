package com.example.walker.milestone;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class UserSettings extends AppCompatActivity {

    private final String TAG = "UserSettings";
    public Intent intent;
    private TextView supporterCode;
    private FirebaseUser user;
    public String viceName;


    private FirebaseUser mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        viceName = getIntent().getStringExtra("viceName");

        Button[] buttons = new Button[3];
        Button b1 = findViewById(R.id.milestone_reached);
        buttons[0] = b1;
        Button b2 = findViewById(R.id.supporter_request);
        buttons[1] = b1;
        Button b3 = findViewById(R.id.daily_update);
        buttons[2] = b3;

        for (int i = 0; i < buttons.length; i++){
            buttons[i].setTag("on");
            buttons[i].setBackgroundResource(R.drawable.notification_on);
        }

        supporterCode = findViewById(R.id.supportcode);

        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        if (mAuth == null) {
            Log.d(TAG, "null user");
        }
        supporterCode.setText(mAuth.getUid());
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

    public void onOffNotification(View view) {
        int id = view.getId();
        Button b = findViewById(id);
        if(b.getTag() == "off") {
            b.setBackgroundResource(R.drawable.notification_on);
            b.setTag("on");
        } else {
            b.setBackgroundResource(R.drawable.notification_off);
            b.setTag("off");
        }

    }

    public void onShareCode(View view) {
        // TODO: can u set "code" to the username
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("supporter code", "code");
        clipboard.setPrimaryClip(clip);
    }
}
