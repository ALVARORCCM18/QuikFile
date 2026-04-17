package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FileVersionHistoryActivity extends AppCompatActivity {

    private TextView tvFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_version_history);

        tvFileName = findViewById(R.id.tvFileName);

        String fileName = getIntent().getStringExtra("fileName");
        if (fileName != null) {
            tvFileName.setText(fileName);
        }

        // --- Navegación Barra Superior ---
        findViewById(R.id.btnSettingsTop).setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });

        findViewById(R.id.btnHomeTop).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        findViewById(R.id.btnSharedTop).setOnClickListener(v -> {
            Intent intent = new Intent(this, SharedActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        findViewById(R.id.btnLoginTop).setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        // --- Navegación Versiones ---
        findViewById(R.id.itemVersionCurrent).setOnClickListener(v -> openVersion("Ayer, 16:05"));
        findViewById(R.id.itemVersion2).setOnClickListener(v -> openVersion("Ante ayer, 18:30"));
        findViewById(R.id.itemVersion3).setOnClickListener(v -> openVersion("19/2/2026, 13:25"));

        // --- Botones Inferiores ---
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void openVersion(String date) {
        Intent intent = new Intent(this, FilePreviewActivity.class);
        intent.putExtra("fileName", tvFileName.getText().toString());
        intent.putExtra("versionDate", date);
        startActivity(intent);
        Toast.makeText(this, "Abriendo versión de " + date, Toast.LENGTH_SHORT).show();
    }
}
