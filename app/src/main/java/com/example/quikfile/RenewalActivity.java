package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RenewalActivity extends AppCompatActivity {

    private ImageView rbIndefinite, rbYears, rbMonths;
    private EditText etYears, etMonths;
    private int selectedOption = -1; // 0: Indefinite, 1: Years, 2: Months

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renewal);

        // --- Inicializar Vistas ---
        rbIndefinite = findViewById(R.id.rbIndefinite);
        rbYears = findViewById(R.id.rbYears);
        rbMonths = findViewById(R.id.rbMonths);
        etYears = findViewById(R.id.etYears);
        etMonths = findViewById(R.id.etMonths);

        // --- Lógica de Selección ---
        findViewById(R.id.optionIndefinite).setOnClickListener(v -> selectOption(0));
        findViewById(R.id.optionYears).setOnClickListener(v -> selectOption(1));
        findViewById(R.id.optionMonths).setOnClickListener(v -> selectOption(2));

        // --- Navegación Barra Superior ---
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

        findViewById(R.id.btnLoginTop).setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        // --- Navegación Inferior ---
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        findViewById(R.id.btnForward).setOnClickListener(v -> {
            if (selectedOption == -1) {
                Toast.makeText(this, "Por favor, seleccione una opción", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedOption == 1 && etYears.getText().toString().isEmpty()) {
                etYears.setError("Introduce el número de años");
                return;
            }

            if (selectedOption == 2 && etMonths.getText().toString().isEmpty()) {
                etMonths.setError("Introduce el número de meses");
                return;
            }

            // Vamos a la pantalla de Pago indicando que es una RENOVACIÓN
            Intent intent = new Intent(this, PaymentInfoActivity.class);
            intent.putExtra("flow_type", "renewal");
            startActivity(intent);
        });
    }

    private void selectOption(int option) {
        selectedOption = option;

        // Resetear iconos
        rbIndefinite.setImageResource(R.drawable.ic_radio_unselected);
        rbYears.setImageResource(R.drawable.ic_radio_unselected);
        rbMonths.setImageResource(R.drawable.ic_radio_unselected);

        // Marcar seleccionado
        if (option == 0) rbIndefinite.setImageResource(R.drawable.ic_radio_selected);
        else if (option == 1) rbYears.setImageResource(R.drawable.ic_radio_selected);
        else if (option == 2) rbMonths.setImageResource(R.drawable.ic_radio_selected);
    }
}
