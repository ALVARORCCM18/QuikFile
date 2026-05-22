package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TrashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash);

        // --- Spark Mascot ---
        View spark = findViewById(R.id.ivSparkMascot);
        if (spark != null) {
            spark.setOnClickListener(v -> 
                Toast.makeText(this, R.string.spark_msg_trash, Toast.LENGTH_SHORT).show());
        }

        // --- Navegación ---
        findViewById(R.id.btnSettings).setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        findViewById(R.id.btnHome).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btnShared).setOnClickListener(v -> startActivity(new Intent(this, SharedActivity.class)));
        findViewById(R.id.btnLogin).setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));

        CheckBox cbSelectAll = findViewById(R.id.cbSelectAll);
        CheckBox cbFile1 = findViewById(R.id.cbFile1);

        if (cbSelectAll != null && cbFile1 != null) {
            cbSelectAll.setOnCheckedChangeListener((buttonView, isChecked) -> cbFile1.setChecked(isChecked));
        }

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        findViewById(R.id.btnDelete).setOnClickListener(v -> {
            if (cbFile1 != null && cbFile1.isChecked()) {
                Toast.makeText(this, "Deleted permanently", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Select a file first", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnRecover).setOnClickListener(v -> {
            if (cbFile1 != null && cbFile1.isChecked()) {
                Toast.makeText(this, "File recovered", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Select a file first", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
