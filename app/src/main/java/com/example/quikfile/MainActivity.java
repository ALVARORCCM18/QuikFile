package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Navegación a Papelera
        findViewById(R.id.itemTrash).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TrashActivity.class);
            startActivity(intent);
        });

        // Navegación a Entorno Compartido
        findViewById(R.id.btnShared).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SharedActivity.class);
            startActivity(intent);
        });

        // Otros botones (Placeholder)
        findViewById(R.id.btnSettings).setOnClickListener(v -> Toast.makeText(this, "Ajustes", Toast.LENGTH_SHORT).show());
        findViewById(R.id.btnHome).setOnClickListener(v -> Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show());
        findViewById(R.id.btnLogin).setOnClickListener(v -> Toast.makeText(this, "Grupo", Toast.LENGTH_SHORT).show());
        findViewById(R.id.itemFolder).setOnClickListener(v -> Toast.makeText(this, "Carpeta", Toast.LENGTH_SHORT).show());
        findViewById(R.id.btnAdd).setOnClickListener(v -> Toast.makeText(this, "Añadir", Toast.LENGTH_SHORT).show());
    }
}
