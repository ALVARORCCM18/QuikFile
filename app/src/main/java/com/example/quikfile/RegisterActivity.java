package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etUsername = findViewById(R.id.etUsername);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        EditText etRepeatPassword = findViewById(R.id.etRepeatPassword);
        Button btnRegisterAction = findViewById(R.id.btnRegisterAction);
        TextView btnGoToLogin = findViewById(R.id.btnGoToLogin);

        // Al pulsar Registrarse, simulamos éxito y volvemos al login
        btnRegisterAction.setOnClickListener(v -> {
            String user = etUsername.getText().toString();
            String email = etEmail.getText().toString();
            String pass = etPassword.getText().toString();
            String repeatPass = etRepeatPassword.getText().toString();

            if (!user.isEmpty() && !email.isEmpty() && !pass.isEmpty() && !repeatPass.isEmpty()) {
                if (pass.equals(repeatPass)) {
                    Toast.makeText(this, R.string.registration_success, Toast.LENGTH_SHORT).show();
                    finish(); // Vuelve al Login
                } else {
                    Toast.makeText(this, R.string.passwords_do_not_match, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
            }
        });

        // Al pulsar "Iniciar sesion", volvemos a la pantalla de Login
        btnGoToLogin.setOnClickListener(v -> {
            finish(); // Cierra esta actividad y vuelve a la anterior (Login)
        });
    }
}
