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

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

// Esta pantalla es la que se abre cuando entras en una carpeta para ver sus ficheros y subcarpetas
public class FolderContentActivity extends AppCompatActivity {

    private GridLayout contentGrid;
    private TextView tvFolderName;

    // Escuchador para cuando volvemos de la pantalla de añadir un nuevo elemento
    private final ActivityResultLauncher<Intent> addActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String type = result.getData().getStringExtra("type");
                    if ("FILE".equals(type)) {
                        addNewFile("Nuevo Fichero");
                    } else if ("FOLDER".equals(type)) {
                        addNewFolder("Nueva Subcarpeta");
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_content);

        contentGrid = findViewById(R.id.contentGrid);
        tvFolderName = findViewById(R.id.tvFolderName);

        // Pongo el nombre de la carpeta que me han pasado al abrir esta pantalla
        String folderName = getIntent().getStringExtra("folderName");
        if (folderName != null) {
            tvFolderName.setText(folderName);
        }

        // --- BOTONES DE NAVEGACIÓN ---

        // Boton para volver atras
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Boton para añadir un nuevo fichero o carpeta a esta ubicacion
        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            Intent intent = new Intent(this, AddActivity.class);
            addActivityLauncher.launch(intent);
        });

        // Configuro los items que ya estan puestos en el diseño XML para que se puedan pulsar
        for (int i = 0; i < contentGrid.getChildCount(); i++) {
            View child = contentGrid.getChildAt(i);
            if (child instanceof LinearLayout) {
                LinearLayout item = (LinearLayout) child;
                // Cojo el texto del item para saber como se llama el fichero
                TextView text = (TextView) item.getChildAt(1);
                String name = text.getText().toString();
                // Por defecto los que estan en el XML los tratamos como ficheros
                item.setOnClickListener(v -> openFileDetail(name));
            }
        }

        // Botones de la barra de arriba (Home y Compartido)
        findViewById(R.id.btnHomeTop).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Limpio el historial para volver al inicio
            startActivity(intent);
        });

        findViewById(R.id.btnSharedTop).setOnClickListener(v -> {
            Intent intent = new Intent(this, SharedActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    // Metodo para ir a la pantalla de detalles de un fichero
    private void openFileDetail(String name) {
        Intent intent = new Intent(this, FileDetailActivity.class);
        intent.putExtra("fileName", name);
        startActivity(intent);
    }

    // Funciones rapidas para añadir ficheros o carpetas
    private void addNewFile(String name) {
        addItemToGrid(name, R.drawable.ic_file);
    }

    private void addNewFolder(String name) {
        addItemToGrid(name, R.drawable.ic_folder);
    }

    // Metodo que crea el diseño visual del nuevo elemento y lo mete en el grid
    private void addItemToGrid(String name, int iconRes) {
        LinearLayout newItem = new LinearLayout(this);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        newItem.setLayoutParams(params);
        newItem.setOrientation(LinearLayout.VERTICAL);
        newItem.setGravity(Gravity.CENTER);
        newItem.setPadding(32, 32, 32, 32);

        // El dibujito del archivo o la carpeta
        ImageView icon = new ImageView(this);
        LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(
                (int) (80 * getResources().getDisplayMetrics().density),
                (int) (80 * getResources().getDisplayMetrics().density)
        );
        icon.setLayoutParams(iconParams);
        icon.setImageResource(iconRes);
        icon.setColorFilter(ContextCompat.getColor(this, R.color.white));
        newItem.addView(icon);

        // El texto con el nombre debajo
        TextView text = new TextView(this);
        text.setText(name);
        text.setTextColor(ContextCompat.getColor(this, R.color.white));
        text.setTextSize(18);
        text.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        textParams.topMargin = 16;
        text.setLayoutParams(textParams);
        newItem.addView(text);

        // Configuro que pasa al hacer click segun sea carpeta o fichero
        newItem.setOnClickListener(v -> {
            if (iconRes == R.drawable.ic_folder) {
                // Si es carpeta, abro otra vez esta actividad pero con el nuevo nombre
                Intent intent = new Intent(this, FolderContentActivity.class);
                intent.putExtra("folderName", name);
                startActivity(intent);
            } else {
                // Si es fichero, voy a ver sus detalles
                openFileDetail(name);
            }
        });

        // Lo añado al grid antes del espacio vacio para que quede bien colocado
        int index = contentGrid.indexOfChild(findViewById(R.id.gridSpacer));
        contentGrid.addView(newItem, index);
    }
}
