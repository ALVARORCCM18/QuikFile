package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SharedActivity extends AppCompatActivity {

    private GridLayout folderGrid;

    // Lanzador para recibir el resultado de la pantalla de añadir
    private final ActivityResultLauncher<Intent> addActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String type = result.getData().getStringExtra("type");
                    if ("FOLDER".equals(type)) {
                        addNewItem("Nueva Carpeta Compartida", R.drawable.ic_folder);
                    } else if ("FILE".equals(type)) {
                        addNewItem("Nuevo Fichero Compartido", android.R.drawable.ic_menu_edit);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        folderGrid = findViewById(R.id.folderGridShared);

        // --- Navegación Barra Superior ---
        findViewById(R.id.btnSettingsTop).setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });

        findViewById(R.id.btnHomeTop).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        findViewById(R.id.btnSharedTop).setOnClickListener(v -> 
            Toast.makeText(this, "Ya estás en Entorno Compartido", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnLoginTop).setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        // --- Funcionalidades Barra Lateral Izquierda ---
        findViewById(R.id.btnUserLeft).setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });

        findViewById(R.id.btnAddLeft).setOnClickListener(v -> {
            Intent intent = new Intent(SharedActivity.this, AddActivity.class);
            addActivityLauncher.launch(intent);
        });

        findViewById(R.id.btnSettingsLeft).setOnClickListener(v -> 
            Toast.makeText(this, "Configuración del entorno", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnTrashLeft).setOnClickListener(v -> {
            startActivity(new Intent(this, TrashActivity.class));
        });

        // --- Funcionalidades Contenido Central ---
        findViewById(R.id.itemFolder).setOnClickListener(v -> 
            Toast.makeText(this, "Abriendo carpeta compartida...", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            Intent intent = new Intent(SharedActivity.this, AddActivity.class);
            addActivityLauncher.launch(intent);
        });
    }

    /**
     * Añade visualmente un nuevo elemento (carpeta o fichero) al GridLayout compartido.
     */
    private void addNewItem(String name, int iconRes) {
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

        ImageView icon = new ImageView(this);
        LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(
                (int) (90 * getResources().getDisplayMetrics().density),
                (int) (90 * getResources().getDisplayMetrics().density)
        );
        icon.setLayoutParams(iconParams);
        icon.setImageResource(iconRes);
        icon.setColorFilter(ContextCompat.getColor(this, R.color.icon_bg_white));
        newItem.addView(icon);

        TextView text = new TextView(this);
        text.setText(name);
        text.setTextColor(ContextCompat.getColor(this, R.color.white));
        text.setTextSize(18);
        text.setTypeface(null, android.graphics.Typeface.BOLD);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        textParams.topMargin = (int) (4 * getResources().getDisplayMetrics().density);
        text.setLayoutParams(textParams);
        newItem.addView(text);

        newItem.setOnClickListener(v -> Toast.makeText(this, "Abriendo " + name, Toast.LENGTH_SHORT).show());

        int index = folderGrid.indexOfChild(findViewById(R.id.gridSpacerShared));
        folderGrid.addView(newItem, index);
        
        Toast.makeText(this, name + " creada", Toast.LENGTH_SHORT).show();
    }
}
