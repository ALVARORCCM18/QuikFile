package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Clase principal que se ejecuta al abrir la aplicación.
 * Hereda de AppCompatActivity, que es la base para actividades modernas de Android.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Habilita el diseño de borde a borde (para que la app use toda la pantalla, incluyendo barras de estado)
        EdgeToEdge.enable(this);
        
        // Asocia esta clase Java con el archivo de diseño XML correspondiente
        setContentView(R.layout.activity_main);
        
        // Ajusta el padding de la vista para evitar que el contenido se superponga con las barras del sistema (estado/navegación)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // --- LÓGICA DE NAVEGACIÓN ---

        // Configura el clic en el elemento "Papelera"
        findViewById(R.id.itemTrash).setOnClickListener(v -> {
            // Un Intent es un objeto que describe una operación a realizar (abrir otra pantalla)
            Intent intent = new Intent(MainActivity.this, TrashActivity.class);
            startActivity(intent); // Inicia la actividad de la Papelera
        });

        // Configura el clic en el botón de "Entorno Compartido" (manos)
        findViewById(R.id.btnShared).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SharedActivity.class);
            startActivity(intent); // Inicia la actividad de Entorno Compartido
        });

        // --- MENSAJES INFORMATIVOS (TOASTS) ---
        // Los Toasts son pequeños mensajes flotantes que aparecen y desaparecen solos

        findViewById(R.id.btnSettings).setOnClickListener(v -> 
            Toast.makeText(this, "Abriendo Ajustes", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnHome).setOnClickListener(v -> 
            Toast.makeText(this, "Ya estás en Inicio", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnLogin).setOnClickListener(v -> 
            Toast.makeText(this, "Acceso a Grupos / Login", Toast.LENGTH_SHORT).show());

        findViewById(R.id.itemFolder).setOnClickListener(v -> 
            Toast.makeText(this, "Abriendo Carpeta Personal", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnAdd).setOnClickListener(v -> 
            Toast.makeText(this, "Opción: Añadir nuevo archivo", Toast.LENGTH_SHORT).show());
    }
}
