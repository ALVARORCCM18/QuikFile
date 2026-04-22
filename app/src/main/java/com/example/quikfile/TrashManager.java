package com.example.quikfile;

import java.util.ArrayList;
import java.util.List;

// Este es el encargado de guardar las cosas que borramos
// Uso el patron Singleton para que solo haya una papelera en toda la app
public class TrashManager {
    private static TrashManager instance;
    private List<TrashItem> trashedItems;

    private TrashManager() {
        trashedItems = new ArrayList<>();
    }

    public static synchronized TrashManager getInstance() {
        if (instance == null) {
            instance = new TrashManager();
        }
        return instance;
    }

    // Metodo para meter algo en la papelera
    public void addItem(String name, int iconRes) {
        trashedItems.add(new TrashItem(name, iconRes));
    }

    // Para ver que hay en la papelera
    public List<TrashItem> getTrashedItems() {
        return trashedItems;
    }

    // Para vaciarla
    public void clearTrash() {
        trashedItems.clear();
    }

    // Una clase sencilla para representar cada cosa borrada
    public static class TrashItem {
        public String name;
        public int iconRes;

        public TrashItem(String name, int iconRes) {
            this.name = name;
            this.iconRes = iconRes;
        }
    }
}
