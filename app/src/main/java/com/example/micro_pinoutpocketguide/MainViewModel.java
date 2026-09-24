package com.example.micro_pinoutpocketguide;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private final HardwareRepository repository;
    private final MutableLiveData<List<HardwareComponent>> componentsLiveData = new MutableLiveData<>();
    private List<HardwareComponent> allComponents = new ArrayList<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new HardwareRepository();
        loadComponents();
    }

    public LiveData<List<HardwareComponent>> getComponentsLiveData() {
        return componentsLiveData;
    }

    public void loadComponents() {
        allComponents = repository.getComponents(getApplication());
        componentsLiveData.setValue(allComponents);
    }

    public void searchComponents(String query) {
        if (query == null || query.trim().isEmpty()) {
            componentsLiveData.setValue(allComponents);
            return;
        }

        String lowerCaseQuery = query.toLowerCase().trim();
        List<HardwareComponent> filteredList = new ArrayList<>();
        for (HardwareComponent component : allComponents) {
            if (component.getName() != null && component.getName().toLowerCase().contains(lowerCaseQuery)) {
                filteredList.add(component);
            }
        }
        componentsLiveData.setValue(filteredList);
    }
}
