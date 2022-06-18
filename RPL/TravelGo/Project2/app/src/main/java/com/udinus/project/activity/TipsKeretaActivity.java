package com.udinus.project.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.udinus.project.R;
import com.udinus.project.adapter.DataAdapter;
import com.udinus.project.model.DataModel;

import java.util.ArrayList;

public class TipsKeretaActivity extends AppCompatActivity {

    public DataAdapter dataAdapter;
    public RecyclerView recyclerView;
    public ArrayList<DataModel> dataModelArrayList = new ArrayList<DataModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_kereta);

        //membuat data yang akan ditampilkan dalam list
        //file .html mengambil di folder assets
        inputData("7 Tips Travelling dengan Kereta Api agar selalu Nyaman dan Aman", "artikel_kereta_1.html");
        inputData("Tips Agar Nyaman Sebelum Travelling", "artikel_kereta_2.html");
        inputData("Tips agar Perjalanan Kereta Anda menjadi Menyenangkan", "artikel_kereta_3.html");
        inputData("5 Kemudahan Liburan dengan Kereta Api", "artikel_kereta_4.html");
        inputData("Tips Liburan Naik Kereta Api yang Penting Anda Tahu", "artikel_kereta_5.html");
        inputData("Syarat Naik Kereta Api Jarak Jauh, Mulai Hari Ini Wajib Antigen", "artikel_kereta_6.html");
        inputData("Begini Cara Traveling Naik Kereta Api di Masa Covid-19  ", "artikel_kereta_7.html");
        inputData("5 Syarat Wajib Naik Kereta Api Jarak Jauh di Masa New Normal", "artikel_kereta_8.html");
        inputData("Ini Syarat Naik Kereta Api Masa Perpanjangan PPKM Mulai 31 Agustus", "artikel_kereta_9.html");
        inputData("Catat! Ini 12 Syarat & Ketentuan Naik Kereta Api Jarak Jauh/Dekat Saat New Normal", "artikel_kereta_10.html");
        inputData("6 Cara Menghindari Mual Saat Travelling Naik Kereta Api", "artikel_kereta_11.html");
        inputData("Simak! Panduan untuk Ibu Hamil agar bisa Nyaman dan Aman Bepergian Naik Kereta", "artikel_kereta_12.html");
        inputData("Tips dan Panduan Saat Ibu Hamil Naik Kereta. Memang Boleh?", "artikel_kereta_13.html");
        inputData("Hal yang Harus Dihindari Ibu Hamil Naik Kereta", "artikel_kereta_14.html");
        inputData("7 Tips Aman Traveling dengan Kereta Saat Hamil Muda", "artikel_kereta_15.html");

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