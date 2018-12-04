package com.example.walker.milestone;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateUser extends AppCompatActivity implements View.OnClickListener {

    public Intent intent;
    private static final String TAG = "CreateUser";

    private EditText display_name, email, school, password, confirmPassword;
    private Button selectIcon, submit;
    private View loader;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private RecyclerViewAdapter imageIcon;


    //for the icons
    private ArrayList<Integer> mImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent(this, ChooseVice.class);
        setContentView(R.layout.activity_create_user);

        final Spinner schoolDropdown = findViewById(R.id.spinner);
        String[] items = new String[]{"UC BERKELEY", "STANFURD"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        schoolDropdown.setAdapter(adapter);

        //placeholder icons
        mImages.add(R.drawable.blank_emoji);
        mImages.add(R.drawable.blush_emoji);
        mImages.add(R.drawable.cheese_emoji);
        mImages.add(R.drawable.drop_emoji);
        mImages.add(R.drawable.love_emoji);
        mImages.add(R.drawable.sleep_emoji);
        mImages.add(R.drawable.smile2_emoji);
        mImages.add(R.drawable.smile_emoji);
        mImages.add(R.drawable.sunglass_emoji);
        mImages.add(R.drawable.tongue_emoji);
        mImages.add(R.drawable.wow_emoji);
        initRecyclerView();

        // EditTexts
        display_name = findViewById(R.id.displayName);
        email = findViewById(R.id.email);
        school = findViewById(R.id.school);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);

        // Buttons
        //selectIcon = findViewById(R.id.selectIcon);
        submit = findViewById(R.id.submit);
        loader = findViewById(R.id.loader);
        // Set listeners
//        selectIcon.setOnClickListener(this);
        submit.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference();

        // Once the user is successfully created, we store additional info in our db
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                Log.d(TAG, "got here");
                if (user != null) {
                    // User is signed in
                    DatabaseReference usersRef = ref.child("users");
                    usersRef.child(user.getUid()).setValue(new User(user.getUid(),
                            display_name.getText().toString(), email.getText().toString(),
                            schoolDropdown.getSelectedItem().toString(),
                            imageIcon.clickedIcon.getId(), "placeholder", true));
                    startActivity(intent);
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }


    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mImages);
        recyclerView.setAdapter(adapter);
        imageIcon = adapter;
    }

    public void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "insideCreateUser");
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            submit.setVisibility(View.VISIBLE);
                            loader.setVisibility(View.INVISIBLE);
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CreateUser.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    private boolean validateForm() {
        // TODO: we can add fancy regex checking for email
        boolean valid = true;

        String mEmail = email.getText().toString();
        if (TextUtils.isEmpty(mEmail)) {
            email.setError("Required.");
            valid = false;
        } else {
            email.setError(null);
        }

        String pass = password.getText().toString();
        if (TextUtils.isEmpty(pass)) {
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }

        String confirmPass = confirmPassword.getText().toString();
        if (!pass.equals(confirmPass)) {
            confirmPassword.setError("Passwords don't match");
            valid = false;
        } else {
            confirmPassword.setError(null);
        }
        if (valid) {
            Log.d(TAG, "validated");
        }

        return valid;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        //if (i == R.id.selectIcon) {
            //// TODO: send to the icon screen so user can select
//            intent = new Intent(this, AccountType.class);
//            startActivity(intent);
        if (i == R.id.submit) {
            Log.d(TAG, "creatingAccount....");
            submit.setVisibility(View.INVISIBLE);
            loader.setVisibility(View.VISIBLE);
            createAccount(email.getText().toString(), password.getText().toString());
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
