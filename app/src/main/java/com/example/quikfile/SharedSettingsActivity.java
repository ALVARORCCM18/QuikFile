package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SharedSettingsActivity extends AppCompatActivity {

    private LinearLayout containerActions;
    private final ActivityResultLauncher<Intent> addUserLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    addNewUserIcon();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_settings);

        containerActions = findViewById(R.id.containerActions);

        // --- Spark Mascot ---
        View spark = findViewById(R.id.ivSparkMascot);
        if (spark != null) {
            spark.setOnClickListener(v -> 
                Toast.makeText(this, R.string.spark_msg_shared_settings, Toast.LENGTH_SHORT).show());
        }

        // --- Navegación Barra Superior ---
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

        // --- Opciones ---
        findViewById(R.id.btnEnvAddMember).setOnClickListener(v -> {
            Intent intent = new Intent(this, AddUserActivity.class);
            addUserLauncher.launch(intent);
        });

        // Botón Salir del Entorno
        findViewById(R.id.btnExitEnv).setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.exit_env)
                    .setMessage(R.string.exit_env_confirm)
                    .setPositiveButton(R.string.accept, (dialog, which) -> {
                        finish();
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .show();
        });

        // Botón Borrar Entorno
        findViewById(R.id.btnDeleteEnv).setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.delete_env)
                    .setMessage(R.string.delete_env_confirm)
                    .setPositiveButton(R.string.delete, (dialog, which) -> {
                        finish();
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .show();
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void addNewUserIcon() {
        LinearLayout newUserLayout = new LinearLayout(this);
        newUserLayout.setLayoutParams(new LinearLayout.LayoutParams((int) (80 * getResources().getDisplayMetrics().density), LinearLayout.LayoutParams.WRAP_CONTENT));
        newUserLayout.setGravity(Gravity.CENTER);
        newUserLayout.setOrientation(LinearLayout.VERTICAL);

        FrameLayout frame = new FrameLayout(this);
        frame.setLayoutParams(new FrameLayout.LayoutParams((int) (80 * getResources().getDisplayMetrics().density), (int) (80 * getResources().getDisplayMetrics().density)));

        View bg = new View(this);
        bg.setBackground(ContextCompat.getDrawable(this, R.drawable.circle_white));
        frame.addView(bg);

        ImageView icon = new ImageView(this);
        icon.setPadding(20, 20, 20, 20);
        icon.setImageResource(R.drawable.ic_person);
        icon.setColorFilter(ContextCompat.getColor(this, R.color.blue_app));
        frame.addView(icon);

        newUserLayout.addView(frame);

        TextView text = new TextView(this);
        text.setText(R.string.user_label);
        text.setTextColor(ContextCompat.getColor(this, R.color.white));
        text.setTextSize(16);
        text.setGravity(Gravity.CENTER);
        newUserLayout.addView(text);

        int index = containerActions.getChildCount() - 1;
        containerActions.addView(newUserLayout, index);
    }
}
