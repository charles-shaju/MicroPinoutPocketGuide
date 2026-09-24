package com.example.micro_pinoutpocketguide;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class FavoritesManager {
    private static final String PREF_NAME = "workbench_prefs";
    private static final String KEY_FAVORITES = "favorite_components";

    private final SharedPreferences prefs;

    public FavoritesManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public Set<String> getFavorites() {
        return prefs.getStringSet(KEY_FAVORITES, new HashSet<>());
    }

    public boolean isFavorite(String componentId) {
        Set<String> favorites = getFavorites();
        return favorites.contains(componentId);
    }

    public void toggleFavorite(String componentId) {
        Set<String> favorites = new HashSet<>(getFavorites());
        if (favorites.contains(componentId)) {
            favorites.remove(componentId);
        } else {
            favorites.add(componentId);
        }
        prefs.edit().putStringSet(KEY_FAVORITES, favorites).apply();
    }
}
