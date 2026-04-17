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

// Esta es la zona para los archivos que compartimos con otros usuarios
public class SharedActivity extends AppCompatActivity {

    private GridLayout folderGrid;
    private LinearLayout userIconsContainer;

    // Lanzador para cuando añadimos algo nuevo en esta zona
    private final ActivityResultLauncher<Intent> addActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String type = result.getData().getStringExtra("type");
                    if ("FOLDER".equals(type)) {
                        addNewItem("Nueva Carpeta Compartida", R.drawable.ic_folder);
                    } else if ("FILE".equals(type)) {
                        addNewItem("Nuevo Fichero Compartido", R.drawable.ic_file);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        folderGrid = findViewById(R.id.folderGridShared);
        userIconsContainer = findViewById(R.id.userIconsContainer);

        // --- BOTONES DE LA BARRA DE ARRIBA ---
        
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

        // --- ICONOS DE LA BARRA LATERAL (LA DE LA IZQUIERDA) ---
        
        findViewById(R.id.btnUserLeft).setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });

        // Este es para añadir un nuevo icono de persona a la lista de la izquierda
        findViewById(R.id.btnAddLeft).setOnClickListener(v -> {
            addNewSharedEnvironmentIcon();
        });

        findViewById(R.id.btnSettingsLeft).setOnClickListener(v -> {
            startActivity(new Intent(this, SharedSettingsActivity.class));
        });

        findViewById(R.id.btnTrashLeft).setOnClickListener(v -> {
            startActivity(new Intent(this, TrashActivity.class));
        });

        // --- PARTE CENTRAL (ARCHIVOS Y CARPETAS) ---
        
        findViewById(R.id.itemFolder).setOnClickListener(v -> openFolder("Carpeta"));

        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            Intent intent = new Intent(SharedActivity.this, AddActivity.class);
            addActivityLauncher.launch(intent);
        });
    }

    // Entrar en una carpeta compartida
    private void openFolder(String name) {
        Intent intent = new Intent(this, FolderContentActivity.class);
        intent.putExtra("folderName", name);
        startActivity(intent);
    }

    // Ver detalles de un archivo compartido
    private void openFileDetail(String name) {
        Intent intent = new Intent(this, FileDetailActivity.class);
        intent.putExtra("fileName", name);
        startActivity(intent);
    }

    // Metodo para meter otra "persona" en el menu lateral
    private void addNewSharedEnvironmentIcon() {
        ImageView newEnvironment = new ImageView(this);
        int size = (int) (55 * getResources().getDisplayMetrics().density);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
        params.topMargin = (int) (8 * getResources().getDisplayMetrics().density);
        newEnvironment.setPadding(4, 4, 4, 4);
        newEnvironment.setLayoutParams(params);
        newEnvironment.setImageResource(R.drawable.ic_person);
        newEnvironment.setColorFilter(ContextCompat.getColor(this, R.color.cyan_recover));
        
        newEnvironment.setOnClickListener(v -> 
            Toast.makeText(this, "Cambiando a otro entorno compartido...", Toast.LENGTH_SHORT).show());
            
        userIconsContainer.addView(newEnvironment);
        Toast.makeText(this, "Nuevo entorno compartido añadido", Toast.LENGTH_SHORT).show();
    }

    // Para pintar una carpeta o archivo nuevo en el grid central
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

        // Al pulsar, abro la carpeta o el detalle segun lo que sea
        newItem.setOnClickListener(v -> {
            if (iconRes == R.drawable.ic_folder) {
                openFolder(name);
            } else {
                openFileDetail(name);
            }
        });

        // Lo pongo antes del espacio vacio para que no se desordene
        int index = folderGrid.indexOfChild(findViewById(R.id.gridSpacerShared));
        folderGrid.addView(newItem, index);
        
        Toast.makeText(this, name + " creada", Toast.LENGTH_SHORT).show();
    }
}
