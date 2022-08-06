package com.speech.audio.audio_record;

import android.media.AudioRecord;
import android.os.Build;
import androidx.annotation.RequiresApi;

import com.speech.audio.audio_common.AudioConfigure;
import com.speech.audio.audio_dump.AudioDumpCore;
import com.speech.audio.base_utils.LogUtil;
import com.speech.audio.audio_common.AudioConstant;
import com.speech.audio.base_utils.TimeUtil;

/**
 * Description : AudioRecord工具类
 * Author : guyu.gao
 * Date : 2020/5/27
 */
class AudioRecordManager {
    private static final String TAG = AudioRecordManager.class.getSimpleName();
    private volatile static AudioRecordManager mInstance = null;

    private int mSampleRate = getDefaultSampleRate();
    private int mChannelSize = getDefaultChannelSize();
    private int mAudioSource = getDefaultAudioSource();
    private int mAudioFormat = getDefaultAudioFormat();
    private int mBufferSize = AudioRecord.getMinBufferSize(mSampleRate, mChannelSize, mAudioFormat);

    /**
     * AudioRecord 对象
     */
    private AudioRecord mAudioRecord = null;
    /**
     * 录音标志位，用于开始和中断读取录音数据。
     */
    private boolean mRecordFlag = false;
    /**
     * 录音线程
     */
    private Thread mRecordThread = null;
    /**
     * 对象锁
     */
    private final Object mLock = new Object();

    private AudioDumpCore mMicDump = null;

    private AudioRecordManager() {
    }

    static AudioRecordManager getInstance() {

        if (mInstance == null) {
            synchronized (AudioRecordManager.class) {
                if (mInstance == null) {
                    mInstance = new AudioRecordManager();
                }
            }
        }
        return mInstance;
    }

    synchronized void startRecord() {
        if (mAudioRecord == null) {
            mAudioRecord = new AudioRecord(mAudioSource, mSampleRate, mChannelSize, mAudioFormat, mBufferSize);
        }

        int state = mAudioRecord.getState();
        LogUtil.i(TAG, "Current record init state = " + state);
        if (state != AudioRecord.STATE_INITIALIZED) {
            LogUtil.e(TAG, "Unable to create AudioRecord!");
            return;
        }

        int recordState = mAudioRecord.getRecordingState();
        LogUtil.i(TAG, "Current recording state = " + recordState);
        if (recordState == AudioRecord.RECORDSTATE_RECORDING) {
            LogUtil.e(TAG, "Audio is recording now!");
            return;
        }

        startDump();
        mAudioRecord.startRecording();
        mRecordFlag = true;
        if (mRecordThread == null) {
            mRecordThread = new Thread(mRecordRunnable);
            mRecordThread.setName("audio_record_thread");
            mRecordThread.start();
        }
    }

    synchronized void stopRecord() {
        mRecordFlag = false;
        if (mAudioRecord != null) {
            mAudioRecord.stop();
        }
        stopDump();
    }

    void release() {
        mRecordFlag = false;
        if (mAudioRecord != null) {
            mAudioRecord.release();
            mAudioRecord = null;
        }
        mRecordThread = null;
        stopDump();
    }

    void startDump() {
        mMicDump = new AudioDumpCore(AudioConfigure.path(), "mic_" + TimeUtil.currentTime() + ".pcm");
        mMicDump.startDump();
    }

    void stopDump() {
        if (mMicDump == null) {
            return;
        }
        mMicDump.stopDump();
        mMicDump = null;
    }

    void setSampleRate(int mSampleRate) {
        LogUtil.i(TAG, "Set sample rate = " + mSampleRate);
        this.mSampleRate = mSampleRate;
    }

    void setChannelSize(int mChannelSize) {
        LogUtil.i(TAG, "Set channel size = " + mChannelSize);
        this.mChannelSize = mChannelSize;
    }

    void setAudioSource(int mAudioSource) {
        LogUtil.i(TAG, "Set audio source value = " + mAudioSource);
        this.mAudioSource = mAudioSource;
    }

    void setAudioFormat(int mAudioFormat) {
        LogUtil.i(TAG, "Set audio format value = " + mAudioFormat);
        this.mAudioFormat = mAudioFormat;
    }

    void setBufferSize(int mBufferSize) {
        LogUtil.i(TAG, "Set buffer size = " + mBufferSize);
        this.mBufferSize = mBufferSize;
    }

    int getDefaultSampleRate() {
        return AudioConstant.SampleRate.SAMPLE_RATE_16000.value();
    }

    int getDefaultChannelSize() {
        return AudioConstant.ChannelIn.CHANNEL_IN_MONO.value();
    }

    int getDefaultAudioSource() {
        return AudioConstant.AudioSource.MIC.value();
    }

    int getDefaultAudioFormat() {
        return AudioConstant.AudioFormatEnum.ENCODING_PCM_16BIT.value();
    }

    int getDefaultBufferSize() {
        return mBufferSize;
    }

    private Runnable mRecordRunnable = new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void run() {
            byte[] audioData = new byte[mBufferSize];
            while (mRecordFlag) {
                synchronized (mLock) {
                    int readSize = mAudioRecord.read(audioData, 0, mBufferSize);
                    if (readSize < 0) {
                        LogUtil.i(TAG, "audio record read result = " + readSize);
                        break;
                    }

                    mMicDump.writeAudioByte(audioData);
                }
            }
            mMicDump.createWavFile(mSampleRate, AudioConstant.getChannelInSize(mChannelSize), mAudioFormat == AudioConstant.AudioFormatEnum.ENCODING_PCM_16BIT.value() ? 16 : 8, mBufferSize);
        }
    };
}
