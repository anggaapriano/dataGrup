package com.udinus.project.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.udinus.project.R;
import com.udinus.project.session.SessionManager;

public class MainActivity extends AppCompatActivity {

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();
    }

    public void profileMenu(View v) {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    public void pesawat(View v) {
        Intent i = new Intent(this, PesawatActivity.class);
        startActivity(i);
    }

    public void kereta(View v) {
        Intent i = new Intent(this, KeretaActivity.class);
        startActivity(i);
    }

    public void hotel(View v) {
        Intent i = new Intent(this, HotelActivity.class);
        startActivity(i);
    }

    public void about(View view) {
        Intent i = new Intent(this, AboutActivity.class);
        startActivity(i);
    }

    public void rateApp(View view) {
        Intent i = new Intent (this, RateActivity.class);
        startActivity(i);
    }
}
