package com.udinus.project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.udinus.project.R;

public class HotelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
    }

    public void bookKamarHotel(View v) {
        Intent i = new Intent(this, BookHotelActivity.class);
        startActivity(i);
    }

    public void hargaKamarHotel(View v) {
        Intent i = new Intent(this, Harga_Hotel.class);
        startActivity(i);
    }

    public void tipsHotel(View v) {
        Intent i = new Intent(this, TipsHotelActivity.class);
        startActivity(i);
    }

    public void historyHotel(View v) {
        Intent i = new Intent(this, HistoryHotel.class);
        startActivity(i);
    }

    public void toDashboard(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}