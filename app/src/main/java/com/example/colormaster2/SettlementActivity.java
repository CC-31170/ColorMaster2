package com.example.colormaster2;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettlementActivity extends AppCompatActivity {

    private TextView resultView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settlement);

        resultView = findViewById(R.id.ResultView);
        Intent intent = getIntent();
        if (intent != null) {
            int answerRed = intent.getIntExtra("ANSWER_RED", 0); // 默认值0
            int answerGreen = intent.getIntExtra("ANSWER_GREEN", 0); // 默认值0
            int answerBlue = intent.getIntExtra("ANSWER_BLUE",0);

            // 计算颜色相似度
            double similarity = calculateColorSimilarity(answerRed, answerGreen, answerBlue,27,169,228);

            // 输出或显示相似度结果
            resultView.setText("similarity = " + similarity);
            // 更新UI以展示相似度结果
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private double calculateColorSimilarity(int red1, int green1, int blue1, int red2, int green2, int blue2) {
        double MAX_RGB_DISTANCE = Math.sqrt(3 * 255 * 255);
        double distance = Math.sqrt( Math.pow(red2 - red1, 2) + Math.pow(green2 - green1, 2) + Math.pow(blue2 - blue1, 2));
        double normalizedDistance = distance / MAX_RGB_DISTANCE;
        double similarityPercentage = (1.0 - normalizedDistance) * 100;
        return similarityPercentage;

    }
}