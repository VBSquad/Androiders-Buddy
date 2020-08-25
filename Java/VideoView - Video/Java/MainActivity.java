package com.example.mytubem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoView motivation = findViewById(R.id.motivation);
        motivation.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.clip );
        MediaController mediaController = new MediaController(this);
        motivation.setMediaController(mediaController);
        mediaController.setAnchorView(motivation);
        motivation.start();

    }
}
