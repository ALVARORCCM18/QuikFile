package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

        // --- Navegación Barra Superior ---
        findViewById(R.id.btnSettingsTop).setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
            finish();
        });

        findViewById(R.id.btnHomeTop).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        findViewById(R.id.btnSharedTop).setOnClickListener(v -> {
            startActivity(new Intent(this, SharedActivity.class));
            finish();
        });

        findViewById(R.id.btnLoginTop).setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        // --- Editar Nombre del Entorno ---
        EditText etEnvName = findViewById(R.id.etEnvName);
        findViewById(R.id.btnEditEnvName).setOnClickListener(v -> {
            etEnvName.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(etEnvName, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        // --- Acciones de Miembros ---
        findViewById(R.id.btnEnvUsers).setOnClickListener(v -> 
            Toast.makeText(this, "gestionar usuario", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnEnvAddMember).setOnClickListener(v -> {
            Intent intent = new Intent(this, AddUserActivity.class);
            addUserLauncher.launch(intent);
        });

        // --- Botones de Acción ---
        findViewById(R.id.btnExitEnv).setOnClickListener(v -> 
            Toast.makeText(this, "Saliendo del entorno compartido...", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnDeleteEnv).setOnClickListener(v -> 
            Toast.makeText(this, "Borrando entorno (Requiere confirmación)...", Toast.LENGTH_SHORT).show());

        // --- Botón Atrás ---
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void addNewUserIcon() {
        // Creamos el nuevo contenedor de usuario
        LinearLayout newUserLayout = new LinearLayout(this);
        newUserLayout.setLayoutParams(new LinearLayout.LayoutParams(
                (int) (80 * getResources().getDisplayMetrics().density),
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newUserLayout.setGravity(Gravity.CENTER);
        newUserLayout.setOrientation(LinearLayout.VERTICAL);

        // FrameLayout para el icono
        FrameLayout frame = new FrameLayout(this);
        frame.setLayoutParams(new FrameLayout.LayoutParams(
                (int) (80 * getResources().getDisplayMetrics().density),
                (int) (80 * getResources().getDisplayMetrics().density)));

        View bg = new View(this);
        bg.setBackground(ContextCompat.getDrawable(this, R.drawable.circle_blue));
        bg.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white));
        frame.addView(bg);

        ImageView icon = new ImageView(this);
        icon.setPadding(20, 20, 20, 20);
        icon.setImageResource(R.drawable.ic_person);
        icon.setColorFilter(ContextCompat.getColor(this, R.color.blue_app));
        frame.addView(icon);

        newUserLayout.addView(frame);

        TextView text = new TextView(this);
        text.setText("Usuario");
        text.setTextColor(ContextCompat.getColor(this, R.color.white));
        text.setTextSize(16);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        textParams.topMargin = (int) (8 * getResources().getDisplayMetrics().density);
        text.setLayoutParams(textParams);
        newUserLayout.addView(text);

        // Insertar antes del botón Añadir (que es el último hijo)
        int index = containerActions.getChildCount() - 1;
        containerActions.addView(newUserLayout, index, new LinearLayout.LayoutParams(
                (int) (80 * getResources().getDisplayMetrics().density),
                LinearLayout.LayoutParams.WRAP_CONTENT));
        
        // Añadir margen al nuevo usuario para separarlo del anterior
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) newUserLayout.getLayoutParams();
        layoutParams.setMarginStart((int) (8 * getResources().getDisplayMetrics().density));
        newUserLayout.setLayoutParams(layoutParams);
    }
}
