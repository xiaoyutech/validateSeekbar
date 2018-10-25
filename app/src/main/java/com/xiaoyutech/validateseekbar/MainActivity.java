package com.xiaoyutech.validateseekbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.xiaoyutech.validateseekbar.R;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private TextView tv;
    private SeekBar seekBar;
    private ImageView ivSeekDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        seekBar = (SeekBar) findViewById(R.id.sb);
        seekBar.setOnSeekBarChangeListener(this);
        ivSeekDone = (ImageView) findViewById(R.id.iv_seek_done);
        //这里是个大坑，SeekBar在不同安卓版本OR机型上有着不同表现，默认padding不同
        //在代码中手动设置padding，避免发生无法"matchparent"的情况
        seekBar.setPadding(0, 0, 0, 0);
    }

    /**
     * seekBar进度变化时回调
     *
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getProgress() == seekBar.getMax()) {
            tv.setVisibility(View.VISIBLE);
            tv.setTextColor(Color.WHITE);
            tv.setText("完成验证");
            ivSeekDone.setVisibility(View.VISIBLE);
            seekBar.setThumb(null);
//            seekBar.setEnabled(false);
        } else {
            tv.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * seekBar开始触摸时回调
     *
     * @param seekBar
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    /**
     * seekBar停止触摸时回调
     *
     * @param seekBar
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getProgress() != seekBar.getMax()) {
            seekBar.setProgress(0);
            tv.setVisibility(View.VISIBLE);
            tv.setTextColor(Color.GRAY);
            tv.setText("请按住滑块，拖动到最右边");
        }
    }
}
