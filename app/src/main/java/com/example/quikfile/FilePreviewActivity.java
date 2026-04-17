package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FilePreviewActivity extends AppCompatActivity {

    private TextView tvFileName, tvFileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_preview);

        tvFileName = findViewById(R.id.tvFileName);
        tvFileContent = findViewById(R.id.tvFileContent);

        String fileName = getIntent().getStringExtra("fileName");
        String versionDate = getIntent().getStringExtra("versionDate");

        if (fileName != null) {
            tvFileName.setText(fileName);
        }

        if (versionDate != null) {
            tvFileContent.setText("Esta es la versión del archivo correspondiente a: " + versionDate + 
                "\n\nContenido restaurado de la fecha indicada.\n\n" + tvFileContent.getText());
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

        // --- Botones Inferiores ---
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        findViewById(R.id.btnHistory).setOnClickListener(v -> {
            Intent intent = new Intent(this, FileVersionHistoryActivity.class);
            intent.putExtra("fileName", tvFileName.getText().toString());
            startActivity(intent);
        });
    }
}
