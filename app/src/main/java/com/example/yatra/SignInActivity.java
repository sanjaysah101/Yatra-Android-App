package com.example.yatra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    SignInButton signInButton;
    private GoogleSignInClient googleSignInClient;
    private final String TAG = "mainTag";
    private final int RESULT_CODE_SIGNIN = 999;


    //String Url ="https://sanjay.rpme.website/AndroidApi/getUser.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            finish();
            return;
        }
        Objects.requireNonNull(getSupportActionBar()).hide();

        TextView signUpText, forgetPassword;
        Button btnLognin = findViewById(R.id.loginbtn);
        signUpText = findViewById(R.id.textViewSignUp);
        forgetPassword = findViewById(R.id.textViewForgetPassword);
        signInButton = findViewById(R.id.sign_in_button);
        CheckBox checkBox = findViewById(R.id.checkbox);

        //Configure sign-in to request the user's ID, email address, and basic profile. ID and basic profile are included in DEFAULT_SIGN+IN.

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();

        // Build a GoogleSignINClint with the options specified by googleSignInOptions
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        //Set on click for Google Sign-in
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInM();
            }
        });

        Intent signUpIntent = new Intent(this, SignupActivity.class);
        Intent forgetIntent = new Intent(this, ForgetPasswordActivity.class);


        btnLognin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticateUser();
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(forgetIntent);
            }
        });
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(signUpIntent);
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                checkBox.setBackgroundColor(Color.parseColor("#cbff75"));
            }
        });
    }
    private void authenticateUser(){
        EditText etUserEmail, etLoginPassword;
        etUserEmail = findViewById(R.id.userEmail);
        etLoginPassword = findViewById(R.id.password);

        String email = etUserEmail.getText().toString().trim();
        String password = etLoginPassword.getText().toString().trim();

        if(email.isEmpty()){
            etUserEmail.setError("Email is required");
            etUserEmail.requestFocus();
            return;
        }if(password.isEmpty()){
            etLoginPassword.setError("Password is required");
            etLoginPassword.requestFocus();
            return;
        }if(password.length() < 6  ){
            etLoginPassword.setError("Password must be greater than 6 characters");
            etLoginPassword.requestFocus();
            return;
        }

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);
                        if(task.isSuccessful()){
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            assert user != null;
                            if(user.isEmailVerified()){
                                showDashboard();
                            }
                            else{
                                user.sendEmailVerification();
                                Toast.makeText(SignInActivity.this, "Check your email to verify your account", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(SignInActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    private void showDashboard(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    private void signInM(){
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RESULT_CODE_SIGNIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_CODE_SIGNIN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try{
            GoogleSignInAccount googleSignInAccount = task.getResult(ApiException.class);
            Toast.makeText(this, "Signed In Successfully", Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(googleSignInAccount);
        }catch (ApiException e){
            e.printStackTrace();
            Toast.makeText(this, "SignIn Failed", Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount googleSignInAccount) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignInActivity.this, "Successul", Toast.LENGTH_SHORT).show();
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
//                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    showDashboard();
                    UpdateUI();
                }else{
                    Toast.makeText(SignInActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    UpdateUI();
                }
            }
        });
    }
    private void UpdateUI(){
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(googleSignInAccount != null){
            String personName = googleSignInAccount.getDisplayName();
            String email = googleSignInAccount.getEmail();
//            User user = new User(personName, email);
            Toast.makeText(this, "Person Name "+ personName , Toast.LENGTH_SHORT).show();
        }
    }
}