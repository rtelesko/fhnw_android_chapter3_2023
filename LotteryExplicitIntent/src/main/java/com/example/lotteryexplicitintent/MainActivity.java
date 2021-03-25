package com.example.lotteryexplicitintent;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // GUI controls
    private EditText etNamePlayer;
    private Spinner spNumberTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get reference to the EditView
        etNamePlayer = findViewById(R.id.etNamePlayer);
        // Get reference to the Spinner
        spNumberTips = findViewById(R.id.spNumberTips);
    }

    // EditText should be empty when returning from ResultActivity
    @Override
    public void onResume() {
        super.onResume();
        etNamePlayer.getText().clear();
    }

    public void sendData(View view) {
        // Define Intent
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);

        /* Alternative Bundle notation
        https://stackoverflow.com/questions/34607727/how-to-add-pass-multiple-values-to-intent-object
        https://zocada.com/using-intents-extras-pass-data-activities-android-beginners-guide/
        */

        // Pass Data
        intent.putExtra("NAME", etNamePlayer.getText().toString());
        intent.putExtra("NUMBER_TIPS", spNumberTips.getSelectedItem().toString());

        // Check for empty name
        if (TextUtils.isEmpty(etNamePlayer.getText().toString()))
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
        else {
            // Normally, when we launch a new activity, the previous activities will be kept in a queue like a stack of activities.
            // So if you want to kill all the previous activities, use FLAG_ACTIVITY_CLEAR_TASK and FLAG_ACTIVITY_NEW_TASK flag
            // on the Intent to clear all the activity stack.
            // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}