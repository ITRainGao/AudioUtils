package com.speech.audio;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.speech.audio.audio_common.AudioConfigure;
import com.speech.audio.audio_record.AudioRecordActivity;
import com.speech.audio.audio_player.AudioPlayerActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AudioConfigure.init();

        findViewById(R.id.audio_record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AudioRecordActivity.class));
            }
        });

        findViewById(R.id.audio_track).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AudioPlayerActivity.class));
            }
        });
    }
}