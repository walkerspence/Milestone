package com.example.walker.milestone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_supporter_login extends AppCompatActivity implements View.OnClickListener {

    public Intent intent;
    private static final String TAG = "activity_supporter_login";

    private EditText displayName, email, supporterCode, password, confirmPassword;
    private Button submit;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent(this, SupporterHome.class);
        setContentView(R.layout.activity_supporter_login);

        displayName = findViewById(R.id.displayName);
        email = findViewById(R.id.email);
        supporterCode = findViewById(R.id.supporterCode);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference();

        // Once the user is successfully created, we store additional info in our db
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser supporter = firebaseAuth.getCurrentUser();
                Log.d(TAG, "got here");
                if (supporter != null) {
                    // User is signed in
                    DatabaseReference supportersRef = ref.child("supporters");
                    supportersRef.child(supporter.getUid()).setValue(new Supporter(supporter.getUid(),
                            displayName.getText().toString(), email.getText().toString(),
                            supporterCode.getText().toString(), false));

                    setUserSupporter(supporter.getUid(), supporterCode.getText().toString(), ref);
                    startActivity(intent);
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    public void setUserSupporter(String supporterUID, String userUID, DatabaseReference ref) {
        DatabaseReference usersRef = ref.child("users");
        usersRef.child(userUID).child("supporterUID").setValue(supporterUID);
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
                        Log.d(TAG, "insideCreateSupporter");
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createSupporterWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createSupporterWithEmail:failure", task.getException());
                            Toast.makeText(activity_supporter_login.this, "Authentication failed.",
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

        if (i == R.id.submit) {
            Log.d(TAG, "creatingAccount....");
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
