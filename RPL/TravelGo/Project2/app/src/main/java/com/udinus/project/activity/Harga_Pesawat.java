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

public class Harga_Pesawat extends AppCompatActivity {

    ListView lv;

    String[] Headline = {
            "Ambon - Banjarmasin",
            "Ambon - Denpasar   ",
            "Ambon - Gorontalo",
            "Ambon - Jakarta",

            "Banjarmasin - Ambon",
            "Banjarmasin - Denpasar",
            "Banjarmasin - Gorontalo",
            "Banjarmasin - Jakarta",

            "Denpasar - Ambon",
            "Denpasar - Banjarmasin",
            "Denpasar - Gorontalo",
            "Denpasar - Jakarta",

            "Gorontalo - Ambon",
            "Gorontalo - Banjarmasin",
            "Gorontalo - Denpasar",
            "Gorontalo - Jakarta",

            "Jakarta - Ambon",
            "Jakarta - Banjarmasin",
            "Jakarta - Denpasar",
            "Jakarta - Gorontalo"
    };
    String[] Subhead = {
            "Dewasa = Rp. 1.000.000,00      \nAnak      = Rp. 800.000,00",
            "Dewasa = Rp. 1.100.000,00      \nAnak      = Rp. 850.000,00",
            "Dewasa = Rp. 800.000,00        \nAnak      = Rp. 600.000,00",
            "Dewasa = Rp. 1.000.000,00      \nAnak      = Rp. 800.000,00",

            "Dewasa = Rp. 1.000.000,00      \nAnak      = Rp. 800.000,00",
            "Dewasa = Rp. 1.000.000,00      \nAnak      = Rp. 800.000,00",
            "Dewasa = Rp. 900.000,00        \nAnak      = Rp. 750.000,00",
            "Dewasa = Rp. 1.000.000,00      \nAnak      = Rp. 800.000,00",

            "Dewasa = Rp. 1.100.000,00      \nAnak      = Rp. 850.000,00",
            "Dewasa = Rp. 1.000.000,00      \nAnak      = Rp. 800.000,00",
            "Dewasa = Rp. 1.000.000,00      \nAnak      = Rp. 800.000,00",
            "Dewasa = Rp. 1.100.000,00      \nAnak      = Rp. 900.000,00",

            "Dewasa = Rp. 800.000,00        \nAnak      = Rp. 700.000,00",
            "Dewasa = Rp. 900.000,00        \nAnak      = Rp. 750.000,00",
            "Dewasa = Rp. 1.000.000,00      \nAnak      = Rp. 800.000,00",
            "Dewasa = Rp. 1.200.000,00      \nAnak      = Rp. 1.000.000,00",

            "Dewasa = Rp. 1.000.000,00      \nAnak      = Rp. 800.000,00",
            "Dewasa = Rp. 1.000.000,00      \nAnak      = Rp. 800.000,00",
            "Dewasa = Rp. 1.100.000,00      \nAnak      = Rp. 900.000,00",
            "Dewasa = Rp. 1.200.000,00      \nAnak      = Rp. 1.000.000,00"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harga_pesawat);

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

    public void toPesawat(View view) {
        Intent i = new Intent(this, PesawatActivity.class);
        startActivity(i);
    }
}