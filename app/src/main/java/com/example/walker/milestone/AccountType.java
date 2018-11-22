package com.example.walker.milestone;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AccountType extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AccountType";

    public Intent intent;

    private ImageButton supporter, user;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type);

        // Set our buttons
        supporter = findViewById(R.id.supporter);
        user = findViewById(R.id.user);
        // TODO: we need to find a better name for this shit lmao - forgot_password2 doesn't make sense
        back = findViewById(R.id.forgot_password2);

        // Set listeners
        supporter.setOnClickListener(this);
        user.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();

        if (i == R.id.supporter) {
            // TODO: this will set the intent so that we are sent to the supporter sign up page
//            intent = new Intent(this, CreateUser.class);
//            startActivity(intent);
        }
        else if (i == R.id.user) {
            // We go to the CreateUser activity
            intent = new Intent(this, CreateUser.class);
            startActivity(intent);
        }
        else if (i == R.id.forgot_password2) {
            // We go back to the parent, which is MainActivity
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
