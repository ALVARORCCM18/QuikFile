package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SharedSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_settings);

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

        // --- Editar Nombre del Entorno ---
        EditText etEnvName = findViewById(R.id.etEnvName);
        findViewById(R.id.btnEditEnvName).setOnClickListener(v -> {
            etEnvName.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(etEnvName, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        // --- Acciones de Miembros ---
        findViewById(R.id.btnEnvUsers).setOnClickListener(v -> 
            Toast.makeText(this, "gestionar usuario", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnEnvAddMember).setOnClickListener(v -> 
            Toast.makeText(this, "Añadir nuevo miembro...", Toast.LENGTH_SHORT).show());

        // --- Botones de Acción ---
        findViewById(R.id.btnExitEnv).setOnClickListener(v -> 
            Toast.makeText(this, "Saliendo del entorno compartido...", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnDeleteEnv).setOnClickListener(v -> 
            Toast.makeText(this, "Borrando entorno (Requiere confirmación)...", Toast.LENGTH_SHORT).show());

        // --- Botón Atrás ---
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
