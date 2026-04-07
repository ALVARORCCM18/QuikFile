package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Clase principal que se ejecuta al abrir la aplicación.
 */
public class MainActivity extends AppCompatActivity {

    private GridLayout folderGrid;
    private int itemCount = 0;

    // Lanzador para recibir el resultado de la pantalla de añadir
    private final ActivityResultLauncher<Intent> addActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String type = result.getData().getStringExtra("type");
                    if ("FOLDER".equals(type)) {
                        addNewItem("Nueva Carpeta", R.drawable.ic_folder);
                    } else if ("FILE".equals(type)) {
                        addNewItem("Nuevo Fichero", android.R.drawable.ic_menu_edit);
                    }
                }
            }
    );

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

        folderGrid = findViewById(R.id.folderGrid);

        // --- LÓGICA DE NAVEGACIÓN ---

        findViewById(R.id.itemTrash).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TrashActivity.class));
        });

        findViewById(R.id.btnShared).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SharedActivity.class));
        });

        // Configura el clic en el botón de "Login" (grupos)
        findViewById(R.id.btnLogin).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent); // Inicia la actividad de Login
        });

        // --- MENSAJES INFORMATIVOS (TOASTS) ---
        // Los Toasts son pequeños mensajes flotantes que aparecen y desaparecen solos

        // Configura el clic en el botón de "Añadir" usando el launcher
        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            addActivityLauncher.launch(intent);
        });

        // --- OTROS BOTONES ---
        findViewById(R.id.btnSettings).setOnClickListener(v -> 
            Toast.makeText(this, "Abriendo Ajustes", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnHome).setOnClickListener(v -> 
            Toast.makeText(this, "Ya estás en Inicio", Toast.LENGTH_SHORT).show());

        findViewById(R.id.itemFolder).setOnClickListener(v -> 
            Toast.makeText(this, "Abriendo Carpeta Personal", Toast.LENGTH_SHORT).show());
    }

    /**
     * Añade visualmente un nuevo elemento (carpeta o fichero) al GridLayout.
     */
    private void addNewItem(String name, int iconRes) {
        // Creamos el contenedor (LinearLayout)
        LinearLayout newItem = new LinearLayout(this);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        newItem.setLayoutParams(params);
        newItem.setOrientation(LinearLayout.VERTICAL);
        newItem.setGravity(Gravity.CENTER);
        int padding = (int) (16 * getResources().getDisplayMetrics().density);
        newItem.setPadding(padding, padding, padding, padding);

        // Creamos la imagen
        ImageView icon = new ImageView(this);
        LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(
                (int) (90 * getResources().getDisplayMetrics().density),
                (int) (90 * getResources().getDisplayMetrics().density)
        );
        icon.setLayoutParams(iconParams);
        icon.setImageResource(iconRes);
        icon.setColorFilter(ContextCompat.getColor(this, R.color.icon_bg_white));
        newItem.addView(icon);

        // Creamos el texto
        TextView text = new TextView(this);
        text.setText(name);
        text.setTextColor(ContextCompat.getColor(this, R.color.white));
        text.setTextSize(20);
        text.setTypeface(null, android.graphics.Typeface.BOLD);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        textParams.topMargin = (int) (8 * getResources().getDisplayMetrics().density);
        text.setLayoutParams(textParams);
        newItem.addView(text);

        // Añadimos el clic al nuevo item
        newItem.setOnClickListener(v -> Toast.makeText(this, "Abriendo " + name, Toast.LENGTH_SHORT).show());

        // Lo insertamos en el grid (antes del espaciador final)
        int index = folderGrid.indexOfChild(findViewById(R.id.gridSpacer));
        folderGrid.addView(newItem, index);
        
        Toast.makeText(this, name + " añadida", Toast.LENGTH_SHORT).show();
    }
}
