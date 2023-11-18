package com.example.uocquizgame.placeholder;

import com.example.uocquizgame.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<PlaceholderItem> UNITS = new ArrayList<PlaceholderItem>();
    private static String[]description = new String[]{"Introducción a los sistemas de comunicación inalámbricos",
            "Introducción a los dispositivos móviles",
            "Entornos de programación móviles",
            "Métodos para el desarrollo de aplicaciones móviles",
            "Desarrollo de aplicaciones basadas en Android",
            "Seguridad en dispositivos móviles"};
    private static int[] images = new int[]{R.drawable.icon1, R.drawable.icon2, R.drawable.icon3, R.drawable.icon4, R.drawable.icon5, R.drawable.icon6};

    public static final Map<String, PlaceholderItem> ITEM_MAP = new HashMap<String, PlaceholderItem>();

    public static final int COUNT = 6;

    static {
        // Add some sample items.
        for(int i=0;i<COUNT;i++)
            addItem(createPlaceholderItem(i));

    }

    private static void addItem(PlaceholderItem item) {
        UNITS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static PlaceholderItem createPlaceholderItem(int position) {
        return new PlaceholderItem(String.valueOf(position), description[position], images[position]);
    }

    /**
     * A placeholder item representing a piece of content.
     */
    public static class PlaceholderItem {
        public final String id;
        public final String description;
        public final int icon;

        public PlaceholderItem(String id, String description, int icon) {
            this.id = id;
            this.description = description;
            this.icon =icon;
        }

        @Override
        public String toString() {
            return description;
        }
    }
}