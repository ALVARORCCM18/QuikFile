package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // --- NAVEGACIÓN BARRA SUPERIOR ---
        findViewById(R.id.btnSettings).setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });

        findViewById(R.id.btnHome).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        findViewById(R.id.btnShared).setOnClickListener(v -> {
            startActivity(new Intent(this, SharedActivity.class));
        });

        findViewById(R.id.btnLogin).setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        // --- OPCIONES DE AÑADIR ---
        findViewById(R.id.btnNewFolder).setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("type", "FOLDER");
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        findViewById(R.id.btnNewFile).setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("type", "FILE");
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // --- BOTÓN VOLVER ---
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            onBackPressed();
        });
    }
}
