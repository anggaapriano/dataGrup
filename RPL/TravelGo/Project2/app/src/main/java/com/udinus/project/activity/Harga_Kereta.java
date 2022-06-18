package com.udinus.project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.udinus.project.R;
import com.udinus.project.adapter.AdapterFitur;

public class Harga_Kereta extends AppCompatActivity {

    ListView lv;

    String[] Headline = {
            "Bandung - Jakarta",
            "Bandung - Semarang",
            "Bandung - Surakarta",
            "Bandung - Yogyakarta",

            "Jakarta - Bandung",
            "Jakarta - Semarang",
            "Jakarta - Surakarta",
            "Jakarta - Yogyakarta",

            "Semarang - Bandung",
            "Semarang - Jakarta",
            "Semarang - Surakarta",
            "Semarang - Yogyakarta",

            "Surakarta - Bandung",
            "Surakarta - Jakarta",
            "Surakarta - Semarang",
            "Surakarta - Yogyakarta",

            "Yogyakarta - Bandung",
            "Yogyakarta - Jakarta",
            "Yogyakarta - Semarang",
            "Yogyakarta - Surakarta"
    };
    String[] Subhead = {
            "Dewasa = Rp. 100.000,00        \nAnak      = Rp. 80.000,00",
            "Dewasa = Rp. 300.000,00        \nAnak      = Rp. 250.000,00",
            "Dewasa = Rp. 400.000,00        \nAnak      = Rp. 350.000,00",
            "Dewasa = Rp. 450.000,00        \nAnak      = Rp. 400.000,00",

            "Dewasa = Rp. 100.000,00        \nAnak      = Rp. 80.000,00",
            "Dewasa = Rp. 300.000,00        \nAnak      = Rp. 250.000,00",
            "Dewasa = Rp. 400.000,00        \nAnak      = Rp. 350.000,00",
            "Dewasa = Rp. 450.000,00        \nAnak      = Rp. 400.000,00",

            "Dewasa = Rp. 300.000,00        \nAnak      = Rp. 250.000,00",
            "Dewasa = Rp. 300.000,00        \nAnak      = Rp. 250.000,00",
            "Dewasa = Rp. 100.000,00        \nAnak      = Rp. 70.000,00",
            "Dewasa = Rp. 150.000,00        \nAnak      = Rp. 100.000,00",

            "Dewasa = Rp. 400.000,00        \nAnak      = Rp. 350.000,00",
            "Dewasa = Rp. 400.000,00        \nAnak      = Rp. 350.000,00",
            "Dewasa = Rp. 100.000,00        \nAnak      = Rp. 70.000,00",
            "Dewasa = Rp. 50.000,00         \nAnak      = Rp. 40.000,00",

            "Dewasa = Rp. 100.000,00        \nAnak      = Rp. 80.000,00",
            "Dewasa = Rp. 450.000,00        \nAnak      = Rp. 400.000,00",
            "Dewasa = Rp. 150.000,00        \nAnak      = Rp. 100.000,00",
            "Dewasa = Rp. 50.000,00         \nAnak      = Rp. 40.000,00"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harga_kereta);

        lv = findViewById(R.id.listView);
        AdapterFitur adapterFitur = new AdapterFitur(this, Headline, Subhead);
        lv.setAdapter(adapterFitur);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),
                        Headline[position],
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void toKereta(View view) {
        Intent i = new Intent(this, KeretaActivity.class);
        startActivity(i);
    }
}