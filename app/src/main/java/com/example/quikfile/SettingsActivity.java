package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // --- Navegación Barra Superior (Funcionalidad Index) ---
        findViewById(R.id.btnSettingsTop).setOnClickListener(v -> 
            Toast.makeText(this, "Ya estás en Ajustes", Toast.LENGTH_SHORT).show());

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

        // --- Botón Atrás ---
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // --- Otras funcionalidades ---
        findViewById(R.id.btnEditPhoto).setOnClickListener(v -> 
            Toast.makeText(this, "Cambiar foto de perfil", Toast.LENGTH_SHORT).show());
    }
}
