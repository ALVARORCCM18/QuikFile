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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Esta es la pantalla principal, lo primero que ves al entrar (si ya estas logueado)
public class MainActivity extends AppCompatActivity {

    private GridLayout folderGrid;

    // Con esto manejo lo que vuelve de la pantalla de añadir (si fue carpeta o fichero)
    private final ActivityResultLauncher<Intent> addActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String type = result.getData().getStringExtra("type");
                    if ("FOLDER".equals(type)) {
                        addNewItem("Nueva Carpeta", R.drawable.ic_folder);
                    } else if ("FILE".equals(type)) {
                        addNewItem("Nuevo Fichero", R.drawable.ic_file);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        // Ajusto los margenes para que no se pegue a los bordes de la pantalla (status bar, etc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        folderGrid = findViewById(R.id.folderGrid);

        // --- BOTONES DE LA PANTALLA ---

        // Ir a la papelera
        findViewById(R.id.itemTrash).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TrashActivity.class));
        });

        // Ver lo que tengo compartido con otros
        findViewById(R.id.btnShared).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SharedActivity.class));
        });

        // Ir al login (por si quiero cambiar de usuario)
        findViewById(R.id.btnLogin).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // Abrir la pantalla para crear algo nuevo
        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            addActivityLauncher.launch(intent);
        });

        // Ajustes de la app
        findViewById(R.id.btnSettings).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        // Avisar que ya estas aqui si pulsas home
        findViewById(R.id.btnHome).setOnClickListener(v -> 
            Toast.makeText(this, "Ya estás en Inicio", Toast.LENGTH_SHORT).show());

        // Configuro la carpeta que viene por defecto para que funcione
        View itemFolder = findViewById(R.id.itemFolder);
        if (itemFolder != null) {
            itemFolder.setOnClickListener(v -> openFolder("Carpeta Personal"));
            setupItemLongClick(itemFolder, "Carpeta Personal", R.drawable.ic_folder);
        }
    }

    // Metodo para entrar en una carpeta
    private void openFolder(String name) {
        Intent intent = new Intent(this, FolderContentActivity.class);
        intent.putExtra("folderName", name);
        startActivity(intent);
    }

    // Metodo para ver los detalles de un archivo
    private void openFileDetail(String name) {
        Intent intent = new Intent(this, FileDetailActivity.class);
        intent.putExtra("fileName", name);
        startActivity(intent);
    }

    // Esto es para borrar cosas cuando dejas pulsado el dedo
    private void setupItemLongClick(View view, String name, int iconRes) {
        view.setOnLongClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Eliminar elemento")
                    .setMessage("¿Quieres enviar \"" + name + "\" a la papelera?")
                    .setPositiveButton("Eliminar", (dialog, which) -> {
                        // Lo mando al manager de la papelera y lo quito de la vista
                        TrashManager.getInstance().addItem(name, iconRes);
                        folderGrid.removeView(view);
                        Toast.makeText(this, name + " movido a la papelera", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
            return true;
        });
    }

    // Metodo para crear visualmente una carpeta o archivo nuevo en el grid
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

        // El icono (fichero o carpeta)
        ImageView icon = new ImageView(this);
        LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(
                (int) (90 * getResources().getDisplayMetrics().density),
                (int) (90 * getResources().getDisplayMetrics().density)
        );
        icon.setLayoutParams(iconParams);
        icon.setImageResource(iconRes);
        icon.setColorFilter(ContextCompat.getColor(this, R.color.icon_bg_white));
        newItem.addView(icon);

        // El nombre debajo del icono
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

        // Si es carpeta abro contenido, si es fichero abro detalles
        newItem.setOnClickListener(v -> {
            if (iconRes == R.drawable.ic_folder) {
                openFolder(name);
            } else {
                openFileDetail(name);
            }
        });
        
        // Tambien que se pueda borrar al dejar pulsado
        setupItemLongClick(newItem, name, iconRes);

        // Lo meto en el grid antes del spacer para que quede bien ordenado
        int index = folderGrid.indexOfChild(findViewById(R.id.gridSpacer));
        folderGrid.addView(newItem, index);
        
        Toast.makeText(this, name + " añadida", Toast.LENGTH_SHORT).show();
    }
}
