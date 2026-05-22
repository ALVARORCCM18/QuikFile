package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UserManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manual);

        // --- Spark Mascot ---
        View spark = findViewById(R.id.ivSparkMascot);
        if (spark != null) {
            spark.setOnClickListener(v -> 
                Toast.makeText(this, R.string.spark_msg_manual, Toast.LENGTH_SHORT).show());
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

        // --- Botón Atrás ---
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
