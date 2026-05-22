package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

public class LanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        // --- Spark Traducido ---
        if (findViewById(R.id.ivSparkMascot) != null) {
            findViewById(R.id.ivSparkMascot).setOnClickListener(v -> 
                Toast.makeText(this, R.string.spark_msg_language, Toast.LENGTH_SHORT).show());
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

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // --- Lógica de Idiomas ---
        findViewById(R.id.btnAppIdiomSpain).setOnClickListener(v -> changeLanguage("es"));
        findViewById(R.id.btnAppIdiomEng).setOnClickListener(v -> changeLanguage("en"));
    }

    private void changeLanguage(String lang) {
        LocaleListCompat appLocales = LocaleListCompat.forLanguageTags(lang);
        AppCompatDelegate.setApplicationLocales(appLocales);
        int msg = lang.equals("es") ? R.string.lang_changed_es : R.string.lang_changed_en;
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        recreate();
    }
}
