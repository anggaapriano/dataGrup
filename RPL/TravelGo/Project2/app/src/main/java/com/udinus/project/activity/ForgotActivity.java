package com.udinus.project.activity;

import android.content.Intent;
import com.udinus.project.R;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotActivity extends AppCompatActivity {

    EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        editTextEmail = findViewById(R.id.reg_email);
    }

    public void postSendRequest (View view){
        if(TextUtils.isEmpty(editTextEmail.getText().toString().trim())){
            Toast.makeText(view.getContext(), "Email Tidak Boleh Kosong!", Toast.LENGTH_LONG).show();
        }

        // Validasi inputan tipe email
        else
        if (!isValidEmail(editTextEmail.getText().toString().trim())){
            Toast.makeText(view.getContext(), "Email tidak Valid", Toast.LENGTH_LONG).show();
        }
        else {
            Intent i = new Intent(ForgotActivity.this, ResetActivity.class);
            startActivity(i);
        }
    }

    public static boolean isValidEmail (CharSequence email){
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}