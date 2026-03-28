package com.example.quikfile;

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

        // Configuración de botones de la barra superior
        findViewById(R.id.btnSettings).setOnClickListener(v ->
                Toast.makeText(this, "Navegando a Ajustes", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnHome).setOnClickListener(v ->
                Toast.makeText(this, "Ya estás en la Página Principal", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnShared).setOnClickListener(v ->
                Toast.makeText(this, "Abriendo Entorno Compartido", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnLogin).setOnClickListener(v ->
                Toast.makeText(this, "Navegando a Inicio de Sesión", Toast.LENGTH_SHORT).show());

        // Configuración de elementos centrales
        findViewById(R.id.itemFolder).setOnClickListener(v ->
                Toast.makeText(this, "Entrando en carpeta...", Toast.LENGTH_SHORT).show());

        findViewById(R.id.itemTrash).setOnClickListener(v ->
                Toast.makeText(this, "Abriendo papelera", Toast.LENGTH_SHORT).show());

        // Botón Añadir
        findViewById(R.id.btnAdd).setOnClickListener(v ->
                Toast.makeText(this, "Opción Añadir", Toast.LENGTH_SHORT).show());
    }
}
