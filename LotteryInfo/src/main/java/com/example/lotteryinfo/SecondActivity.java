package com.example.lotteryinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SecondActivity extends AppCompatActivity {

    // GUI controls
    private RadioGroup rgLottery;
    private RadioButton rbLottery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // Get reference to the RadioGroup
        rgLottery = findViewById(R.id.rgLottery);
    }

    public void submit(View view) {
        Intent intent = new Intent();
        // Get selected radio button from RadioGroup
        int selectedId = rgLottery.getCheckedRadioButtonId();
        // Get reference to the RadioButton
        rbLottery = findViewById(selectedId);
        intent.putExtra("SELECTION", rbLottery.getText());
        // The setResult method takes an int result value and an Intent that is passed back to the calling Activity
        setResult(RESULT_OK, intent);
        finish();   // Finishing the activity
    }

}
