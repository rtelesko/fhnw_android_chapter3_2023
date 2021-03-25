package com.example.lotteryexplicitintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    // GUI controls
    private TextView tvNamePlayer;
    private TextView tvCostsTips;
    private Button btReturnToMain;

    // Data to pass between the two activities
    String namePlayer, numberTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Get reference to the TextViews
        tvNamePlayer = findViewById(R.id.tvNamePlayer);
        tvCostsTips = findViewById(R.id.tvCostsTips);

        // Check if Intent is not empty and has data
        if (getIntent().hasExtra("NAME") && getIntent().hasExtra("NUMBER_TIPS")) {
            // Get data
            namePlayer = getIntent().getStringExtra("NAME");
            numberTips = getIntent().getStringExtra("NUMBER_TIPS");
        }

        // Calculation of tip costs
        int tips = Integer.parseInt(numberTips);
        double costTips = 2.5 * tips;

        // Set Data in TextViews
        tvNamePlayer.setText("Player name: " + namePlayer);
        tvCostsTips.setText("Costs in CHF: " + costTips);

        // Button for returning back to Main Activity
        btReturnToMain = findViewById(R.id.btReturnToMain);
        btReturnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                // Alternative onBackPressed();
            }
        });

    }
}
