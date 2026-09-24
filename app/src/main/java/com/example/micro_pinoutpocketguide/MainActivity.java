package com.example.micro_pinoutpocketguide;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ComponentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewComponents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ComponentAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(component -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("component_id", component.getId());
            startActivity(intent);
        });

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Observe LiveData
        viewModel.getComponentsLiveData().observe(this, components -> {
            adapter.setComponents(components);
        });

        // Setup Search EditText
        EditText editSearch = findViewById(R.id.editSearch);
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.searchComponents(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}
