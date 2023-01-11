package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import  java.util.regex.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText Fullname, Email, Password, Phone;
    Button RegisterButton;
    TextView gotoLoginButton;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Fullname        = findViewById(R.id.fullname);
        Email           = findViewById(R.id.email);
        Password        = findViewById(R.id.password);
        Phone           = findViewById(R.id.phone);
        RegisterButton  = findViewById(R.id.registerbutton);
        gotoLoginButton = findViewById(R.id.remark1);

        fAuth= FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email is required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Email.setError("Password cannot be empty");
                    return;
                }
                if (checkpasswordformat(password)) {
                    Password.setError("Password must contain at least 8 characters and at most 20 characters.\n" +
                            "Password must contain at least one digit.\n" +
                            "Password must contain at least one special character.\n" +
                            "Password must not contain white space.");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //registering user in firebase;

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Profile.class));
                        } else {
                            Toast.makeText(Register.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        }) ;

        gotoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
    boolean checkpasswordformat(String password){
        String regex = "^(?=.*[0-9])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);

        if(m.matches()) return false;
        else return true;
    }
}