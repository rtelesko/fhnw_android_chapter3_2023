package com.example.lotteryinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> someActivityResultLauncher;
    // GUI control
    private TextView tvResult;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get reference to the TextView
        tvResult = findViewById(R.id.tvResult);

        // New way to send and receive data: startActivityForResult() is deprecated!
        // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                            Intent data = result.getData();
                            String selection = data.getStringExtra("SELECTION");
                            if (selection.equals("Swiss Lottery"))
                                tvResult.setText("Mode: Choose 6 numbers and 1 lucky number per tip\n" +
                                        "Draw: Every Wednesday and Saturday");
                            else
                                tvResult.setText("Mode: Choose 5 numbers and 2 stars per tip\n" +
                                        "Draw: Every Tuesday and Friday");
                        }
                    }
                });
    }

    public void fetchInfo(View view) {
        // Create Intent
        Intent intent = new Intent(this, SecondActivity.class);
        someActivityResultLauncher.launch(intent);
    }
}
