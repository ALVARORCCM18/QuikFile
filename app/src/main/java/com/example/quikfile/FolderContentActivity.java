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

public class FolderContentActivity extends AppCompatActivity {

    private GridLayout contentGrid;
    private TextView tvFolderName;

    private final ActivityResultLauncher<Intent> addActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String type = result.getData().getStringExtra("type");
                    if ("FILE".equals(type)) {
                        addNewFile(getString(R.string.new_file));
                    } else if ("FOLDER".equals(type)) {
                        addNewFolder(getString(R.string.new_folder));
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

        int resId = getIntent().getIntExtra("folderResId", 0);
        String folderName = getIntent().getStringExtra("folderName");
        if (resId != 0) tvFolderName.setText(getString(resId));
        else if (folderName != null) tvFolderName.setText(folderName);

        // --- Spark ---
        View spark = findViewById(R.id.ivSparkMascot);
        if (spark != null) {
            spark.setOnClickListener(v -> Toast.makeText(this, R.string.spark_msg_main, Toast.LENGTH_SHORT).show());
        }

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        findViewById(R.id.btnAdd).setOnClickListener(v -> addActivityLauncher.launch(new Intent(this, AddActivity.class)));

        // Configuración de clics para items estáticos
        for (int i = 0; i < contentGrid.getChildCount(); i++) {
            View child = contentGrid.getChildAt(i);
            if (child instanceof LinearLayout) {
                View textChild = ((LinearLayout) child).getChildAt(1);
                if (textChild instanceof TextView) {
                    final String name = ((TextView) textChild).getText().toString();
                    child.setOnClickListener(v -> openFileDetail(name));
                }
            }
        }

        // --- NAVEGACIÓN BARRA SUPERIOR ---
        findViewById(R.id.btnSettingsTop).setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });

        findViewById(R.id.btnHomeTop).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btnSharedTop).setOnClickListener(v -> {
            startActivity(new Intent(this, SharedActivity.class));
        });

        findViewById(R.id.btnLoginTop).setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
    }

    private void openFileDetail(String name) {
        Intent intent = new Intent(this, FileDetailActivity.class);
        intent.putExtra("fileName", name);
        startActivity(intent);
    }

    private void addNewFile(String name) { addItemToGrid(name, R.drawable.ic_file); }
    private void addNewFolder(String name) { addItemToGrid(name, R.drawable.ic_folder); }

    private void addItemToGrid(String name, int iconRes) {
        LinearLayout newItem = new LinearLayout(this);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        newItem.setLayoutParams(params);
        newItem.setOrientation(LinearLayout.VERTICAL);
        newItem.setGravity(Gravity.CENTER);
        newItem.setPadding(16, 16, 16, 16);
        newItem.setClickable(true);
        newItem.setFocusable(true);
        newItem.setBackgroundResource(android.R.drawable.list_selector_background);

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
        newItem.addView(text);

        newItem.setOnClickListener(v -> {
            if (iconRes == R.drawable.ic_folder) {
                Intent intent = new Intent(this, FolderContentActivity.class);
                intent.putExtra("folderName", name);
                startActivity(intent);
            } else {
                openFileDetail(name);
            }
        });

        int index = contentGrid.indexOfChild(findViewById(R.id.gridSpacer));
        contentGrid.addView(newItem, index);
    }
}
