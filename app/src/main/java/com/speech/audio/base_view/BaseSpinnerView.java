package com.speech.audio.base_view;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.speech.audio.R;

import java.util.List;

public class BaseSpinnerView extends ViewGroup implements AdapterView.OnItemSelectedListener {

    private Spinner mSpinner;

    private Context mContext;

    private ArrayAdapter<Integer> mAdapter;

    private ISpinnerListener mListener;

    public BaseSpinnerView(Context context) {
        super(context);
    }

    public BaseSpinnerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BaseSpinnerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = View.inflate(context, R.layout.layout_base_spinner, this);
        mSpinner = view.findViewById(R.id.spinner_case);
        mSpinner.setOnItemSelectedListener(this);
    }

    public void initData(List<Integer> data) {
        mAdapter = new ArrayAdapter<Integer>(mContext, android.R.layout.simple_spinner_dropdown_item, data);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setVisibility(View.VISIBLE);
    }

    public void setSpinnerListener(ISpinnerListener listener) {
        mListener = listener;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mListener.onItemSelected(mAdapter.getItem(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
