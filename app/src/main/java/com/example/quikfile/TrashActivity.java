package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// Esta es la pantalla de la papelera, donde van a parar las cosas que borramos por error
public class TrashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash);

        // --- LOS ICONOS DE ARRIBA (ACCESOS RAPIDOS) ---

        // Abrir ajustes
        findViewById(R.id.btnSettings).setOnClickListener(v -> 
            Toast.makeText(this, "Ajustes de Usuario", Toast.LENGTH_SHORT).show());
        
        // Volver a la pantalla principal
        findViewById(R.id.btnHome).setOnClickListener(v -> {
            Intent intent = new Intent(TrashActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Cerramos esta para que no se quede abierta por debajo
        });

        // Ir a la zona compartida
        findViewById(R.id.btnShared).setOnClickListener(v -> {
            Intent intent = new Intent(TrashActivity.this, SharedActivity.class);
            startActivity(intent);
        });

        // Ir al login
        findViewById(R.id.btnLogin).setOnClickListener(v -> {
            Intent intent = new Intent(TrashActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // --- MANEJO DE LOS ARCHIVOS BORRADOS ---

        // Cojo los checkboxes para saber que quiere recuperar o borrar el usuario
        CheckBox cbSelectAll = findViewById(R.id.cbSelectAll);
        CheckBox cbFile1 = findViewById(R.id.cbFile1);

        // Si marcas "Seleccionar todos", marcamos tambien el archivo individual
        if (cbSelectAll != null && cbFile1 != null) {
            cbSelectAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
                cbFile1.setChecked(isChecked);
            });
        }

        // --- ACCIONES DE LA PARTE DE ABAJO ---

        // Flecha para ir atras
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
        });

        // Borrar definitivamente lo seleccionado
        findViewById(R.id.btnDelete).setOnClickListener(v -> {
            if (cbFile1 != null && cbFile1.isChecked()) {
                Toast.makeText(this, "Ficheros eliminados permanentemente", Toast.LENGTH_LONG).show();
                // Aqui faltaria quitarlo de la lista visualmente
            } else {
                Toast.makeText(this, "Selecciona algún fichero para eliminar", Toast.LENGTH_SHORT).show();
            }
        });

        // Sacar de la papelera lo seleccionado
        findViewById(R.id.btnRecover).setOnClickListener(v -> {
            if (cbFile1 != null && cbFile1.isChecked()) {
                Toast.makeText(this, "Ficheros recuperados con éxito", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Selecciona algún fichero para recuperar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
