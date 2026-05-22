package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        EditText etEmail = findViewById(R.id.etRecoverEmail);

        // --- Spark Mascot ---
        View spark = findViewById(R.id.ivSparkMascot);
        if (spark != null) {
            spark.setOnClickListener(v -> 
                Toast.makeText(this, R.string.spark_msg_forgot_password, Toast.LENGTH_SHORT).show());
        }

        // --- Navegación Barra Superior ---
        findViewById(R.id.btnSettingsTop).setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
            finish();
        });

        findViewById(R.id.btnHomeTop).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btnSharedTop).setOnClickListener(v -> {
            startActivity(new Intent(this, SharedActivity.class));
            finish();
        });

        findViewById(R.id.btnLoginTop).setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        // --- Botón Recuperar ---
        findViewById(R.id.btnSubmitRecover).setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.forgot_password_toast, Toast.LENGTH_LONG).show();
                finish(); // Regresa a la pantalla anterior
            }
        });

        // --- Botón Atrás ---
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
