package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SupportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_support);

            // --- Spark ---
            if (findViewById(R.id.ivSparkMascot) != null) {
                findViewById(R.id.ivSparkMascot).setOnClickListener(v -> 
                    Toast.makeText(this, R.string.spark_msg_support, Toast.LENGTH_SHORT).show());
            }

            // --- Opciones de Soporte ---
            findViewById(R.id.btnAppFAQs).setOnClickListener(v -> {
                startActivity(new Intent(this, FaqActivity.class));
            });

            // Vinculado a la nueva actividad UserManualActivity
            findViewById(R.id.btnAppManual).setOnClickListener(v -> {
                startActivity(new Intent(this, UserManualActivity.class));
            });

            findViewById(R.id.btnAppContact).setOnClickListener(v -> {
                startActivity(new Intent(this, ContactActivity.class));
            });

            // Nueva opción: Recuperar Contraseña
            findViewById(R.id.btnForgotPassword).setOnClickListener(v -> {
                startActivity(new Intent(this, ForgotPasswordActivity.class));
            });

            findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        } catch (Exception e) {
            Log.e("SupportActivity", "Error", e);
            finish();
        }
    }
}