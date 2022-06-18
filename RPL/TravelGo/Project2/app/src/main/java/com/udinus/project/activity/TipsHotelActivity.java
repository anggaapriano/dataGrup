package com.udinus.project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.udinus.project.R;
import com.udinus.project.adapter.DataAdapter;
import com.udinus.project.model.DataModel;

import java.util.ArrayList;

public class TipsHotelActivity extends AppCompatActivity {

    public DataAdapter dataAdapter;
    public RecyclerView recyclerView;
    public ArrayList<DataModel> dataModelArrayList = new ArrayList<DataModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_hotel);

        //membuat data yang akan ditampilkan dalam list
        //file .html mengambil di folder assets
        inputData("5 Tips Memilih Hotel Long Stay Tepat untuk Perjalanan Bisnis Anda", "artikel_hotel_1.html");
        inputData("4 Tips Hemat Menginap di Hotel selama Liburan", "artikel_hotel_2.html");
        inputData("10 Tips Aman Menginap di Hotel, Perhatikan yang Harus dan Jangan Dilakukan", "artikel_hotel_3.html");
        inputData("5 Tips Sederhana Menginap di Hotel agar Lebih Nyaman dan Menyenangkan", "artikel_hotel_4.html");
        inputData("Pesan Hotel Yang Benar? Baca Dulu Tips Sebelum Booking Hotel Ini!", "artikel_hotel_5.html");
        inputData("Catat! 5 Tips Penting Pilih Hotel untuk Staycation di Masa Pandemi!", "artikel_hotel_6.html");
        inputData("3 Tips Mendapatkan Hotel yang Pas untuk Liburan", "artikel_hotel_7.html");
        inputData("Tips Menginap di Hotel untuk Pemula", "artikel_hotel_8.html");
        inputData("5 Rahasia Mendapatkan Hotel Murah Saat Traveling", "artikel_hotel_9.html");
        inputData("Tips Hemat Staycation di Hotel, Anti Kantong Bolong!", "artikel_hotel_10.html");
        inputData("7 Panduan Memilih Hotel Terbaik untuk Staycation", "artikel_hotel_11.html");
        inputData("Ingin Berlibur Singkat dengan Menginap di Hotel? Berikut Tips Menginap Murah di Hotel", "artikel_hotel_12.html");
        inputData("6 Tips Menginap Dengan Aman di Hotel Selama Pandemi", "artikel_hotel_13.html");
        inputData("6 Tips Memilih Hotel untuk Perjalanan Bisnis", "artikel_hotel_14.html");
        inputData("4 Tips Memilih Hotel Staycation yang Aman dan Nyaman saat Pandemi", "artikel_hotel_15.html");

        //menampilkan data ke dalam recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        dataAdapter = new DataAdapter(this, dataModelArrayList);
        recyclerView.setAdapter(dataAdapter);

        /*//menambahakan header
        DataModel headerModel = new DataModel();
        headerModel.setViewType(2);
        dataModelArrayList.add(0, headerModel);*/

        //menambahkan footer
        DataModel footerModel = new DataModel();
        footerModel.setViewType(2);
        dataModelArrayList.add(footerModel);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

    }

    //fungsi input
    public void inputData(String judul, String konten) {
        DataModel dataModel = new DataModel();
        dataModel.setJudul(judul);
        dataModel.setKonten(konten);
        dataModel.setViewType(1);
        dataModelArrayList.add(dataModel);
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
