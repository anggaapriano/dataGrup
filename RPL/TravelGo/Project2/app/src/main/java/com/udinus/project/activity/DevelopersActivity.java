package com.udinus.project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.udinus.project.R;

public class DevelopersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
    }

    public void profileReyhan(View v) {
        Intent i = new Intent(this, Profile1.class);
        startActivity(i);
    }


    public void profileBimo(View v) {
        Intent i = new Intent(this, Profile2.class);
        startActivity(i);
    }

    public void profileSandro(View v) {
        Intent i = new Intent(this, Profile3.class);
        startActivity(i);
    }

    public void profileAngga(View v) {
        Intent i = new Intent(this, Profile4.class);
        startActivity(i);
    }

    public void toAbout(View v) {
        Intent i = new Intent(this, AboutActivity.class);
        startActivity(i);
    }
}