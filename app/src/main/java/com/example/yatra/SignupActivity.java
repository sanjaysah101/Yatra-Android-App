package com.example.yatra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private EditText textInputEditTextFirstName, textInputEditTextLastName, textInputEditTextEmail, textInputEditTextPassword, textInputEditTextConfirmPassword;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        Objects.requireNonNull(getSupportActionBar()).hide(); //Hide Title Bar

        TextView signInTextBtn;
        signInTextBtn = findViewById(R.id.signInTextBtn);
        Button signUpBtn = findViewById(R.id.signUpBtn);
        textInputEditTextFirstName = findViewById(R.id.userFirstNames);
        textInputEditTextLastName = findViewById(R.id.userLastName);
        textInputEditTextEmail = findViewById(R.id.userEmail);
        textInputEditTextPassword = findViewById(R.id.password);
        textInputEditTextConfirmPassword = findViewById(R.id.confirmPassword);
        progressBar = findViewById(R.id.progressBar);

        Intent signInIntent = new Intent(this, SignInActivity.class);

        signInTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(signInIntent);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }
    public void registerUser(){
        String fname, lname, email, password, confirmPassword;
        fname = textInputEditTextFirstName.getText().toString().trim();
        lname = textInputEditTextLastName.getText().toString().trim();
        email = textInputEditTextEmail.getText().toString().trim();
        password = textInputEditTextPassword.getText().toString().trim();
        confirmPassword = textInputEditTextConfirmPassword.getText().toString().trim();

        if(fname.isEmpty()){
            textInputEditTextFirstName.setError("First name is required");
            textInputEditTextFirstName.requestFocus();
            return;
        }if(lname.isEmpty()){
            textInputEditTextFirstName.setError("First name is required");
            textInputEditTextFirstName.requestFocus();
            return;
        }
        if(email.isEmpty()){
            textInputEditTextEmail.setError("Email is required");
            textInputEditTextEmail.requestFocus();
            return;
        }if(password.isEmpty()){
            textInputEditTextPassword.setError("Password is required");
            textInputEditTextPassword.requestFocus();
            return;
        }if(confirmPassword.isEmpty()){
            textInputEditTextConfirmPassword.setError("Confirm password is required");
            textInputEditTextConfirmPassword.requestFocus();
            return;
        }if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            textInputEditTextConfirmPassword.setError("Invalid email Address");
            textInputEditTextConfirmPassword.requestFocus();
            return;
        }if(password.length() < 6  ){
            textInputEditTextPassword.setError("Password must be greater than 6 characters");
            textInputEditTextConfirmPassword.requestFocus();
            return;
        }if(!confirmPassword.equals(password)){
            textInputEditTextConfirmPassword.setError("Confirm password did not match with password");
            textInputEditTextConfirmPassword.requestFocus();
            return;
        }

//        Toast.makeText(this, fname+" "+lname +" "+ email+" " +password + " "+confirmPassword, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.VISIBLE);
        mAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User(fname, lname, email);
                    FirebaseDatabase.getInstance().getReference("Users").child(Objects.requireNonNull(FirebaseAuth.getInstance()
                                    .getCurrentUser()).getUid()).setValue(user)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignupActivity.this, "User has been register successfully", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                            else{
                                Toast.makeText(SignupActivity.this, "Sign Up failed! Try again!", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }else{
                    Toast.makeText(SignupActivity.this, "Sign Up failed! Try again!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
//                if(task.isSuccessful()){
//                    Toast.makeText(SignupActivity.this, "User has been register successfully", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(SignupActivity.this, "Sign Up failed! Try again!", Toast.LENGTH_SHORT).show();
//                }

            }
        });


    }
}
