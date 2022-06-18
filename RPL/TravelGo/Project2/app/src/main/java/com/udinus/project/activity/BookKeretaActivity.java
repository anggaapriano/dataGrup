package com.udinus.project.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.udinus.project.R;
import com.udinus.project.database.DBBookKereta;
import com.udinus.project.session.SessionManager;

import java.util.Calendar;
import java.util.HashMap;

public class BookKeretaActivity extends AppCompatActivity {

    protected Cursor cursor;
    DBBookKereta dbHelper;
    SQLiteDatabase db;
    Spinner spinAsal, spinTujuan, spinDewasa, spinAnak;
    SessionManager session;
    String email;
    int id_book;
    public String sAsal, sTujuan, sTanggal, sDewasa, sAnak;
    int jmlDewasa, jmlAnak;
    int hargaDewasa, hargaAnak;
    int hargaTotalDewasa, hargaTotalAnak, hargaTotal;
    private EditText etTanggal;
    private DatePickerDialog dpTanggal;
    Calendar newCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_kereta);

        dbHelper = new DBBookKereta(BookKeretaActivity.this);
        db = dbHelper.getReadableDatabase();

        final String[] asal = {
                "Puntadewa",
                "Werkudara",
                "Arjuna",
                "Nakula",
                "Sadewa"};

        final String[] tujuan = {
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
                "Yogyakarta - Surakarta"};

        final String[] dewasa = {"0", "1", "2", "3", "4", "5"};
        final String[] anak = {"0", "1", "2", "3", "4", "5"};

        spinAsal = findViewById(R.id.asal);
        spinTujuan = findViewById(R.id.tujuan);
        spinDewasa = findViewById(R.id.dewasa);
        spinAnak = findViewById(R.id.anak);

        ArrayAdapter<CharSequence> adapterAsal = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, asal);
        adapterAsal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAsal.setAdapter(adapterAsal);

        ArrayAdapter<CharSequence> adapterTujuan = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, tujuan);
        adapterTujuan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTujuan.setAdapter(adapterTujuan);

        ArrayAdapter<CharSequence> adapterDewasa = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, dewasa);
        adapterDewasa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDewasa.setAdapter(adapterDewasa);

        ArrayAdapter<CharSequence> adapterAnak = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, anak);
        adapterAnak.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAnak.setAdapter(adapterAnak);

        spinAsal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sAsal = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinTujuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sTujuan = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinDewasa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sDewasa = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinAnak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sAnak = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btnBook = findViewById(R.id.book);

        etTanggal = findViewById(R.id.tanggal_berangkat);
        etTanggal.setInputType(InputType.TYPE_NULL);
        etTanggal.requestFocus();
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        email = user.get(SessionManager.KEY_EMAIL);
        setDateTimeField();

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perhitunganHarga();
                if (sAsal != null && sTujuan != null && sTanggal != null && sDewasa != null) {
                    if ((sAsal.equalsIgnoreCase("Yogyakarta") && sTujuan.equalsIgnoreCase("Yogyakarta"))
                            || (sAsal.equalsIgnoreCase("bandung") && sTujuan.equalsIgnoreCase("bandung"))
                            || (sAsal.equalsIgnoreCase("purwokerto") && sTujuan.equalsIgnoreCase("purwokerto"))
                            || (sAsal.equalsIgnoreCase("yogyakarta") && sTujuan.equalsIgnoreCase("yogyakarta"))
                            || (sAsal.equalsIgnoreCase("surabaya") && sTujuan.equalsIgnoreCase("surabaya"))) {
                        Toast.makeText(BookKeretaActivity.this, "Asal dan Tujuan tidak boleh sama !", Toast.LENGTH_LONG).show();
                    } else {
                        AlertDialog dialog = new AlertDialog.Builder(BookKeretaActivity.this)
                                .setTitle("Ingin booking tiket kereta sekarang?")
                                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        try {
                                            db.execSQL("INSERT INTO TB_BOOK (asal, tujuan, tanggal, dewasa, anak) VALUES ('" +
                                                    sAsal + "','" +
                                                    sTujuan + "','" +
                                                    sTanggal + "','" +
                                                    sDewasa + "','" +
                                                    sAnak + "');");
                                            cursor = db.rawQuery("SELECT id_book FROM TB_BOOK ORDER BY id_book DESC", null);
                                            cursor.moveToLast();
                                            if (cursor.getCount() > 0) {
                                                cursor.moveToPosition(0);
                                                id_book = cursor.getInt(0);
                                            }
                                            db.execSQL("INSERT INTO TB_HARGA (username, id_book, harga_dewasa, harga_anak, harga_total) VALUES ('" +
                                                    email + "','" +
                                                    id_book + "','" +
                                                    hargaTotalDewasa + "','" +
                                                    hargaTotalAnak + "','" +
                                                    hargaTotal + "');");
                                            Toast.makeText(BookKeretaActivity.this, "Booking berhasil", Toast.LENGTH_LONG).show();
                                            finish();
                                        } catch (Exception e) {
                                            Toast.makeText(BookKeretaActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                })
                                .setNegativeButton("Tidak", null)
                                .create();
                        dialog.show();
                    }
                } else {
                    Toast.makeText(BookKeretaActivity.this, "Mohon lengkapi data pemesanan!", Toast.LENGTH_LONG).show();
                }
            }
        });

        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbKrl);
        toolbar.setTitle("Form Booking");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void perhitunganHarga() {
        if (sTujuan.equalsIgnoreCase("Bandung - Jakarta")) {
            hargaDewasa = 100000;
            hargaAnak = 80000;
        } else if (sTujuan.equalsIgnoreCase("Bandung - Semarang")) {
            hargaDewasa = 300000;
            hargaAnak = 250000;
        } else if (sTujuan.equalsIgnoreCase("Bandung - Surakarta")) {
            hargaDewasa = 400000;
            hargaAnak = 350000;
        } else if (sTujuan.equalsIgnoreCase("Bandung - Yogyakarta")) {
            hargaDewasa = 450000;
            hargaAnak = 400000;
        }

        else if (sTujuan.equalsIgnoreCase("Jakarta - Bandung")) {
            hargaDewasa = 100000;
            hargaAnak = 80000;
        } else if (sTujuan.equalsIgnoreCase("Jakarta - Semarang")) {
            hargaDewasa = 300000;
            hargaAnak = 250000;
        } else if (sTujuan.equalsIgnoreCase("Jakarta - Surakarta")) {
            hargaDewasa = 400000;
            hargaAnak = 350000;
        } else if (sTujuan.equalsIgnoreCase("Jakarta - Yogyakarta")) {
            hargaDewasa = 450000;
            hargaAnak = 400000;
        }

        else if (sTujuan.equalsIgnoreCase("Semarang - Bandung")) {
            hargaDewasa = 300000;
            hargaAnak = 250000;
        } else if (sTujuan.equalsIgnoreCase("Semarang - Jakarta")) {
            hargaDewasa = 300000;
            hargaAnak = 250000;
        } else if (sTujuan.equalsIgnoreCase("Semarang - Surakarta")) {
            hargaDewasa = 100000;
            hargaAnak = 70000;
        } else if (sTujuan.equalsIgnoreCase("Semarang - Yogyakarta")) {
            hargaDewasa = 150000;
            hargaAnak = 100000;
        }

        else if (sTujuan.equalsIgnoreCase("Surakarta - Bandung")) {
            hargaDewasa = 400000;
            hargaAnak = 350000;
        } else if (sTujuan.equalsIgnoreCase("Surakarta - Jakarta")) {
            hargaDewasa = 400000;
            hargaAnak = 350000;
        } else if (sTujuan.equalsIgnoreCase("Surakarta - Semarang")) {
            hargaDewasa = 100000;
            hargaAnak = 70000;
        } else if (sTujuan.equalsIgnoreCase("Surakarta - Yogyakarta")) {
            hargaDewasa = 50000;
            hargaAnak = 40000;
        }

        else if (sTujuan.equalsIgnoreCase("Yogyakarta - Bandung")) {
            hargaDewasa = 100000;
            hargaAnak = 80000;
        } else if (sTujuan.equalsIgnoreCase("Yogyakarta - Jakarta")) {
            hargaDewasa = 450000;
            hargaAnak = 400000;
        } else if (sTujuan.equalsIgnoreCase("Yogyakarta - Semarang")) {
            hargaDewasa = 150000;
            hargaAnak = 100000;
        } else if (sTujuan.equalsIgnoreCase("Yogyakarta - Surakarta")) {
            hargaDewasa = 50000;
            hargaAnak = 40000;
        }

        jmlDewasa = Integer.parseInt(sDewasa);
        jmlAnak = Integer.parseInt(sAnak);

        hargaTotalDewasa = jmlDewasa * hargaDewasa;
        hargaTotalAnak = jmlAnak * hargaAnak;
        hargaTotal = hargaTotalDewasa + hargaTotalAnak;
    }

    private void setDateTimeField() {
        etTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggal.show();
            }
        });

        dpTanggal = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                String[] bulan = {"Januari", "Februari", "Maret", "April", "Mei",
                        "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
                sTanggal = dayOfMonth + " " + bulan[monthOfYear] + " " + year;
                etTanggal.setText(sTanggal);

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
}