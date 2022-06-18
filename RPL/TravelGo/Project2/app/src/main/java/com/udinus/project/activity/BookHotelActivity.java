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
import com.udinus.project.database.DBHelperHotel;
import com.udinus.project.session.SessionManager;

import java.util.Calendar;
import java.util.HashMap;

public class BookHotelActivity extends AppCompatActivity {

    protected Cursor cursor;
    DBHelperHotel dbHelper;
    SQLiteDatabase db;
    Spinner spinHotel, spinKelas, spinJenisKamar, spinJumlahKamar;
    SessionManager session;
    String email;
    int id_book;
    public String sHotel, sKelas, sTanggal, sJenisKamar, sJumlahKamar;
    int jmlKamar;
    int hargaKamar;
    int hargaTotalKamar, hargaTotal;
    private EditText etTanggal;
    private DatePickerDialog dpTanggal;
    Calendar newCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_hotel);

        dbHelper = new DBHelperHotel(BookHotelActivity.this);
        db = dbHelper.getReadableDatabase();

        final String[] hotel = {
                "The Oberoi Lombok",
                "Intercontinental Bali Resort",
                "Four Seasons Resort Bali at Jimbaran Bay",
                "Amanjiwo",
                "Alila Purnama",
                "Bawah Reserve",
                "Nihi Sumba",
                "The Mulia, Nusa Dua",
                "Banyan Tree Ungasan",
                "W Retreat & Spa Bali"};
        final String[] kelas = {
                "Standard Room",
                "Superior Room",
                "Deluxe Room",
                "Suite Room",
                "Presidential Room"};
        final String[] jenis_kamar = {
                "Single Bed",
                "Double Bed"};
        final String[] jumlah_kamar = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        spinHotel = findViewById(R.id.hotel);
        spinKelas = findViewById(R.id.kelas);
        spinJenisKamar = findViewById(R.id.jenisKamar);
        spinJumlahKamar = findViewById(R.id.jumlahKamar);

        ArrayAdapter<CharSequence> adapterHotel = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, hotel);
        adapterHotel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinHotel.setAdapter(adapterHotel);

        ArrayAdapter<CharSequence> adapterKelas = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, kelas);
        adapterKelas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinKelas.setAdapter(adapterKelas);

        ArrayAdapter<CharSequence> adapterJenisKamar = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, jenis_kamar);
        adapterJenisKamar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinJenisKamar.setAdapter(adapterJenisKamar);

        ArrayAdapter<CharSequence> adapterJumlahKamar = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, jumlah_kamar);
        adapterJumlahKamar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinJumlahKamar.setAdapter(adapterJumlahKamar);

        spinHotel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sHotel = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinKelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sKelas = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinJenisKamar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sJenisKamar = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinJumlahKamar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sJumlahKamar = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btnBook = findViewById(R.id.book);

        etTanggal = findViewById(R.id.tanggal);
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
                if (sHotel != null && sKelas != null && sTanggal != null && sJenisKamar != null) {
                    if ((sHotel.equalsIgnoreCase("Metropole XXI") && sKelas.equalsIgnoreCase("Metropole XXI"))
                            || (sHotel.equalsIgnoreCase("CGV Blitz Grand Indonesia") && sKelas.equalsIgnoreCase("CGV Blitz Grand Indonesia"))
                            || (sHotel.equalsIgnoreCase("Cinemaxx Plaza Semanggi") && sKelas.equalsIgnoreCase("Cinemaxx Plaza Semanggi"))
                            || (sHotel.equalsIgnoreCase("Pejaten Village XXI") && sKelas.equalsIgnoreCase("Pejaten Village XXI"))
                            || (sHotel.equalsIgnoreCase("Cinema XXI Kuningan City") && sKelas.equalsIgnoreCase("Cinema XXI Kuningan City"))) {
                        Toast.makeText(BookHotelActivity.this, "Error !", Toast.LENGTH_LONG).show();
                    } else {
                        AlertDialog dialog = new AlertDialog.Builder(BookHotelActivity.this)
                                .setTitle("Ingin booking kamar hotel sekarang?")
                                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        try {
                                            db.execSQL("INSERT INTO TB_BOOK (hotel, kelas, tanggal, jenis_kamar, jumlah_kamar) VALUES ('" +
                                                    sHotel + "','" +
                                                    sKelas + "','" +
                                                    sTanggal + "','" +
                                                    sJenisKamar + "','" +
                                                    sJumlahKamar + "');");
                                            cursor = db.rawQuery("SELECT id_book FROM TB_BOOK ORDER BY id_book DESC", null);
                                            cursor.moveToLast();
                                            if (cursor.getCount() > 0) {
                                                cursor.moveToPosition(0);
                                                id_book = cursor.getInt(0);
                                            }
                                            db.execSQL("INSERT INTO TB_HARGA (username, id_book, harga_kamar, harga_total) VALUES ('" +
                                                    email + "','" +
                                                    id_book + "','" +
                                                    hargaTotalKamar + "','" +
                                                    hargaTotal + "');");
                                            Toast.makeText(BookHotelActivity.this, "Booking berhasil", Toast.LENGTH_LONG).show();
                                            finish();
                                        } catch (Exception e) {
                                            Toast.makeText(BookHotelActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                })
                                .setNegativeButton("Tidak", null)
                                .create();
                        dialog.show();
                    }
                } else {
                    Toast.makeText(BookHotelActivity.this, "Mohon lengkapi data pemesanan!", Toast.LENGTH_LONG).show();
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
        if (sJenisKamar.equalsIgnoreCase("single bed") && sKelas.equalsIgnoreCase("standard room")) {
            hargaKamar = 50000;
        } else if (sJenisKamar.equalsIgnoreCase("single bed") && sKelas.equalsIgnoreCase("superior room")) {
            hargaKamar = 75000;
        } else if (sJenisKamar.equalsIgnoreCase("single bed") && sKelas.equalsIgnoreCase("deluxe room")) {
            hargaKamar = 100000;
        } else if (sJenisKamar.equalsIgnoreCase("single bed") && sKelas.equalsIgnoreCase("suite room")) {
            hargaKamar = 125000;
        } else if (sJenisKamar.equalsIgnoreCase("single bed") && sKelas.equalsIgnoreCase("presidential room")) {
            hargaKamar = 150000;
        } else if (sJenisKamar.equalsIgnoreCase("double bed") && sKelas.equalsIgnoreCase("standard room")) {
            hargaKamar = 75000;
        } else if (sJenisKamar.equalsIgnoreCase("double bed") && sKelas.equalsIgnoreCase("superior room")) {
            hargaKamar = 100000;
        } else if (sJenisKamar.equalsIgnoreCase("double bed") && sKelas.equalsIgnoreCase("deluxe room")) {
            hargaKamar = 125000;
        } else if (sJenisKamar.equalsIgnoreCase("double bed") && sKelas.equalsIgnoreCase("suite room")) {
            hargaKamar = 150000;
        } else if (sJenisKamar.equalsIgnoreCase("double bed") && sKelas.equalsIgnoreCase("presidential room")) {
            hargaKamar = 200000;
        }

        jmlKamar = Integer.parseInt(sJumlahKamar);


        hargaTotal = jmlKamar * hargaKamar;
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