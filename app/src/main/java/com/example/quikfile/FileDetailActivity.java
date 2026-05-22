package com.example.quikfile;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

// Esta es la pantalla de informacion detallada de un fichero y su menu de acciones
public class FileDetailActivity extends AppCompatActivity {

    private EditText etFileName;
    private View btnMenu, btnCloseMenu, btnBack;
    private View btnDownload, btnDelete, btnReport, btnPreview, btnShare;
    private boolean isMenuOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_detail);

        etFileName = findViewById(R.id.etFileName);
        btnMenu = findViewById(R.id.btnMenu);
        btnCloseMenu = findViewById(R.id.btnCloseMenu);
        btnBack = findViewById(R.id.btnBack);

        // Referencias a los botones que saltan en el menu circular
        btnDownload = findViewById(R.id.btnDownload);
        btnDelete = findViewById(R.id.btnDelete);
        btnReport = findViewById(R.id.btnReport);
        btnPreview = findViewById(R.id.btnPreview);
        btnShare = findViewById(R.id.btnShare);

        // Cojo el nombre del fichero que me pasan
        String fileName = getIntent().getStringExtra("fileName");
        if (fileName != null) {
            etFileName.setText(fileName);
        }

        // Para cambiar el nombre del archivo
        findViewById(R.id.btnEditName).setOnClickListener(v -> {
            etFileName.requestFocus();
            etFileName.setSelection(etFileName.getText().length());
            Toast.makeText(this, R.string.file_renamed_success, Toast.LENGTH_SHORT).show();
        });

        // Abrir y cerrar el menu circular
        btnMenu.setOnClickListener(v -> toggleMenu());
        btnCloseMenu.setOnClickListener(v -> toggleMenu());

        // Boton para volver
        btnBack.setOnClickListener(v -> finish());

        // --- ACCIONES DE LOS BOTONES DEL MENU ---

        btnDownload.setOnClickListener(v -> { toggleMenu(); Toast.makeText(this, R.string.downloading, Toast.LENGTH_SHORT).show(); });
        
        btnDelete.setOnClickListener(v -> {
            toggleMenu();
            // Pido confirmacion antes de cargarme el archivo
            new AlertDialog.Builder(this)
                    .setTitle(R.string.delete_item_title)
                    .setMessage(getString(R.string.move_to_trash_confirm, etFileName.getText().toString()))
                    .setPositiveButton(R.string.delete, (dialog, which) -> {
                        Toast.makeText(this, R.string.file_deleted, Toast.LENGTH_SHORT).show();
                        finish(); // Me vuelvo atras al borrar
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .show();
        });

        // Ir a la pantalla de reportes
        btnReport.setOnClickListener(v -> {
            toggleMenu();
            Intent intent = new Intent(this, FileReportActivity.class);
            intent.putExtra("fileName", etFileName.getText().toString());
            startActivity(intent);
        });

        // Ver el contenido del fichero
        btnPreview.setOnClickListener(v -> {
            toggleMenu();
            Intent intent = new Intent(this, FilePreviewActivity.class);
            intent.putExtra("fileName", etFileName.getText().toString());
            startActivity(intent);
        });
        
        // Compartir con alguien
        btnShare.setOnClickListener(v -> {
            toggleMenu();
            Intent intent = new Intent(this, FileShareActivity.class);
            intent.putExtra("fileName", etFileName.getText().toString());
            startActivity(intent);
        });
        
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

    // Alternar entre abrir y cerrar el menu
    private void toggleMenu() {
        if (!isMenuOpen) {
            openMenu();
        } else {
            closeMenu();
        }
        isMenuOpen = !isMenuOpen;
    }

    // Animacion para desplegar los iconos en arco
    private void openMenu() {
        btnMenu.setVisibility(View.INVISIBLE);
        btnCloseMenu.setVisibility(View.VISIBLE);
        
        // Quito la flecha de atras para que no moleste visualmente
        btnBack.animate().alpha(0f).setDuration(200).withEndAction(() -> btnBack.setVisibility(View.GONE)).start();

        float radius = 130 * getResources().getDisplayMetrics().density;
        float[] angles = {180f, 225f, 270f, 315f, 360f}; // Posiciones en el arco
        View[] items = {btnPreview, btnShare, btnDownload, btnDelete, btnReport};

        AnimatorSet animatorSet = new AnimatorSet();
        List<Animator> animations = new ArrayList<>();

        for (int i = 0; i < items.length; i++) {
            View item = items[i];
            item.setVisibility(View.VISIBLE);
            item.setAlpha(0f);

            // Calculo donde tiene que acabar cada icono
            double angleRad = Math.toRadians(angles[i]);
            float translationX = (float) (radius * Math.cos(angleRad));
            float translationY = (float) (radius * Math.sin(angleRad));

            // Animaciones de movimiento, transparencia y tamaño
            animations.add(ObjectAnimator.ofFloat(item, "translationX", 0f, translationX));
            animations.add(ObjectAnimator.ofFloat(item, "translationY", 0f, translationY));
            animations.add(ObjectAnimator.ofFloat(item, "alpha", 0f, 1f));
            animations.add(ObjectAnimator.ofFloat(item, "scaleX", 0.5f, 1f));
            animations.add(ObjectAnimator.ofFloat(item, "scaleY", 0.5f, 1f));
        }

        animatorSet.playTogether(animations);
        animatorSet.setDuration(400);
        animatorSet.setInterpolator(new OvershootInterpolator()); // Efecto rebote al final
        animatorSet.start();
    }

    // Animacion para recoger los iconos hacia el centro
    private void closeMenu() {
        View[] items = {btnPreview, btnShare, btnDownload, btnDelete, btnReport};
        
        // Recupero la flecha de atras
        btnBack.setVisibility(View.VISIBLE);
        btnBack.animate().alpha(1f).setDuration(200).start();

        AnimatorSet animatorSet = new AnimatorSet();
        List<Animator> animations = new ArrayList<>();

        for (View item : items) {
            // Todos vuelven al origen (0,0)
            animations.add(ObjectAnimator.ofFloat(item, "translationX", 0f));
            animations.add(ObjectAnimator.ofFloat(item, "translationY", 0f));
            animations.add(ObjectAnimator.ofFloat(item, "alpha", 1f, 0f));
            animations.add(ObjectAnimator.ofFloat(item, "scaleX", 1f, 0.5f));
            animations.add(ObjectAnimator.ofFloat(item, "scaleY", 1f, 0.5f));
        }

        animatorSet.playTogether(animations);
        animatorSet.setDuration(300);
        animatorSet.setInterpolator(new AnticipateInterpolator()); // Efecto de tomar impulso antes de cerrar
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override public void onAnimationStart(Animator animation) {}
            @Override public void onAnimationEnd(Animator animation) {
                // Al terminar escondo y vuelvo a poner el boton principal
                for (View item : items) item.setVisibility(View.INVISIBLE);
                btnMenu.setVisibility(View.VISIBLE);
                btnCloseMenu.setVisibility(View.GONE);
            }
            @Override public void onAnimationCancel(Animator animation) {}
            @Override public void onAnimationRepeat(Animator animation) {}
        });
        animatorSet.start();
    }
}
