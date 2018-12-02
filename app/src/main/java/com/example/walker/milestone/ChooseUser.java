package com.example.walker.milestone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseUser extends AppCompatActivity {

    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
    }

    public void onClick(View view) {
        intent = new Intent(this, SupporterHome.class);
        startActivity(intent);
    }
}
