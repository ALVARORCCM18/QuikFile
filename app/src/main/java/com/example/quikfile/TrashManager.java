package com.example.quikfile;

import java.util.ArrayList;
import java.util.List;

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

    public void addItem(String name, int iconRes) {
        trashedItems.add(new TrashItem(name, iconRes));
    }

    public List<TrashItem> getTrashedItems() {
        return trashedItems;
    }

    public void clearTrash() {
        trashedItems.clear();
    }

    public static class TrashItem {
        public String name;
        public int iconRes;

        public TrashItem(String name, int iconRes) {
            this.name = name;
            this.iconRes = iconRes;
        }
    }
}
