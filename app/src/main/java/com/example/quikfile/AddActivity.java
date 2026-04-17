package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// Esta es la pantalla que sale cuando le das al boton de "+" para añadir cosas
public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // --- LOS BOTONES DE ARRIBA (MENU RAPIDO) ---
        
        // Ir a los ajustes
        findViewById(R.id.btnSettings).setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });

        // Ir al inicio de la app
        findViewById(R.id.btnHome).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            // Esto es para que no se amontonen las pantallas y vuelva a la que ya habia
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        // Ver el entorno compartido
        findViewById(R.id.btnShared).setOnClickListener(v -> {
            startActivity(new Intent(this, SharedActivity.class));
        });

        // Ir al login
        findViewById(R.id.btnLogin).setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        // --- ELECCION DE LO QUE QUEREMOS CREAR ---

        // Si el usuario elige "Nueva Carpeta"
        findViewById(R.id.btnNewFolder).setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("type", "FOLDER"); // Avisamos que es una carpeta
            setResult(RESULT_OK, resultIntent); // Todo ha ido bien
            finish(); // Cerramos esta pantalla y volvemos a la anterior
        });

        // Si el usuario elige "Nuevo Fichero"
        findViewById(R.id.btnNewFile).setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("type", "FILE"); // Avisamos que es un fichero
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // --- EL BOTON DE ATRAS ---
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
        });
    }
}
