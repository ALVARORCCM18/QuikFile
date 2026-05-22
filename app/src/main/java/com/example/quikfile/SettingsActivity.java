package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // --- Spark Mascot ---
        if (findViewById(R.id.ivSparkMascot) != null) {
            findViewById(R.id.ivSparkMascot).setOnClickListener(v -> 
                Toast.makeText(this, R.string.spark_msg_settings, Toast.LENGTH_SHORT).show());
        }

        // --- Navegación Barra Superior ---
        findViewById(R.id.btnSettingsTop).setOnClickListener(v -> 
            Toast.makeText(this, R.string.already_at_settings, Toast.LENGTH_SHORT).show());

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

        findViewById(R.id.btnLoginTop).setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));

        // --- Campo Nombre ---
        EditText etName = findViewById(R.id.etName);
        findViewById(R.id.btnEditName).setOnClickListener(v -> {
            etName.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) imm.showSoftInput(etName, InputMethodManager.SHOW_IMPLICIT);
        });

        // --- Opciones ---
        findViewById(R.id.btnAccountSettings).setOnClickListener(v -> startActivity(new Intent(this, AccountSettingsActivity.class)));
        findViewById(R.id.btnChangePlan).setOnClickListener(v -> startActivity(new Intent(this, PaymentPlanActivity.class)));
        findViewById(R.id.btnAppReports).setOnClickListener(v -> startActivity(new Intent(this, AppReportsActivity.class)));
        findViewById(R.id.btnAppSupport).setOnClickListener(v -> startActivity(new Intent(this, SupportActivity.class)));
        findViewById(R.id.btnAppIdiom).setOnClickListener(v -> startActivity(new Intent(this, LanguageActivity.class)));

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        findViewById(R.id.btnEditPhoto).setOnClickListener(v -> Toast.makeText(this, R.string.change_profile_photo, Toast.LENGTH_SHORT).show());
    }
}
