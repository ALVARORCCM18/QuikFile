package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddUserActivity extends AppCompatActivity {

    private boolean isOptionsVisible = false;
    private String selectedType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        LinearLayout optionsLayout = findViewById(R.id.optionsLayout);
        TextView tvSelectedType = findViewById(R.id.tvSelectedType);
        ImageView ivArrow = findViewById(R.id.ivArrow);

        // Lógica del selector
        findViewById(R.id.selectorHeader).setOnClickListener(v -> {
            isOptionsVisible = !isOptionsVisible;
            optionsLayout.setVisibility(isOptionsVisible ? View.VISIBLE : View.GONE);
            ivArrow.setImageResource(isOptionsVisible ? android.R.drawable.arrow_up_float : android.R.drawable.arrow_down_float);
        });

        findViewById(R.id.optionAdmin).setOnClickListener(v -> {
            selectedType = "Administrador";
            tvSelectedType.setText(selectedType);
            optionsLayout.setVisibility(View.GONE);
            isOptionsVisible = false;
        });

        findViewById(R.id.optionMember).setOnClickListener(v -> {
            selectedType = "Miembro";
            tvSelectedType.setText(selectedType);
            optionsLayout.setVisibility(View.GONE);
            isOptionsVisible = false;
        });

        // Confirmar añadir
        findViewById(R.id.btnAddUserConfirm).setOnClickListener(v -> {
            if (selectedType.isEmpty()) {
                Toast.makeText(this, "Selecciona un tipo de usuario", Toast.LENGTH_SHORT).show();
                return;
            }
            
            // Mensaje informativo
            Toast.makeText(this, "Usuario añadido correctamente", Toast.LENGTH_SHORT).show();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("user_added", true);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Navegación top bar
        findViewById(R.id.btnHomeTop).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
