package com.udinus.project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.udinus.project.R;

public class KeretaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kereta);
    }

    public void bookTiketKereta(View v) {
        Intent i = new Intent(this, BookKeretaActivity.class);
        startActivity(i);
    }

    public void hargaTiketKereta(View v) {
        Intent i = new Intent(this, Harga_Kereta.class);
        startActivity(i);
    }

    public void tipsKereta(View v) {
        Intent i = new Intent(this, TipsKeretaActivity.class);
        startActivity(i);
    }

    public void historyKereta(View v) {
        Intent i = new Intent(this, HistoryKereta.class);
        startActivity(i);
    }

    public void toDashboard(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}