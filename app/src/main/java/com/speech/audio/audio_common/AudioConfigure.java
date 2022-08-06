package com.speech.audio.audio_common;

import android.annotation.SuppressLint;
import android.os.Environment;

import com.speech.audio.base_utils.LogUtil;

import java.io.File;

public class AudioConfigure {
    private static final String TAG = "AudioConfigure";

    private static final String sRootPath = Environment.getExternalStorageDirectory().getPath() + "/audio/";

    public static void init() {
        File path = new File(sRootPath);
        if (!path.exists()) {
            boolean result = path.mkdirs();
            if (!result) {
                LogUtil.e(TAG, "File path : " + path.getAbsolutePath() + " create fail!");
            }
        }
    }

    public static String path() {
        return sRootPath;
    }
}
