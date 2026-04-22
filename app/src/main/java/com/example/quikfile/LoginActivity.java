package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// Pantalla de acceso. Lo primero que ves si no has entrado antes.
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnLoginAction = findViewById(R.id.btnLoginAction);
        TextView btnGoToRegister = findViewById(R.id.btnGoToRegister);

        // Cuando el usuario le da a entrar
        btnLoginAction.setOnClickListener(v -> {
            String user = etUsername.getText().toString();
            String pass = etPassword.getText().toString();

            // Compruebo que no deje los campos vacios
            if (!user.isEmpty() && !pass.isEmpty()) {
                // a la pantalla principal
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Quito el login del historial para que no vuelva atras al salir
            } else {
                Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
            }
        });

        // Por si no tiene cuenta todavia
        btnGoToRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
