package com.speech.audio.audio_player;

import com.speech.audio.audio_common.AudioConstant;

/**
 * @Description: AudioTrack播放.pcm文件
 * @ProjectName: ${PROJECT_NAME}
 * @Package: com.speech.audio.audio_player
 * @ClassName: AudioTrackManager
 * @Author: guyu.gao
 * @CreateDate: 2022/5/4 20:38
 * @UpdateUser: guyu.gao
 * @UpdateDate: 2022/5/4 20:38
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class AudioTrackManager {
    private static final String TAG = "AudioTrackManager";
    private static AudioTrackManager mInstance = null;

    private int mChannelConfig = AudioConstant.ChannelOut.CHANNEL_OUT_MONO.value();
    private int mAudioFormat = AudioConstant.AudioFormatEnum.ENCODING_PCM_16BIT.value();
    private int mStreamType = AudioConstant.StreamType.STREAM_TTS.value();


    private AudioTrackManager() {
    }

    public static AudioTrackManager getInstance() {
        if (mInstance == null) {
            synchronized (AudioTrackManager.class) {
                if (mInstance == null) {
                    mInstance = new AudioTrackManager();
                }
            }
        }

        return mInstance;
    }
}
