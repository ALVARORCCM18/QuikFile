package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // --- Spark Mascot ---
        View spark = findViewById(R.id.ivSparkSpying);
        if (spark != null) {
            spark.setOnClickListener(v -> 
                Toast.makeText(this, R.string.spark_msg_add, Toast.LENGTH_SHORT).show());
        }

        findViewById(R.id.btnNewFolder).setOnClickListener(v -> {
            Intent result = new Intent();
            result.putExtra("type", "FOLDER");
            setResult(RESULT_OK, result);
            finish();
        });

        findViewById(R.id.btnNewFile).setOnClickListener(v -> {
            Intent result = new Intent();
            result.putExtra("type", "FILE");
            setResult(RESULT_OK, result);
            finish();
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Navegación superior
        findViewById(R.id.btnHome).setOnClickListener(v -> finish());
        findViewById(R.id.btnShared).setOnClickListener(v -> {
            startActivity(new Intent(this, SharedActivity.class));
            finish();
        });
        findViewById(R.id.btnLogin).setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
        findViewById(R.id.btnSettings).setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
            finish();
        });
    }
}
