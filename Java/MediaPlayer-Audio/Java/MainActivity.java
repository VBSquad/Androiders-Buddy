package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaplayer;

     public void play(View view)
     {
       mediaplayer.start();
     }

    public void pause(View view)
    {
        mediaplayer.pause();
    }

    public void stop(View view)
    {
        mediaplayer.stop();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaplayer = MediaPlayer.create(this,R.raw.song);
    }
}
