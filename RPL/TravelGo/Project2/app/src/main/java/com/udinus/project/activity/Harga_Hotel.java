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

public class Harga_Hotel extends AppCompatActivity {

    ListView lv;

    String[] Headline = {
            "Standard Room",
            "Superior Room",
            "Deluxe Room",
            "Suite Room",
            "Presidential Room"
    };
    String[] Subhead = {
            "Single-bed \t = Rp. 50.000,00    \nDouble-bed = Rp. 75.000,00",
            "Single-bed \t = Rp. 75.000,00    \nDouble-bed = Rp. 100.000,00",
            "Single-bed \t = Rp. 100.000,00   \nDouble-bed = Rp. 125.000,00",
            "Single-bed \t = Rp. 125.000,00   \nDouble-bed = Rp. 150.000,00",
            "Single-bed \t = Rp. 150.000,00   \nDouble-bed = Rp. 200.000,00"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harga_hotel);

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

    public void toHotel(View view) {
        Intent i = new Intent(this, HotelActivity.class);
        startActivity(i);
    }
}