package com.udinus.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.udinus.project.R;

import androidx.appcompat.app.AppCompatActivity;

public class ResetActivity extends AppCompatActivity {

    // Deklarasi variabel editNewPassword dengan tipe EditText
    EditText editNewPassword;

    // Deklarasi variabel editConfirmPassword dengan tipe EditText
    EditText editConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);


        // Binding edt_new_password ke variabel editNewPassword
        editNewPassword = findViewById(R.id.regpassword);

        // Binding edt_confirm_password ke variabel editConfirmPassword
        editConfirmPassword = findViewById(R.id.reg_password);
    }

    public void postChangePassword (View view){
        // Validasi New Password kosong

                // Validasi input New Password kosong
                if(TextUtils.isEmpty(editNewPassword.getText().toString())) {
                    Toast.makeText(view.getContext(),"Password Tidak Boleh Kosong!", Toast.LENGTH_LONG).show();
                }else

                    // Validasi inputan Confirm Password
                    if(!editConfirmPassword.getText().toString().equals(editNewPassword.getText().toString())) {
                        Toast.makeText(view.getContext(), "Confirm Password Tidak Sesuai!", Toast.LENGTH_LONG).show();
                    }

                    else {
                        Toast.makeText(view.getContext(), "Reset Password Berhasil.", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(ResetActivity.this, LoginActivity.class);
                        startActivity(i);
                    }
    }
}