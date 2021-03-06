package com.example.walker.milestone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    public Intent intent;
    private EditText username, passwordLogin;
    private Button signup, login, forgotPassword;
    private View loader;
    private String domain = "@example.com";


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set variables
        // EditTexts
        username = findViewById(R.id.username);
        passwordLogin = findViewById(R.id.passwordLogin);

        // Buttons
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);

        forgotPassword = findViewById(R.id.forgot_password);

        loader = findViewById(R.id.login_loader);

        // Set the listeners
        signup.setOnClickListener(this);
        login.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        signOut();
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            sendToCorrectIntent(user);
//
//                            Intent intent = new Intent(getBaseContext(), AccountType.class);
//                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            loader.setVisibility(View.INVISIBLE);
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    private void sendToCorrectIntent(FirebaseUser user) {
        final FirebaseUser checkUser = user;
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef = ref.child("users");
        DatabaseReference supportersRef = ref.child("supporters");

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild(checkUser.getUid())) {
                    Intent intent = new Intent(getBaseContext(), Home.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
            }
        });

        supportersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild(checkUser.getUid())) {
                    Intent intent = new Intent(getBaseContext(), SupporterHome.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
            }
        });
    }

    private void signOut() {
        mAuth.signOut();
    }


    private boolean validateForm() {
        boolean valid = true;

        String email = username.getText().toString();
        if (TextUtils.isEmpty(email)) {
            username.setError("Required.");
            valid = false;
        } else {
            username.setError(null);
        }

        String pass = passwordLogin.getText().toString();
        if (TextUtils.isEmpty(pass)) {
            passwordLogin.setError("Required.");
            valid = false;
        } else {
            passwordLogin.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.signup) {
            intent = new Intent(this, AccountType.class);
            startActivity(intent);
        } else if (i == R.id.login) {
            loader.setVisibility(View.VISIBLE);
            signIn(username.getText().toString(), passwordLogin.getText().toString());
        } else if (i == R.id.directlogin) {
            //TODO: Set the login screen based on User/Supporter account determined from Database
            intent = new Intent(this, SupporterHome.class);
            startActivity(intent);
        }
    }
}
