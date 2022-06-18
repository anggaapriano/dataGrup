package com.udinus.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.udinus.project.R;
import com.udinus.project.adapter.AdapterFitur;

import androidx.appcompat.app.AppCompatActivity;

public class FiturApk extends AppCompatActivity {

    ListView lv;

    String[] Headline = {"Fitur booking kamar hotel", "Fitur booking tiket pesawat", "Fitur booking tiket kereta",
            "Fitur history pemesanan", "Fitur tips hotel", "Fitur tips naik kereta", "Fitur tips naik pesawat","Fitur Database","Fitur Maps"
    };
    String[] Subhead = {" Fitur booking hotel digunakan untuk memesan kamar dengan mudah dan aman.",
            "Temukan tiket pesawat dengan mudah dan cepat tanpa biaya transaksi.",
            "Temukan tiket kereta dengan mudah dan cepat tanpa biaya transaksi.",
            "Lihat jejak pemesanan dengan runtut.",
            "Berbagai tips saat ingin memesan kamar hotel.",
            "Berbagai tips saat menaiki kereta.",
            "Berbagai tips saat menaiki pesawat.",
            "Menggunakan SQlite dan juga Firebase.",
            "Menggunakan LBS untuk menampilkan peta yang berada di Universitas Dian Nuswantoro Semarang."
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitur_aplikasi);

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
    public void toAbout(View view) {
        Intent i = new Intent(this, AboutActivity.class);
        startActivity(i);
    }


}
