package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TrashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash);

        // Referencias de los iconos de la barra superior
        findViewById(R.id.btnSettings).setOnClickListener(v -> 
            Toast.makeText(this, "Ajustes de Usuario", Toast.LENGTH_SHORT).show());
        
        findViewById(R.id.btnHome).setOnClickListener(v -> {
            Intent intent = new Intent(TrashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btnShared).setOnClickListener(v -> {
            Intent intent = new Intent(TrashActivity.this, SharedActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnLogin).setOnClickListener(v -> 
            Toast.makeText(this, "Inicio de Sesión / Grupos", Toast.LENGTH_SHORT).show());

        // Lógica de Selección
        CheckBox cbSelectAll = findViewById(R.id.cbSelectAll);
        CheckBox cbFile1 = findViewById(R.id.cbFile1);

        cbSelectAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            cbFile1.setChecked(isChecked);
        });

        // Botón Volver (Flecha)
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            onBackPressed();
        });

        // Funcionalidad Eliminar y Recuperar
        findViewById(R.id.btnDelete).setOnClickListener(v -> {
            if (cbFile1.isChecked()) {
                Toast.makeText(this, "Ficheros eliminados permanentemente", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Selecciona algún fichero para eliminar", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnRecover).setOnClickListener(v -> {
            if (cbFile1.isChecked()) {
                Toast.makeText(this, "Ficheros recuperados con éxito", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Selecciona algún fichero para recuperar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
