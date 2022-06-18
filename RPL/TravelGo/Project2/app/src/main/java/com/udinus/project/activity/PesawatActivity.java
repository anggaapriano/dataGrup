package com.udinus.project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.udinus.project.R;

public class PesawatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesawat);


    }

    public void bookTiketPesawat(View v) {
        Intent i = new Intent(this, BookPesawatActivity.class);
        startActivity(i);
    }

    public void hargaTiketPesawat(View v) {
        Intent i = new Intent(this, Harga_Pesawat.class);
        startActivity(i);
    }

    public void tipsPesawat(View v) {
        Intent i = new Intent(this, TipsPesawatActivity.class);
        startActivity(i);
    }

    public void historyPesawat(View v) {
        Intent i = new Intent(this, HistoryPesawat.class);
        startActivity(i);
    }

    public void toDashboard(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}