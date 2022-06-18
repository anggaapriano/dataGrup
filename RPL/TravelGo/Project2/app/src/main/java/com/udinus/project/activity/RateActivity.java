package com.udinus.project.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.udinus.project.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RateActivity extends AppCompatActivity {
    EditText xkesan, xkomentar;
    Button tblAdd;
    DatabaseReference reff;
    User user;

    public void toDashboard(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_app);

        xkesan = (EditText)findViewById(R.id.tulis2);
        xkomentar = (EditText) findViewById(R.id.tulis);
        tblAdd = (Button) findViewById(R.id.kirim);

        user = new User();
        reff = FirebaseDatabase.getInstance().getReference().child("User");

        tblAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.SetKesan(xkesan.getText().toString().trim());
                user.SetKomentar(xkomentar.getText().toString().trim());
                reff.push().setValue(user);
                Toast.makeText(RateActivity.this, "Data Terkirim",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}