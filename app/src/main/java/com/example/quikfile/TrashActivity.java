package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Pantalla de la Papelera.
 * Aquí el usuario puede ver archivos eliminados, seleccionarlos y decidir si borrarlos para siempre o recuperarlos.
 */
public class TrashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash);

        // --- NAVEGACIÓN BARRA SUPERIOR ---

        findViewById(R.id.btnSettings).setOnClickListener(v -> 
            Toast.makeText(this, "Ajustes de Usuario", Toast.LENGTH_SHORT).show());
        
        // El icono de la casa nos devuelve a la pantalla de inicio
        findViewById(R.id.btnHome).setOnClickListener(v -> {
            Intent intent = new Intent(TrashActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Cierra esta pantalla para que no quede en el historial de "atrás"
        });

        // El icono de las manos nos lleva al entorno compartido
        findViewById(R.id.btnShared).setOnClickListener(v -> {
            Intent intent = new Intent(TrashActivity.this, SharedActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnLogin).setOnClickListener(v -> 
            Toast.makeText(this, "Inicio de Sesión / Grupos", Toast.LENGTH_SHORT).show());

        // --- LÓGICA DE SELECCIÓN DE ARCHIVOS ---

        // Referencias a los cuadraditos de selección (Checkboxes)
        CheckBox cbSelectAll = findViewById(R.id.cbSelectAll);
        CheckBox cbFile1 = findViewById(R.id.cbFile1);

        // Cuando el usuario marca o desmarca "Seleccionar todo"
        cbSelectAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Ponemos el archivo 1 en el mismo estado (marcado o no) que el de "todos"
            cbFile1.setChecked(isChecked);
        });

        // --- BOTONES DE LA PARTE INFERIOR ---

        // Botón de la flecha: simplemente simula el botón "atrás" del teléfono
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            onBackPressed();
        });

        // Botón Eliminar: Verifica si hay algo seleccionado antes de actuar
        findViewById(R.id.btnDelete).setOnClickListener(v -> {
            if (cbFile1.isChecked()) {
                Toast.makeText(this, "Ficheros eliminados permanentemente", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Selecciona algún fichero para eliminar", Toast.LENGTH_SHORT).show();
            }
        });

        // Botón Recuperar: Verifica si hay algo seleccionado antes de actuar
        findViewById(R.id.btnRecover).setOnClickListener(v -> {
            if (cbFile1.isChecked()) {
                Toast.makeText(this, "Ficheros recuperados con éxito", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Selecciona algún fichero para recuperar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
