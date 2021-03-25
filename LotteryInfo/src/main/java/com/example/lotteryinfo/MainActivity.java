package com.example.lotteryinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final int SECOND_ACTIVITY_REQUEST_CODE = 1;

    // GUI control
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get reference to the TextView
        tvResult = findViewById(R.id.tvResult);
    }

    public void fetchInfo(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        // To receive a result, call startActivityForResult() (instead of startActivity())
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);// Activity is started with requestCode 1
    }

    // Callback method to get the message from the other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check which request we are responding to and if the result is OK
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String selection = data.getStringExtra("SELECTION");
            if (selection.equals("Swiss Lottery"))
                tvResult.setText("Mode: Choose 6 numbers and 1 lucky number per tip\n" +
                        "Draw: Every Wednesday and Saturday");
            else
                tvResult.setText("Mode: Choose 5 numbers and 2 stars per tip\n" +
                        "Draw: Every Tuesday and Friday");
        }

    }
}