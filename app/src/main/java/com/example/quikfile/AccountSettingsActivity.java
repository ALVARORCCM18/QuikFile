package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AccountSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        EditText etPassword = findViewById(R.id.etPassword);
        EditText etEmail = findViewById(R.id.etEmail);

        // --- Ayuda de Spark ---
        findViewById(R.id.ivSparkSitting).setOnClickListener(v -> {
            Toast.makeText(this, "Aquí puedes actualizar tu contraseña, cambiar tu correo electrónico o eliminar tu cuenta si lo deseas.", Toast.LENGTH_LONG).show();
        });

        // --- Navegación Barra Superior ---
        findViewById(R.id.btnSettingsTop).setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
            finish();
        });

        findViewById(R.id.btnHomeTop).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        findViewById(R.id.btnSharedTop).setOnClickListener(v -> {
            startActivity(new Intent(this, SharedActivity.class));
            finish();
        });

        findViewById(R.id.btnLoginTop).setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        // --- Opciones de Ajustes ---
        findViewById(R.id.btnUpdatePassword).setOnClickListener(v -> {
            String newPass = etPassword.getText().toString();
            if (!newPass.isEmpty()) {
                Toast.makeText(this, "Contraseña actualizada a: " + newPass, Toast.LENGTH_SHORT).show();
                etPassword.setText(""); // Limpiar campo
            } else {
                Toast.makeText(this, "Por favor, escribe una nueva contraseña", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnUpdateEmail).setOnClickListener(v -> {
            String newEmail = etEmail.getText().toString();
            if (!newEmail.isEmpty()) {
                Toast.makeText(this, "Correo actualizado a: " + newEmail, Toast.LENGTH_SHORT).show();
                etEmail.setText(""); // Limpiar campo
            } else {
                Toast.makeText(this, "Por favor, escribe un nuevo correo", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnDeleteAccount).setOnClickListener(v -> 
            Toast.makeText(this, "Eliminar cuenta (Requiere confirmación)", Toast.LENGTH_SHORT).show());

        // --- Botón Atrás ---
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
