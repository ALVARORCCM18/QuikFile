package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SharedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        // --- Navegación Barra Superior ---
        findViewById(R.id.btnSettingsTop).setOnClickListener(v -> 
            Toast.makeText(this, "Ajustes de Usuario", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnHomeTop).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        findViewById(R.id.btnSharedTop).setOnClickListener(v -> 
            Toast.makeText(this, "Ya estás en Entorno Compartido", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnLoginTop).setOnClickListener(v -> 
            Toast.makeText(this, "Login / Grupos", Toast.LENGTH_SHORT).show());

        // --- Funcionalidades Barra Lateral Izquierda ---
        findViewById(R.id.btnUserLeft).setOnClickListener(v -> 
            Toast.makeText(this, "Perfil de Usuario", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnAddLeft).setOnClickListener(v -> 
            Toast.makeText(this, "Añadir nuevo elemento", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnSettingsLeft).setOnClickListener(v -> 
            Toast.makeText(this, "Configuración del entorno", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnTrashLeft).setOnClickListener(v -> {
            startActivity(new Intent(this, TrashActivity.class));
        });

        // --- Funcionalidades Contenido Central ---
        findViewById(R.id.itemFolder).setOnClickListener(v -> 
            Toast.makeText(this, "Abriendo carpeta compartida...", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnAdd).setOnClickListener(v -> 
            Toast.makeText(this, "Acción: Crear en compartido", Toast.LENGTH_SHORT).show());
    }
}
