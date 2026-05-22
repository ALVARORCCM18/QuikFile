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

public class SharedActivity extends AppCompatActivity {

    private GridLayout folderGrid;
    private LinearLayout userIconsContainer;

    private final ActivityResultLauncher<Intent> addActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String type = result.getData().getStringExtra("type");
                    if ("FOLDER".equals(type)) {
                        addNewItem(getString(R.string.new_folder), R.drawable.ic_folder);
                    } else if ("FILE".equals(type)) {
                        addNewItem(getString(R.string.new_file), R.drawable.ic_file);
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

        // --- Spark Mascot ---
        View spark = findViewById(R.id.ivSparkMascot);
        if (spark != null) {
            spark.setOnClickListener(v -> 
                Toast.makeText(this, R.string.spark_msg_shared, Toast.LENGTH_SHORT).show());
        }

        // --- Navegación Superior ---
        findViewById(R.id.btnSettingsTop).setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        findViewById(R.id.btnHomeTop).setOnClickListener(v -> finish());
        findViewById(R.id.btnSharedTop).setOnClickListener(v -> Toast.makeText(this, R.string.shared_env_title, Toast.LENGTH_SHORT).show());
        findViewById(R.id.btnLoginTop).setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));

        // --- Barra Lateral ---
        findViewById(R.id.btnUserLeft).setOnClickListener(v -> Toast.makeText(this, R.string.shared_env_title, Toast.LENGTH_SHORT).show());
        findViewById(R.id.btnAddLeft).setOnClickListener(v -> addNewSharedEnvironmentIcon());
        findViewById(R.id.btnSettingsLeft).setOnClickListener(v -> startActivity(new Intent(this, SharedSettingsActivity.class)));
        findViewById(R.id.btnTrashLeft).setOnClickListener(v -> startActivity(new Intent(this, TrashActivity.class)));

        // --- Contenido ---
        findViewById(R.id.itemFolder).setOnClickListener(v -> openFolder(getString(R.string.folder)));
        findViewById(R.id.btnAdd).setOnClickListener(v -> addActivityLauncher.launch(new Intent(this, AddActivity.class)));
    }

    private void openFolder(String name) {
        Intent intent = new Intent(this, FolderContentActivity.class);
        intent.putExtra("folderName", name);
        startActivity(intent);
    }

    private void openFileDetail(String name) {
        Intent intent = new Intent(this, FileDetailActivity.class);
        intent.putExtra("fileName", name);
        startActivity(intent);
    }

    private void addNewSharedEnvironmentIcon() {
        ImageView newEnv = new ImageView(this);
        int size = (int) (55 * getResources().getDisplayMetrics().density);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
        params.topMargin = (int) (8 * getResources().getDisplayMetrics().density);
        newEnv.setPadding(4, 4, 4, 4);
        newEnv.setLayoutParams(params);
        newEnv.setImageResource(R.drawable.ic_shared_spaces);
        newEnv.setColorFilter(ContextCompat.getColor(this, R.color.blue_app));
        newEnv.setOnClickListener(v -> Toast.makeText(this, R.string.shared_env_title, Toast.LENGTH_SHORT).show());
        userIconsContainer.addView(newEnv);
    }

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
        newItem.setClickable(true);
        newItem.setFocusable(true);

        ImageView icon = new ImageView(this);
        icon.setLayoutParams(new LinearLayout.LayoutParams((int)(90*getResources().getDisplayMetrics().density), (int)(90*getResources().getDisplayMetrics().density)));
        icon.setImageResource(iconRes);
        icon.setColorFilter(ContextCompat.getColor(this, R.color.white));
        newItem.addView(icon);

        TextView text = new TextView(this);
        text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        text.setText(name);
        text.setTextColor(ContextCompat.getColor(this, R.color.white));
        text.setTextSize(18);
        text.setGravity(Gravity.CENTER);
        text.setTypeface(null, android.graphics.Typeface.BOLD);
        newItem.addView(text);

        newItem.setOnClickListener(v -> {
            if (iconRes == R.drawable.ic_folder) openFolder(name);
            else openFileDetail(name);
        });

        int index = folderGrid.indexOfChild(findViewById(R.id.gridSpacerShared));
        folderGrid.addView(newItem, index);
    }
}
