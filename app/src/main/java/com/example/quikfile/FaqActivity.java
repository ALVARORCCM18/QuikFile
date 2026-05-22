package com.example.quikfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FaqActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        // --- Spark Mascot ---
        View spark = findViewById(R.id.ivSparkMascot);
        if (spark != null) {
            spark.setOnClickListener(v -> 
                Toast.makeText(this, R.string.spark_msg_faq, Toast.LENGTH_SHORT).show());
        }

        // --- Navegación Barra Superior ---
        findViewById(R.id.btnSettingsTop).setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
            finish();
        });

        findViewById(R.id.btnHomeTop).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btnSharedTop).setOnClickListener(v -> {
            startActivity(new Intent(this, SharedActivity.class));
            finish();
        });

        findViewById(R.id.btnLoginTop).setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        // --- Lógica de Desplegables ---
        setupFaq(R.id.header1, R.id.answer1, R.id.arrow1);
        setupFaq(R.id.header2, R.id.answer2, R.id.arrow2);
        setupFaq(R.id.header3, R.id.answer3, R.id.arrow3);
        setupFaq(R.id.header4, R.id.answer4, R.id.arrow4);
        setupFaq(R.id.header5, R.id.answer5, R.id.arrow5);
        setupFaq(R.id.header6, R.id.answer6, R.id.arrow6);
        setupFaq(R.id.header7, R.id.answer7, R.id.arrow7);
        setupFaq(R.id.header8, R.id.answer8, R.id.arrow8);
        setupFaq(R.id.header9, R.id.answer9, R.id.arrow9);
        setupFaq(R.id.header10, R.id.answer10, R.id.arrow10);
        setupFaq(R.id.header11, R.id.answer11, R.id.arrow11);
        setupFaq(R.id.header12, R.id.answer12, R.id.arrow12);
        setupFaq(R.id.header13, R.id.answer13, R.id.arrow13);
        setupFaq(R.id.header14, R.id.answer14, R.id.arrow14);

        // --- Botón Atrás ---
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void setupFaq(int headerId, int answerId, int arrowId) {
        View header = findViewById(headerId);
        final View answer = findViewById(answerId);
        final ImageView arrow = findViewById(arrowId);

        header.setOnClickListener(v -> {
            if (answer.getVisibility() == View.GONE) {
                answer.setVisibility(View.VISIBLE);
                arrow.setImageResource(android.R.drawable.arrow_up_float);
            } else {
                answer.setVisibility(View.GONE);
                arrow.setImageResource(android.R.drawable.arrow_down_float);
            }
        });
    }
}
