package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// Esta pantalla sirve para meter a nuevos usuarios en un grupo o entorno
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

        // --- MANEJO DEL SELECTOR DE ROL ---
        
        // Cuando pulsas el selector, abrimos o cerramos las opciones
        findViewById(R.id.selectorHeader).setOnClickListener(v -> {
            isOptionsVisible = !isOptionsVisible;
            optionsLayout.setVisibility(isOptionsVisible ? View.VISIBLE : View.GONE);
            // Cambio la flecha de arriba/abajo segun este abierto o no
            ivArrow.setImageResource(isOptionsVisible ? android.R.drawable.arrow_up_float : android.R.drawable.arrow_down_float);
        });

        // Si eligen Administrador
        findViewById(R.id.optionAdmin).setOnClickListener(v -> {
            selectedType = "Administrador";
            tvSelectedType.setText(selectedType);
            optionsLayout.setVisibility(View.GONE);
            isOptionsVisible = false;
        });

        // Si eligen Miembro
        findViewById(R.id.optionMember).setOnClickListener(v -> {
            selectedType = "Miembro";
            tvSelectedType.setText(selectedType);
            optionsLayout.setVisibility(View.GONE);
            isOptionsVisible = false;
        });

        // --- BOTON DE CONFIRMAR ---
        
        findViewById(R.id.btnAddUserConfirm).setOnClickListener(v -> {
            if (selectedType.isEmpty()) {
                Toast.makeText(this, "Selecciona un tipo de usuario", Toast.LENGTH_SHORT).show();
                return;
            }
            
            // Si ha elegido rol, guardamos (simulado)
            Toast.makeText(this, "Usuario añadido correctamente", Toast.LENGTH_SHORT).show();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("user_added", true);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // --- NAVEGACION ---

        findViewById(R.id.btnHomeTop).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
