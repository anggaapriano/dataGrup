package com.udinus.project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.udinus.project.MapsActivity;
import com.udinus.project.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void developers(View v) {
        Intent i = new Intent(this, DevelopersActivity.class);
        startActivity(i);
    }

    public void tentangAplikasi(View v) {
        Intent i = new Intent(this, ProfileApk.class);
        startActivity(i);
    }

    public void fiturAplikasi(View v) {
        Intent i = new Intent(this, FiturApk.class);
        startActivity(i);
    }

    public void maps(View v){
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }

    public void toDashboard(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}