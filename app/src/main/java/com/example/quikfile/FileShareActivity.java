package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FileShareActivity extends AppCompatActivity {

    private TextView tvFileName, tvCurrentPermission;
    private EditText etEmail;
    private LinearLayout optionsContainer;
    private ImageView ivDropdownArrow;
    private boolean isOptionsOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_share);

        tvFileName = findViewById(R.id.tvFileName);
        etEmail = findViewById(R.id.etEmail);
        tvCurrentPermission = findViewById(R.id.tvCurrentPermission);
        optionsContainer = findViewById(R.id.optionsContainer);
        ivDropdownArrow = findViewById(R.id.ivDropdownArrow);

        String fileName = getIntent().getStringExtra("fileName");
        if (fileName != null) {
            tvFileName.setText("\"" + fileName + "\"");
        }

        // --- Lógica del Selector de Permisos ---
        findViewById(R.id.btnPermissionsToggle).setOnClickListener(v -> toggleOptions());

        findViewById(R.id.optionEdit).setOnClickListener(v -> selectPermission(getString(R.string.permission_edit)));
        findViewById(R.id.optionRead).setOnClickListener(v -> selectPermission(getString(R.string.permission_read)));

        // --- Navegación Barra Superior ---
        findViewById(R.id.btnSettingsTop).setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });

        findViewById(R.id.btnHomeTop).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btnSharedTop).setOnClickListener(v -> {
            startActivity(new Intent(this, SharedActivity.class));
        });

        findViewById(R.id.btnLoginTop).setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        // --- Botón COMPARTIR ---
        findViewById(R.id.btnSubmitShare).setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String permission = tvCurrentPermission.getText().toString();

            if (email.isEmpty() || permission.equals(getString(R.string.select_permission))) {
                Toast.makeText(this, R.string.fill_share_fields, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.share_success, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // --- Botón Atrás ---
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void toggleOptions() {
        if (!isOptionsOpen) {
            optionsContainer.setVisibility(View.VISIBLE);
            ivDropdownArrow.setImageResource(android.R.drawable.arrow_up_float);
        } else {
            optionsContainer.setVisibility(View.GONE);
            ivDropdownArrow.setImageResource(android.R.drawable.arrow_down_float);
        }
        isOptionsOpen = !isOptionsOpen;
    }

    private void selectPermission(String permission) {
        tvCurrentPermission.setText(permission);
        toggleOptions();
    }
}
