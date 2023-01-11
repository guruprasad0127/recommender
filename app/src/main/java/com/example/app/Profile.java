package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Profile extends AppCompatActivity {
    Context context;

    public Profile(){
        super();
    }
    public Profile(Context context){
        context=this.context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        RelativeLayout profiletab, newstab, booktab;

        profiletab=findViewById(R.id.profiletab);
        newstab=findViewById(R.id.newstab);
        booktab=findViewById(R.id.booktab);

        profiletab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, Profile.class);
                startActivity(i);
            }
        });
        newstab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, Newsexplore.class);
                startActivity(i);
            }
        });
        booktab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, Bookfeed.class);
                startActivity(i);
            }
        });
    }

}