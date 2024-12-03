package com.example.colormaster2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private static final long START_TIME_IN_MILLIS = 4000; // 5 seconds
    private Button submitBtn;
    int Answer_Red,Answer_Green,Answer_Blue,Answer_Color;
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
        submitBtn = findViewById(R.id.SubmitBtn);
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

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToSettlementActivity();
            }
        });


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
            Answer_Red = seekBarR.getProgress();
            Answer_Green = seekBarG.getProgress();
            Answer_Blue = seekBarB.getProgress();

            // 创建颜色
            Answer_Color = Color.rgb(Answer_Red, Answer_Green, Answer_Blue);
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
    //转移颜色数值到结算页面
    private void moveToSettlementActivity() {
        Intent intent = new Intent(Level1Activity.this, SettlementActivity.class);
        intent.putExtra("ANSWER_RED", Answer_Red);
        intent.putExtra("ANSWER_GREEN", Answer_Green);
        intent.putExtra("ANSWER_BLUE", Answer_Blue);

        startActivity(intent);
    }


}