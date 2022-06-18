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

public class TipsPesawatActivity extends AppCompatActivity {

    public DataAdapter dataAdapter;
    public RecyclerView recyclerView;
    public ArrayList<DataModel> dataModelArrayList = new ArrayList<DataModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_pesawat);

        //membuat data yang akan ditampilkan dalam list
        //file .html mengambil di folder assets
        inputData("Tips & Tata Cara Naik Pesawat buat Pemula", "artikel_1.html");
        inputData("5 Tips Pilih Pakaian yang Cocok untuk Naik Pesawat", "artikel_2.html");
        inputData("7 Tips Aman Naik Pesawat saat Pandemi", "artikel_3.html");
        inputData("Tips Membuat Si Kecil Tetap Tenang Saat Naik Pesawat", "artikel_4.html");
        inputData("12 Tips Naik Pesawat Bersama Anak", "artikel_5.html");
        inputData("10 Tips Aman Bawa Anak Kecil Naik Pesawat saat Pandemi", "artikel_6.html");
        inputData("Tips Pertama Kali Naik Pesawat ke Luar Negeri Biar Makin Nyaman  ", "artikel_7.html");
        inputData("Tata Cara Transit Penerbangan Internasional, Simak Disini!", "artikel_8.html");
        inputData("7 Tips Cara Ampuh Menghindari Jet Lag saat Traveling ke Luar Negeri", "artikel_9.html");
        inputData("4 Cara untuk Menghindari Sakit Kepala Saat Naik Pesawat", "artikel_10.html");
        inputData("7 Cara Atasi Telinga Sakit saat Naik Pesawat Alias Barotrauma yang Efektif", "artikel_11.html");
        inputData("Menghilangkan tekanan udara di telinga saat naik pesawat", "artikel_12.html");
        inputData("Tips Aman Naik Pesawat Saat Hamil", "artikel_13.html");
        inputData("Tips untuk Ibu Hamil yang Akan Naik Pesawat", "artikel_14.html");
        inputData("Bolehkah Mama Hamil Naik Pesawat ?", "artikel_15.html");

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
