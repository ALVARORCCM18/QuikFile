package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentInfoActivity extends AppCompatActivity {

    private EditText etCardNumber, etCardHolder, etCVV, etExpiryDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_info);

        // --- Inicializar Vistas ---
        etCardNumber = findViewById(R.id.etCardNumber);
        etCardHolder = findViewById(R.id.etCardHolder);
        etCVV = findViewById(R.id.etCVV);
        etExpiryDate = findViewById(R.id.etExpiryDate);

        // Detectar si venimos de Subscripción o Renovación
        String flowType = getIntent().getStringExtra("flow_type");

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

        // --- Botones Inferiores ---
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
        });

        findViewById(R.id.btnForward).setOnClickListener(v -> {
            if (validateFields()) {
                // Al completar el pago, vamos a la pantalla de ÉXITO
                Intent intent = new Intent(this, SubscriptionSuccessActivity.class);
                intent.putExtra("flow_type", flowType); // Pasamos el tipo de flujo al éxito
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean validateFields() {
        if (etCardNumber.getText().toString().length() < 16) {
            Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etCardHolder.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etCVV.getText().toString().length() < 3) {
            Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etExpiryDate.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
