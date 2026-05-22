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

        // --- Spark Traducido ---
        View mascot = findViewById(R.id.ivSparkMascot);
        if (mascot != null) {
            mascot.setOnClickListener(v -> Toast.makeText(this, R.string.spark_msg_add_user, Toast.LENGTH_SHORT).show());
        }

        View header = findViewById(R.id.selectorHeader);
        LinearLayout options = findViewById(R.id.optionsLayout);
        ImageView arrow = findViewById(R.id.ivArrow);
        TextView typeText = findViewById(R.id.tvSelectedType);

        if (header != null && options != null) {
            header.setOnClickListener(v -> {
                isOptionsVisible = !isOptionsVisible;
                options.setVisibility(isOptionsVisible ? View.VISIBLE : View.GONE);
                if (arrow != null) arrow.setImageResource(isOptionsVisible ? android.R.drawable.arrow_up_float : android.R.drawable.arrow_down_float);
            });
        }

        View optAdmin = findViewById(R.id.optionAdmin);
        if (optAdmin != null) {
            optAdmin.setOnClickListener(v -> {
                selectedType = getString(R.string.administrator);
                if (typeText != null) typeText.setText(selectedType);
                options.setVisibility(View.GONE);
                isOptionsVisible = false;
            });
        }

        View optMember = findViewById(R.id.optionMember);
        if (optMember != null) {
            optMember.setOnClickListener(v -> {
                selectedType = getString(R.string.member);
                if (typeText != null) typeText.setText(selectedType);
                options.setVisibility(View.GONE);
                isOptionsVisible = false;
            });
        }

        View confirm = findViewById(R.id.btnAddUserConfirm);
        if (confirm != null) {
            confirm.setOnClickListener(v -> {
                if (selectedType.isEmpty()) {
                    Toast.makeText(this, R.string.select_type_hint, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, R.string.user_added_success, Toast.LENGTH_SHORT).show();
                    Intent result = new Intent();
                    result.putExtra("user_added", true);
                    setResult(RESULT_OK, result);
                    finish();
                }
            });
        }

        View btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) btnBack.setOnClickListener(v -> finish());
    }
}
