package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText Email, Password;
    Button LoginButton;
    TextView CreateNewAcc;
    ProgressBar Progressbar;
    FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        Progressbar=findViewById(R.id.progressBar);
        Auth=FirebaseAuth.getInstance();

        LoginButton=findViewById(R.id.loginbutton);
        CreateNewAcc=findViewById(R.id.linktoregister);

        LoginButton.setOnClickListener(new View.OnClickListener() {
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
                if (password.length() < 8) {
                    Password.setError("Password must be at least 8 characters.");
                    return;
                }

                Progressbar.setVisibility(View.VISIBLE);
                //authenticating the user
                Auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Logged in Sucessfuly!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                    } else {
                        Toast.makeText(Login.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        Progressbar.setVisibility(View.GONE);
                    }
                });
            }
        });
        CreateNewAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

    }
}