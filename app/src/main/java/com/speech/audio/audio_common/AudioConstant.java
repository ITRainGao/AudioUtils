package com.speech.audio.audio_common;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.os.Build;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AudioConstant {
    public enum AudioSource {
        DEFAULT(MediaRecorder.AudioSource.DEFAULT),
        MIC(MediaRecorder.AudioSource.MIC),
        VOICE_UPLINK(MediaRecorder.AudioSource.VOICE_UPLINK),
        VOICE_DOWNLINK(MediaRecorder.AudioSource.VOICE_DOWNLINK),
        VOICE_CALL(MediaRecorder.AudioSource.VOICE_CALL),
        CAMCORDER(MediaRecorder.AudioSource.CAMCORDER),
        VOICE_RECOGNITION(MediaRecorder.AudioSource.VOICE_RECOGNITION),
        VOICE_COMMUNICATION(MediaRecorder.AudioSource.VOICE_COMMUNICATION),
        REMOTE_SUBMIX(MediaRecorder.AudioSource.REMOTE_SUBMIX),
        UNPROCESSED(MediaRecorder.AudioSource.UNPROCESSED);
        // AUDIO_SOURCE_NOMI( 1996),
        // AUDIO_SOURCE_AEC_REF(1997);

        private int value;

        private AudioSource(int data) {
            this.value = data;
        }

        public int value() {
            return value;
        }
    }

    public enum SampleRate {
        SAMPLE_RATE_4000(4000),
        SAMPLE_RATE_8000(8000),
        SAMPLE_RATE_11025(11025),
        SAMPLE_RATE_16000(16000),
        SAMPLE_RATE_22050(22050),
        SAMPLE_RATE_32000(32000),
        SAMPLE_RATE_44100(44100),
        SAMPLE_RATE_48000(48000),
        SAMPLE_RATE_88200(88200),
        SAMPLE_RATE_96000(96000),
        SAMPLE_RATE_176400(176400),
        SAMPLE_RATE_192000(192000);

        private int value;

        private SampleRate(int data) {
            value = data;
        }

        public int value() {
            return value;
        }
    }

    public enum AudioFormatEnum {
        ENCODING_INVALID(AudioFormat.ENCODING_INVALID),
        ENCODING_DEFAULT(AudioFormat.ENCODING_DEFAULT),
        // These values must be kept in sync with core/jni/android_media_AudioFormat.h
        // Also sync av/services/audiopolicy/managerdefault/ConfigParsingUtils.h
        /**
         * Audio data format: PCM 16 bit per sample. Guaranteed to be supported by devices.
         */
        ENCODING_PCM_16BIT(AudioFormat.ENCODING_PCM_16BIT),
        /**
         * Audio data format: PCM 8 bit per sample. Not guaranteed to be supported by devices.
         */
        ENCODING_PCM_8BIT(AudioFormat.ENCODING_PCM_8BIT),
        /**
         * Audio data format: single-precision floating-point per sample
         */
        ENCODING_PCM_FLOAT(AudioFormat.ENCODING_PCM_FLOAT),
        /**
         * Audio data format: AC-3 compressed
         */
        ENCODING_AC3(AudioFormat.ENCODING_AC3),
        /**
         * Audio data format: E-AC-3 compressed
         */
        ENCODING_E_AC3(AudioFormat.ENCODING_E_AC3),
        /**
         * Audio data format: DTS compressed
         */
        ENCODING_DTS(AudioFormat.ENCODING_DTS),
        /**
         * Audio data format: DTS HD compressed
         */
        ENCODING_DTS_HD(AudioFormat.ENCODING_DTS_HD),
        /**
         * Audio data format: compressed audio wrapped in PCM for HDMI or S/PDIF passthrough.
         * IEC61937 uses a stereo stream of 16-bit samples as the wrapper.
         * So the channel mask for the track must be {CHANNEL_OUT_STEREO}.
         * Data should be written to the stream in a short[] array.
         * If the data is written in a byte[] array then there may be endian problems on some platforms when converting to short internally.
         */
        ENCODING_IEC61937(AudioFormat.ENCODING_IEC61937),
        /**
         * Audio data format: DOLBY TRUEHD compressed
         */
        ENCODING_DOLBY_TRUEHD(AudioFormat.ENCODING_DOLBY_TRUEHD);

        private int value;

        private AudioFormatEnum(int data) {
            value = data;
        }

        public int value() {
            return value;
        }
    }

    public enum ChannelIn {
        CHANNEL_IN_DEFAULT(AudioFormat.CHANNEL_IN_DEFAULT),
        CHANNEL_IN_LEFT(AudioFormat.CHANNEL_IN_LEFT),
        CHANNEL_IN_RIGHT(AudioFormat.CHANNEL_IN_RIGHT),
        CHANNEL_IN_FRONT(AudioFormat.CHANNEL_IN_FRONT),
        CHANNEL_IN_BACK(AudioFormat.CHANNEL_IN_BACK),
        CHANNEL_IN_LEFT_PROCESSED(AudioFormat.CHANNEL_IN_LEFT_PROCESSED),
        CHANNEL_IN_RIGHT_PROCESSED(AudioFormat.CHANNEL_IN_RIGHT_PROCESSED),
        CHANNEL_IN_FRONT_PROCESSED(AudioFormat.CHANNEL_IN_FRONT_PROCESSED),
        CHANNEL_IN_BACK_PROCESSED(AudioFormat.CHANNEL_IN_BACK_PROCESSED),
        CHANNEL_IN_PRESSURE(AudioFormat.CHANNEL_IN_PRESSURE),
        CHANNEL_IN_X_AXIS(AudioFormat.CHANNEL_IN_X_AXIS),
        CHANNEL_IN_Y_AXIS(AudioFormat.CHANNEL_IN_Y_AXIS),
        CHANNEL_IN_Z_AXIS(AudioFormat.CHANNEL_IN_Z_AXIS),
        CHANNEL_IN_VOICE_UPLINK(AudioFormat.CHANNEL_IN_VOICE_UPLINK),
        CHANNEL_IN_VOICE_DNLINK(AudioFormat.CHANNEL_IN_VOICE_DNLINK),
        CHANNEL_IN_MONO(AudioFormat.CHANNEL_IN_MONO),
        CHANNEL_IN_STEREO(AudioFormat.CHANNEL_IN_STEREO),
        CHANNEL_IN_FRONT_BACK(AudioFormat.CHANNEL_IN_FRONT | AudioFormat.CHANNEL_IN_BACK),
        CHANNEL_IN_THREE(AudioFormat.CHANNEL_IN_LEFT | AudioFormat.CHANNEL_IN_RIGHT | AudioFormat.CHANNEL_IN_BACK),
        CHANNEL_IN_FOUR(AudioFormat.CHANNEL_IN_LEFT |
                AudioFormat.CHANNEL_IN_RIGHT |
                AudioFormat.CHANNEL_IN_FRONT |
                AudioFormat.CHANNEL_IN_BACK
        ),
        CHANNEL_IN_FIVE(AudioFormat.CHANNEL_IN_LEFT |
                AudioFormat.CHANNEL_IN_RIGHT |
                AudioFormat.CHANNEL_IN_FRONT |
                AudioFormat.CHANNEL_IN_BACK |
                AudioFormat.CHANNEL_IN_LEFT_PROCESSED
        ),
        CHANNEL_IN_SIX(AudioFormat.CHANNEL_IN_LEFT |
                AudioFormat.CHANNEL_IN_RIGHT |
                AudioFormat.CHANNEL_IN_FRONT |
                AudioFormat.CHANNEL_IN_BACK |
                AudioFormat.CHANNEL_IN_LEFT_PROCESSED |
                AudioFormat.CHANNEL_IN_RIGHT_PROCESSED
        ),
        CHANNEL_IN_EIGHT(AudioFormat.CHANNEL_IN_LEFT |
                AudioFormat.CHANNEL_IN_RIGHT |
                AudioFormat.CHANNEL_IN_FRONT |
                AudioFormat.CHANNEL_IN_BACK |
                AudioFormat.CHANNEL_IN_LEFT_PROCESSED |
                AudioFormat.CHANNEL_IN_RIGHT_PROCESSED |
                AudioFormat.CHANNEL_IN_FRONT_PROCESSED |
                AudioFormat.CHANNEL_IN_BACK_PROCESSED
        ),
        CHANNEL_IN_TWELVE(AudioFormat.CHANNEL_IN_LEFT |
                AudioFormat.CHANNEL_IN_RIGHT |
                AudioFormat.CHANNEL_IN_FRONT |
                AudioFormat.CHANNEL_IN_BACK |
                AudioFormat.CHANNEL_IN_LEFT_PROCESSED |
                AudioFormat.CHANNEL_IN_RIGHT_PROCESSED |
                AudioFormat.CHANNEL_IN_FRONT_PROCESSED |
                AudioFormat.CHANNEL_IN_BACK_PROCESSED |
                AudioFormat.CHANNEL_IN_PRESSURE |
                AudioFormat.CHANNEL_IN_X_AXIS |
                AudioFormat.CHANNEL_IN_Y_AXIS |
                AudioFormat.CHANNEL_IN_Z_AXIS
        );

        private int value;

        private ChannelIn(int data) {
            value = data;
        }

        public int value() {
            return value;
        }
    }

    /**
     * 代码常量数值向声道数转换
     *
     * @param value 代码中定义的常量数值
     * @return 实际声道数
     */
    public static int getChannelInSize(int value) {
        if (value == ChannelIn.CHANNEL_IN_STEREO.value() || value == ChannelIn.CHANNEL_IN_FRONT_BACK.value()) {
            return 2;
        } else if (value == ChannelIn.CHANNEL_IN_THREE.value()) {
            return 3;
        } else if (value == ChannelIn.CHANNEL_IN_FOUR.value()) {
            return 4;
        } else if (value == ChannelIn.CHANNEL_IN_FIVE.value()) {
            return 5;
        } else if (value == ChannelIn.CHANNEL_IN_SIX.value()) {
            return 6;
        } else if (value == ChannelIn.CHANNEL_IN_EIGHT.value()) {
            return 8;
        } else if (value == ChannelIn.CHANNEL_IN_TWELVE.value()) {
            return 12;
        } else {
            return 1;
        }
    }

    public enum ChannelOut {
        CHANNEL_INVALID(AudioFormat.CHANNEL_INVALID),
        CHANNEL_OUT_DEFAULT(AudioFormat.CHANNEL_OUT_DEFAULT),
        CHANNEL_OUT_FRONT_LEFT(AudioFormat.CHANNEL_OUT_FRONT_LEFT),
        CHANNEL_OUT_FRONT_RIGHT(AudioFormat.CHANNEL_OUT_FRONT_RIGHT),
        CHANNEL_OUT_FRONT_CENTER(AudioFormat.CHANNEL_OUT_FRONT_CENTER),
        CHANNEL_OUT_LOW_FREQUENCY(AudioFormat.CHANNEL_OUT_LOW_FREQUENCY),
        CHANNEL_OUT_BACK_LEFT(AudioFormat.CHANNEL_OUT_BACK_LEFT),
        CHANNEL_OUT_BACK_RIGHT(AudioFormat.CHANNEL_OUT_BACK_RIGHT),
        CHANNEL_OUT_FRONT_LEFT_OF_CENTER(AudioFormat.CHANNEL_OUT_FRONT_LEFT_OF_CENTER),
        CHANNEL_OUT_FRONT_RIGHT_OF_CENTER(AudioFormat.CHANNEL_OUT_FRONT_RIGHT_OF_CENTER),
        CHANNEL_OUT_BACK_CENTER(AudioFormat.CHANNEL_OUT_BACK_CENTER),
        CHANNEL_OUT_SIDE_LEFT(AudioFormat.CHANNEL_OUT_SIDE_LEFT),
        CHANNEL_OUT_SIDE_RIGHT(AudioFormat.CHANNEL_OUT_SIDE_RIGHT),
        CHANNEL_OUT_TOP_CENTER(0x2000),
        CHANNEL_OUT_TOP_FRONT_LEFT(0x4000),
        CHANNEL_OUT_TOP_FRONT_CENTER(0x8000),
        CHANNEL_OUT_TOP_FRONT_RIGHT(0x10000),
        CHANNEL_OUT_TOP_BACK_LEFT(0x20000),
        CHANNEL_OUT_TOP_BACK_CENTER(0x40000),
        CHANNEL_OUT_TOP_BACK_RIGHT(0x80000),
        CHANNEL_OUT_MONO(AudioFormat.CHANNEL_OUT_FRONT_LEFT),
        CHANNEL_OUT_STEREO(AudioFormat.CHANNEL_OUT_STEREO),
        CHANNEL_OUT_QUAD(AudioFormat.CHANNEL_OUT_QUAD),
        CHANNEL_OUT_QUAD_SIDE(AudioFormat.CHANNEL_OUT_FRONT_LEFT |
                AudioFormat.CHANNEL_OUT_FRONT_RIGHT |
                AudioFormat.CHANNEL_OUT_SIDE_LEFT |
                AudioFormat.CHANNEL_OUT_SIDE_RIGHT
        ),
        CHANNEL_OUT_SURROUND(AudioFormat.CHANNEL_OUT_SURROUND),
        CHANNEL_OUT_5POINT1(AudioFormat.CHANNEL_OUT_5POINT1),
        CHANNEL_OUT_5POINT1_SIDE(AudioFormat.CHANNEL_OUT_FRONT_LEFT |
                AudioFormat.CHANNEL_OUT_FRONT_RIGHT |
                AudioFormat.CHANNEL_OUT_FRONT_CENTER |
                AudioFormat.CHANNEL_OUT_LOW_FREQUENCY |
                AudioFormat.CHANNEL_OUT_SIDE_LEFT |
                AudioFormat.CHANNEL_OUT_SIDE_RIGHT
        ),
        CHANNEL_OUT_7POINT1(AudioFormat.CHANNEL_OUT_7POINT1),
        CHANNEL_OUT_7POINT1_SURROUND(AudioFormat.CHANNEL_OUT_7POINT1_SURROUND);

        private int value;

        private ChannelOut(int data) {
            value = data;
        }

        public int value() {
            return value;
        }
    }

    public enum StreamType {
        STREAM_VOICE_CALL(AudioManager.STREAM_VOICE_CALL),
        STREAM_SYSTEM(AudioManager.STREAM_SYSTEM),
        STREAM_RING(AudioManager.STREAM_RING),
        STREAM_MUSIC(AudioManager.STREAM_MUSIC),
        STREAM_ALARM(AudioManager.STREAM_ALARM),
        STREAM_NOTIFICATION(AudioManager.STREAM_NOTIFICATION),
        STREAM_BLUETOOTH_SCO(6),
        STREAM_SYSTEM_ENFORCED(7),
        STREAM_DTMF(AudioManager.STREAM_DTMF),
        STREAM_TTS(9),
        STREAM_ACCESSIBILITY(AudioManager.STREAM_ACCESSIBILITY),
        STREAM_11(11),
        STREAM_12(12),
        STREAM_13(13),
        STREAM_14(14),
        STREAM_15(15);

        private int value;

        private StreamType(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }
}
