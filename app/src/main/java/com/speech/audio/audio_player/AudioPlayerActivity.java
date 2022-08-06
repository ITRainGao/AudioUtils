package com.speech.audio.audio_player;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.speech.audio.R;

/**
 * 1.遍历指定文件夹pcm和wav，分别用Spinner方式展示。
 * 2.pcm采用AudioTrack播放
 * 3.wav采用MediaPlayer播放
 */
public class AudioPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_track);
    }
}
