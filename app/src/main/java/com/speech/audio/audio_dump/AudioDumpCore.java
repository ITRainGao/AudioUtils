package com.speech.audio.audio_dump;

import com.speech.audio.base_utils.LogUtil;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class AudioDumpCore {
    private static final String TAG = "AudioDumpCore";
    private DataOutputStream mDataOutputStream;

    private String mFilePath;

    private String mFileName;

    public AudioDumpCore(String path, String fileName) {
        mFilePath = path;
        mFileName = fileName;
        File f = new File(mFilePath + mFileName);
        if (f.exists()) {
            f.delete();
            LogUtil.i(TAG, "delete same name file!");
        }
    }

    public void startDump() {
        File path = new File(mFilePath);
        if (!path.exists()) {
            path.mkdirs();
        }
        try {
            File f = new File(mFilePath + mFileName);
            LogUtil.w(TAG, "mFilePath :" + mFilePath + " mFileName: " + mFileName);
            FileOutputStream outs = new FileOutputStream(f, true);
            BufferedOutputStream bouts = new BufferedOutputStream(outs);
            mDataOutputStream = new DataOutputStream(bouts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void writeAudioByte(byte[] bytes) {
        try {
            if (mDataOutputStream != null) {
                mDataOutputStream.write(bytes);
                mDataOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopDump() {
        if (mDataOutputStream != null) {
            try {
                mDataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] shortToBytes(short[] shorts) {
        if (shorts == null) {
            return null;
        }
        byte[] bytes = new byte[shorts.length * 2];
        ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().put(shorts);

        return bytes;
    }

    public void createWavFile(long longSampleRate, int channels, int audioFormat, int bufferSize) {
        LogUtil.i(TAG, "create wav file sampleRate = " + longSampleRate + " , channels = " + channels + " , audioFormat = " + audioFormat + " , bufferSize = " + bufferSize);
        FileInputStream in = null;
        FileOutputStream out = null;
        long totalAudioLen = 0;
        long totalDataLen = totalAudioLen + 36;
        long byteRate = audioFormat * longSampleRate * channels / 8;
        byte[] data = new byte[bufferSize];
        try {
            in = new FileInputStream(mFilePath + mFileName);
            out = new FileOutputStream(mFilePath + mFileName.replace(".pcm", ".wav"));
            totalAudioLen = in.getChannel().size();
            totalDataLen = totalAudioLen + 36;
            WriteWaveFileHeader(out, totalAudioLen, totalDataLen, longSampleRate, channels, byteRate);
            while (in.read(data) != -1) {
                out.write(data);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void WriteWaveFileHeader(FileOutputStream out,
                                     long totalAudioLen,
                                     long totalDataLen,
                                     long longSampleRate,
                                     int channels,
                                     long byteRate) throws IOException {
        byte[] header = new byte[44];
        header[0] = 'R'; // RIFF/WAVE header
        header[1] = 'I';
        header[2] = 'F';
        header[3] = 'F';
        header[4] = (byte) (totalDataLen & 0xff);
        header[5] = (byte) ((totalDataLen >> 8) & 0xff);
        header[6] = (byte) ((totalDataLen >> 16) & 0xff);
        header[7] = (byte) ((totalDataLen >> 24) & 0xff);
        header[8] = 'W';
        header[9] = 'A';
        header[10] = 'V';
        header[11] = 'E';
        header[12] = 'f'; // 'fmt ' chunk
        header[13] = 'm';
        header[14] = 't';
        header[15] = ' ';
        header[16] = 16; // 4 bytes: size of 'fmt ' chunk
        header[17] = 0;
        header[18] = 0;
        header[19] = 0;
        header[20] = 1; // format = 1
        header[21] = 0;
        header[22] = (byte) channels;
        header[23] = 0;
        header[24] = (byte) (longSampleRate & 0xff);
        header[25] = (byte) ((longSampleRate >> 8) & 0xff);
        header[26] = (byte) ((longSampleRate >> 16) & 0xff);
        header[27] = (byte) ((longSampleRate >> 24) & 0xff);
        header[28] = (byte) (byteRate & 0xff);
        header[29] = (byte) ((byteRate >> 8) & 0xff);
        header[30] = (byte) ((byteRate >> 16) & 0xff);
        header[31] = (byte) ((byteRate >> 24) & 0xff);
        header[32] = (byte) (2 * 16 / 8); // block align
        header[33] = 0;
        header[34] = 16; // bits per sample
        header[35] = 0;
        header[36] = 'd';
        header[37] = 'a';
        header[38] = 't';
        header[39] = 'a';
        header[40] = (byte) (totalAudioLen & 0xff);
        header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
        header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
        header[43] = (byte) ((totalAudioLen >> 24) & 0xff);
        out.write(header, 0, 44);
    }
}
