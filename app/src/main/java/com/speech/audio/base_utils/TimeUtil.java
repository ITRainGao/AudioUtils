package com.speech.audio.base_utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;

/**
 * @ProjectName: TestAudioRecord
 * @Package: com.speech.audio.base_utils
 * @ClassName: TimeUtil
 * @Description: 类作用描述
 * @Author: guyu.gao
 * @CreateDate: 2022/5/4 0:16
 * @UpdateUser: guyu.gao
 * @UpdateDate: 2022/5/4 0:16
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class TimeUtil {
    public static String currentTime() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return format.format(System.currentTimeMillis());
    }
}
