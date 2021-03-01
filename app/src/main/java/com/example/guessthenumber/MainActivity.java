package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    Button start,quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.start_button);
        quit = findViewById(R.id.quit_button);
        start.setOnClickListener(v -> {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.start_sound);
            startActivity(new Intent(this, start_activity.class));
            mp.start();
        });
        quit.setOnClickListener(v -> {
            System.exit(0);
        });
    }
}