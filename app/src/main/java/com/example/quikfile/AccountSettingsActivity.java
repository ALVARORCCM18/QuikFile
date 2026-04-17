package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AccountSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

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
        findViewById(R.id.btnChangePassword).setOnClickListener(v -> 
            Toast.makeText(this, "Redirigiendo a cambiar contraseña...", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnChangeEmail).setOnClickListener(v -> 
            Toast.makeText(this, "Redirigiendo a cambiar correo...", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnDeleteAccount).setOnClickListener(v -> 
            Toast.makeText(this, "Eliminar cuenta (Requiere confirmación)", Toast.LENGTH_SHORT).show());

        // --- Botón Atrás ---
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
