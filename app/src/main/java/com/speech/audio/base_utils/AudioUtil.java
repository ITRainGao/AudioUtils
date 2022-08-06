package com.speech.audio.base_utils;

public class AudioUtil {

    /**
     * 计算发音能量值
     *
     * @param shorts
     * @return
     */
    public static float getDbs(short[] shorts) {
        float dbs = 0.0F;
        for (short aShort : shorts) {
            dbs += (float) Math.abs(aShort);
        }

        dbs = (float) ((double) dbs / ((double) (32767.0F * (float) shorts.length) * 0.05D));
        return dbs * 10.0F;
    }

    /**
     * byte[] to short[]
     *
     * @param bytes
     * @return
     */
    public static short[] bytesToShorts(byte[] bytes) {
        int length = bytes.length / 2;
        int index = 0;
        short[] shorts = new short[length];
        byte[] temp = new byte[2];

        for(int i = 0; i < bytes.length; i += 2) {
            temp[0] = bytes[i];
            temp[1] = bytes[i + 1];
            short tempShort = byteToShort(temp);
            shorts[index] = tempShort;
            ++index;
        }

        return shorts;
    }

    public static short byteToShort(byte[] bytes) {
        short tempShort = (short)(bytes[0] & 255);
        short tempShort2 = (short)(bytes[1] & 255);
        tempShort2 = (short)(tempShort2 << 8);
        return (short) (tempShort | tempShort2);
    }
}
