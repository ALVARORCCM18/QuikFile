package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AccountSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        EditText etPassword = findViewById(R.id.etPassword);
        EditText etEmail = findViewById(R.id.etEmail);

        // --- Spark Traducido ---
        if (findViewById(R.id.ivSparkSitting) != null) {
            findViewById(R.id.ivSparkSitting).setOnClickListener(v -> 
                Toast.makeText(this, R.string.spark_msg_account, Toast.LENGTH_LONG).show());
        }

        // --- Navegación ---
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

        findViewById(R.id.btnLoginTop).setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));

        // --- Lógica de Botones ---
        findViewById(R.id.btnUpdatePassword).setOnClickListener(v -> {
            String newPass = etPassword.getText().toString();
            if (!newPass.isEmpty()) {
                Toast.makeText(this, R.string.password_updated, Toast.LENGTH_SHORT).show();
                etPassword.setText("");
            }
        });

        findViewById(R.id.btnUpdateEmail).setOnClickListener(v -> {
            String newEmail = etEmail.getText().toString();
            if (!newEmail.isEmpty()) {
                Toast.makeText(this, R.string.email_updated, Toast.LENGTH_SHORT).show();
                etEmail.setText("");
            }
        });

        findViewById(R.id.btnDeleteAccount).setOnClickListener(v -> 
            Toast.makeText(this, R.string.delete_account_confirm, Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
