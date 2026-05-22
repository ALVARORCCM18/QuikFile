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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private GridLayout folderGrid;

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
        setContentView(R.layout.activity_main);
        
        folderGrid = findViewById(R.id.folderGrid);

        // --- Spark Mascot Traducido ---
        View spark = findViewById(R.id.ivSparkMascot);
        if (spark != null) {
            spark.setOnClickListener(v -> 
                Toast.makeText(this, R.string.spark_msg_main, Toast.LENGTH_SHORT).show());
        }

        // --- NAVEGACIÓN ---
        findViewById(R.id.itemTrash).setOnClickListener(v -> startActivity(new Intent(this, TrashActivity.class)));
        findViewById(R.id.btnShared).setOnClickListener(v -> startActivity(new Intent(this, SharedActivity.class)));
        findViewById(R.id.btnLogin).setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
        findViewById(R.id.btnAdd).setOnClickListener(v -> addActivityLauncher.launch(new Intent(this, AddActivity.class)));
        findViewById(R.id.btnSettings).setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        findViewById(R.id.btnHome).setOnClickListener(v -> Toast.makeText(this, R.string.already_at_home, Toast.LENGTH_SHORT).show());

        View itemFolder = findViewById(R.id.itemFolder);
        if (itemFolder != null) {
            String personalFolder = getString(R.string.folder_personal);
            itemFolder.setOnClickListener(v -> openFolder(personalFolder, R.string.folder_personal));
            setupItemLongClick(itemFolder, personalFolder, R.drawable.ic_folder);
        }
    }

    private void openFolder(String name, int resId) {
        Intent intent = new Intent(this, FolderContentActivity.class);
        intent.putExtra("folderName", name);
        intent.putExtra("folderResId", resId);
        startActivity(intent);
    }

    private void openFileDetail(String name) {
        Intent intent = new Intent(this, FileDetailActivity.class);
        intent.putExtra("fileName", name);
        startActivity(intent);
    }

    private void setupItemLongClick(View view, String name, int iconRes) {
        view.setOnLongClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.delete_item_title)
                    .setMessage(getString(R.string.move_to_trash_confirm, name))
                    .setPositiveButton(R.string.delete, (dialog, which) -> {
                        TrashManager.getInstance().addItem(name, iconRes);
                        folderGrid.removeView(view);
                        Toast.makeText(this, getString(R.string.moved_to_trash, name), Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .show();
            return true;
        });
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
        newItem.setBackgroundResource(android.R.drawable.list_selector_background);

        ImageView icon = new ImageView(this);
        icon.setLayoutParams(new LinearLayout.LayoutParams(
                (int) (90 * getResources().getDisplayMetrics().density),
                (int) (90 * getResources().getDisplayMetrics().density)
        ));
        icon.setImageResource(iconRes);
        icon.setColorFilter(ContextCompat.getColor(this, R.color.white));
        newItem.addView(icon);

        TextView text = new TextView(this);
        text.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        text.setText(name);
        text.setTextColor(ContextCompat.getColor(this, R.color.white));
        text.setTextSize(20);
        text.setGravity(Gravity.CENTER);
        text.setTypeface(null, android.graphics.Typeface.BOLD);
        newItem.addView(text);

        newItem.setOnClickListener(v -> {
            if (iconRes == R.drawable.ic_folder) openFolder(name, 0);
            else openFileDetail(name);
        });
        
        setupItemLongClick(newItem, name, iconRes);
        int index = folderGrid.indexOfChild(findViewById(R.id.gridSpacer));
        folderGrid.addView(newItem, index);
        Toast.makeText(this, getString(R.string.item_added, name), Toast.LENGTH_SHORT).show();
    }
}
