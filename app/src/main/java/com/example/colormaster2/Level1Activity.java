package com.example.colormaster2;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.widget.EditText;
import android.text.TextWatcher;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Level1Activity extends AppCompatActivity {
    private SeekBar seekBarR,seekBarG,seekBarB;//三个滑块
    private ImageView answerView,questionView;//提交答案的图片
    private TextView text1,text2,text3,count_text;//显示RGB当前数值
    private static final long START_TIME_IN_MILLIS = 6000; // 5 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_level1);

        seekBarR = findViewById(R.id.seekBar1);
        seekBarG = findViewById(R.id.seekBar2);
        seekBarB = findViewById(R.id.seekBar3);
        answerView = findViewById(R.id.AnswerImage);
        questionView = findViewById(R.id.QuestionImage);
        text1 = findViewById(R.id.editText1);
        text2 = findViewById(R.id.editText2);
        text3 = findViewById(R.id.editText3);
        count_text = findViewById(R.id.CountView);
        seekBarR.setOnSeekBarChangeListener(seekBarChangeListener);
        seekBarG.setOnSeekBarChangeListener(seekBarChangeListener);
        seekBarB.setOnSeekBarChangeListener(seekBarChangeListener);
        //设置滑块和进度条分别为对应颜色
        seekBarR.getThumb().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);//滑块
        seekBarR.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);//进度条
        seekBarG.getThumb().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);//滑块
        seekBarG.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);//进度条
        seekBarB.getThumb().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);//滑块
        seekBarB.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);//进度条
        startTimer();

    }

        private final SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 获取当前的RGB值
                if (seekBar == seekBarR) {
                    text1.setText(String.valueOf(progress));
                } else if (seekBar == seekBarG) {
                    text2.setText(String.valueOf(progress));
                } else if (seekBar == seekBarB) {
                    text3.setText(String.valueOf(progress));
                }
                int red = seekBarR.getProgress();
                int green = seekBarG.getProgress();
                int blue = seekBarB.getProgress();

                // 创建颜色
                int Answer_Color = Color.rgb(red, green, blue);
                // 设置AnswerBoard的颜色
                answerView.setBackgroundColor(Answer_Color);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        };

    private void startTimer() {
        CountDownTimer countDownTimer = new CountDownTimer(START_TIME_IN_MILLIS, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int secondsLeft = (int) millisUntilFinished / 1000;
                count_text.setText("Countdown:"+ String.valueOf(secondsLeft));
            }

            @Override
            public void onFinish() {
                questionView.setVisibility(View.GONE); // Hide the image
                count_text.setText("Done!"); // Optional: change text when finished
            }
        }.start();
    }


}