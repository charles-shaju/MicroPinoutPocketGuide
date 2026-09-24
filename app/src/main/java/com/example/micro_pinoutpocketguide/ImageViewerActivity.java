package com.example.micro_pinoutpocketguide;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;

public class ImageViewerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        PhotoView photoView = findViewById(R.id.photoView);
        ImageButton btnClose = findViewById(R.id.btnClose);

        String imageName = getIntent().getStringExtra("image_name");
        if (imageName != null) {
            int imageResId = getResources().getIdentifier(imageName, "drawable", getPackageName());
            if (imageResId != 0) {
                photoView.setImageResource(imageResId);
            }
        }

        btnClose.setOnClickListener(v -> finish());
    }
}
