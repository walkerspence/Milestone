package com.example.walker.milestone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateUser extends AppCompatActivity implements View.OnClickListener {

    public Intent intent;
    private static final String TAG = "CreateUser";

    private EditText displayName;
    private EditText email;
    private EditText school;
    private EditText password;
    private EditText confirmPassword;
    private Button selectIcon;
    private Button submit;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        // EditTexts
        displayName = findViewById(R.id.displayName);
        email = findViewById(R.id.email);
        school = findViewById(R.id.school);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);

        // Buttons
        selectIcon = findViewById(R.id.selectIcon);
        submit = findViewById(R.id.submit);

        // Set listeners
        selectIcon.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    public void createAccount(String displayName, String email, String school,
                              String password) {
        intent = new Intent(this, ChooseVice.class);
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CreateUser.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        // Send displayName and school to database
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
        if (pass != confirmPass) {
            confirmPassword.setError("Passwords don't match");
            valid = false;
        }

        return valid;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.selectIcon) {
            // TODO: send to the icon screen so user can select
//            intent = new Intent(this, AccountType.class);
//            startActivity(intent);
        } else if (i == R.id.submit) {
            createAccount(displayName.getText().toString(), email.getText().toString(),
                          school.getText().toString(), password.getText().toString());
        }
    }
}
