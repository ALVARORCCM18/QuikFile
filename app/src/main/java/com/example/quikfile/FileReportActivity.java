package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FileReportActivity extends AppCompatActivity {

    private TextView tvFileName;
    private EditText etReportMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_report);

        tvFileName = findViewById(R.id.tvFileName);
        etReportMessage = findViewById(R.id.etReportMessage);

        String fileName = getIntent().getStringExtra("fileName");
        if (fileName != null) {
            tvFileName.setText("\"" + fileName + "\"");
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

        // --- Botón REPORTAR ---
        findViewById(R.id.btnSubmitReport).setOnClickListener(v -> {
            String message = etReportMessage.getText().toString().trim();
            if (message.isEmpty()) {
                Toast.makeText(this, "Por favor, introduce el motivo del reporte", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Reporte enviado correctamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // --- Botón Atrás ---
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
