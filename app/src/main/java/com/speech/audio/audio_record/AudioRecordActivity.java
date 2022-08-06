package com.speech.audio.audio_record;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.speech.audio.R;
import com.speech.audio.audio_common.AudioConfigure;
import com.speech.audio.base_utils.LogUtil;
import com.speech.audio.audio_common.AudioConstant;

import java.util.LinkedList;
import java.util.List;

public class AudioRecordActivity extends AppCompatActivity {
    private static final String TAG = "AudioRecordActivity";

    // 采样率
    private List<String> mSampleRateList = new LinkedList<>();
    private ArrayAdapter<String> mSampleRateAdapter = null;
    private String mDefaultSampleRate = "";
    // 音频源
    private List<String> mAudioSourceList = new LinkedList<>();
    private ArrayAdapter<String> mAudioSourceAdapter = null;
    private String mDefaultAudioSource = "";
    // 声道数
    private List<String> mChannelList = new LinkedList<>();
    private ArrayAdapter<String> mChannelAdapter = null;
    private String mDefaultChannel = "";
    // 采样位数
    private List<String> mFormatList = new LinkedList<>();
    private ArrayAdapter<String> mFormatAdapter = null;
    private String mDefaultFormat = "";
    // 缓冲区大小
    private String mDefaultBufferSize = "";
    private EditText mBufferSizeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);
        initData();
        initView();
    }

    private void initData() {
        // 初始化采样率数据
        int rateDefaultValue = AudioRecordManager.getInstance().getDefaultSampleRate();
        for (AudioConstant.SampleRate rate : AudioConstant.SampleRate.values()) {
            mSampleRateList.add(rate.name());
            if (rate.value() == rateDefaultValue) {
                mDefaultSampleRate = rate.name();
            }
        }
        mSampleRateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mSampleRateList);

        // 初始化音頻源数据
        int sourceDefaultValue = AudioRecordManager.getInstance().getDefaultAudioSource();
        for (AudioConstant.AudioSource source : AudioConstant.AudioSource.values()) {
            mAudioSourceList.add(source.name());
            if (source.value() == sourceDefaultValue) {
                mDefaultAudioSource = source.name();
            }
        }
        mAudioSourceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mAudioSourceList);

        // 初始化声道配置
        int channelDefault = AudioRecordManager.getInstance().getDefaultChannelSize();
        for (AudioConstant.ChannelIn channelIn : AudioConstant.ChannelIn.values()) {
            mChannelList.add(channelIn.name());
            if (channelIn.value() == channelDefault) {
                mDefaultChannel = channelIn.name();
            }
        }
        mChannelAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mChannelList);

        // 初始化采样位数
        int formatDefault = AudioRecordManager.getInstance().getDefaultAudioFormat();
        for (AudioConstant.AudioFormatEnum audioFormat : AudioConstant.AudioFormatEnum.values()) {
            mFormatList.add(audioFormat.name());
            if (audioFormat.value() == formatDefault) {
                mDefaultFormat = audioFormat.name();
            }
        }
        mFormatAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mFormatList);

        // 缓冲区大小
        mDefaultBufferSize = AudioRecordManager.getInstance().getDefaultBufferSize() + "";
    }

    private void initView() {
        Spinner sampleRateSpinner = findViewById(R.id.record_sample_rate_value);
        sampleRateSpinner.setOnItemSelectedListener(mSampleRateSpinnerListener);
        sampleRateSpinner.setAdapter(mSampleRateAdapter);
        sampleRateSpinner.setSelection(mSampleRateList.indexOf(mDefaultSampleRate));

        Spinner audioSourceSpinner = findViewById(R.id.record_audio_source_value);
        audioSourceSpinner.setOnItemSelectedListener(mAudioSourceSpinnerListener);
        audioSourceSpinner.setAdapter(mAudioSourceAdapter);
        audioSourceSpinner.setSelection(mAudioSourceList.indexOf(mDefaultAudioSource));

        Spinner channelSpinner = findViewById(R.id.record_channel_value);
        channelSpinner.setOnItemSelectedListener(mChannelSpinnerListener);
        channelSpinner.setAdapter(mChannelAdapter);
        channelSpinner.setSelection(mChannelList.indexOf(mDefaultChannel));

        Spinner formatSpinner = findViewById(R.id.record_format_value);
        formatSpinner.setOnItemSelectedListener(mFormatSpinnerListener);
        formatSpinner.setAdapter(mFormatAdapter);
        formatSpinner.setSelection(mFormatList.indexOf(mDefaultFormat));

        mBufferSizeEditText = findViewById(R.id.record_buffer_size_value);
        mBufferSizeEditText.setText(mDefaultBufferSize);
        mBufferSizeEditText.setSelection(mBufferSizeEditText.getText().length());

        ((TextView) findViewById(R.id.record_save_path_value)).setText(AudioConfigure.path());

        findViewById(R.id.record_start).setOnClickListener(mStartBtnListener);

        findViewById(R.id.record_stop).setOnClickListener(mStopBtnListener);
    }

    private AdapterView.OnItemSelectedListener mSampleRateSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String name = mSampleRateAdapter.getItem(position);
            LogUtil.i(TAG, "Sample rate spinner item selected = " + name);
            AudioConstant.SampleRate rate = AudioConstant.SampleRate.valueOf(name);
            AudioRecordManager.getInstance().setSampleRate(rate.value());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AdapterView.OnItemSelectedListener mAudioSourceSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String name = mAudioSourceAdapter.getItem(position);
            LogUtil.i(TAG, "Audio source spinner item selected = " + name);
            AudioConstant.AudioSource rate = AudioConstant.AudioSource.valueOf(name);
            AudioRecordManager.getInstance().setAudioSource(rate.value());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AdapterView.OnItemSelectedListener mChannelSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String name = mChannelAdapter.getItem(position);
            LogUtil.i(TAG, "Channel spinner item selected = " + name);
            AudioConstant.ChannelIn channelIn = AudioConstant.ChannelIn.valueOf(name);
            AudioRecordManager.getInstance().setChannelSize(channelIn.value());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AdapterView.OnItemSelectedListener mFormatSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String name = mFormatAdapter.getItem(position);
            LogUtil.i(TAG, "Format spinner item selected = " + name);
            AudioConstant.AudioFormatEnum audioFormat = AudioConstant.AudioFormatEnum.valueOf(name);
            AudioRecordManager.getInstance().setAudioFormat(audioFormat.value());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private View.OnClickListener mStartBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AudioRecordManager.getInstance().setBufferSize(Integer.parseInt(mBufferSizeEditText.getText().toString()));
            AudioRecordManager.getInstance().startRecord();
        }
    };

    private View.OnClickListener mStopBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AudioRecordManager.getInstance().stopRecord();
            AudioRecordManager.getInstance().release();
        }
    };
}
