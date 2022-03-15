package com.example.lotteryexplicitintent;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
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

        // Calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.back_icon);

        // Showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

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

        // Variant 1 - Back Button for returning back to Main Activity
        btReturnToMain = findViewById(R.id.btReturnToMain);
        btReturnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Back to main activity - variant 1", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    // Variant 2 - Back Button available on emulator for returning back to Main Activity
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Back to main activity - variant 2", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    // Variant 3 - Customized Back button in the action bar for returning back to Main Activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                Toast.makeText(getApplicationContext(), "Back to main activity - variant 3", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}