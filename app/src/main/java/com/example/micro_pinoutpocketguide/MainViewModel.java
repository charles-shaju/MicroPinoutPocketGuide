package com.example.micro_pinoutpocketguide;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainViewModel extends AndroidViewModel {

    private final HardwareRepository repository;
    private final FavoritesManager favoritesManager;
    private final MutableLiveData<List<HardwareComponent>> componentsLiveData = new MutableLiveData<>();
    private List<HardwareComponent> allComponents = new ArrayList<>();

    private String currentQuery = "";
    private boolean showOnlyFavorites = false;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new HardwareRepository();
        favoritesManager = new FavoritesManager(application);
        loadComponents();
    }

    public LiveData<List<HardwareComponent>> getComponentsLiveData() {
        return componentsLiveData;
    }

    public Set<String> getFavoriteIds() {
        return favoritesManager.getFavorites();
    }

    public void loadComponents() {
        allComponents = repository.getComponents(getApplication());
        applyFilter();
    }

    public void searchComponents(String query) {
        currentQuery = query != null ? query : "";
        applyFilter();
    }

    public void setOnlyFavorites(boolean onlyFavorites) {
        showOnlyFavorites = onlyFavorites;
        applyFilter();
    }

    public void toggleFavorite(String componentId) {
        favoritesManager.toggleFavorite(componentId);
        applyFilter();
    }

    private void applyFilter() {
        Set<String> favoriteIds = favoritesManager.getFavorites();
        List<HardwareComponent> filteredList = new ArrayList<>();

        String lowerCaseQuery = currentQuery.toLowerCase().trim();

        for (HardwareComponent component : allComponents) {
            boolean matchesFavorites = !showOnlyFavorites || favoriteIds.contains(component.getId());
            boolean matchesSearch = lowerCaseQuery.isEmpty() ||
                    (component.getName() != null && component.getName().toLowerCase().contains(lowerCaseQuery));

            if (matchesFavorites && matchesSearch) {
                filteredList.add(component);
            }
        }
        componentsLiveData.setValue(filteredList);
    }
}
