package com.example.micro_pinoutpocketguide;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HardwareRepository {

    private static final String ASSET_FILE_NAME = "components.json";

    public List<HardwareComponent> getComponents(Context context) {
        List<HardwareComponent> componentList = new ArrayList<>();
        try (InputStream inputStream = context.getAssets().open(ASSET_FILE_NAME);
             Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<HardwareComponent>>() {}.getType();
            componentList = gson.fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return componentList != null ? componentList : new ArrayList<>();
    }
}
