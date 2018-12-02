package com.example.walker.milestone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class activity_supporter_login extends AppCompatActivity {

    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supporter_login);
    }

    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.submit) {
            // TODO: send to the icon screen so user can select
              intent = new Intent(this, ChooseUser.class);
              startActivity(intent);
        }
    }

}
