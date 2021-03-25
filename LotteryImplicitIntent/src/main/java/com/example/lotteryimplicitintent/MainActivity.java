package com.example.lotteryimplicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // See for changing the App icon: https://www.youtube.com/watch?v=ts98gL1JCQU

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Intents and Intent Filters: https://developer.android.com/guide/components/intents-filters
    public void invokeWebSite(View view) {
        // Get the URL text
        String url = "http://www.swisslos.ch";

        // Parse the URI and create the intent
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        // Verify that the intent will resolve to an activity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntent", "Can't handle this!");
        }
    }

    public void sendMail(View view) {
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:")); // Only email apps should handle this
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"rainer.telesko@fhnw.ch"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Lottery Jackpot");
        email.putExtra(Intent.EXTRA_TEXT, "Hey guys, the jackpot has just been broken. Rainer");

        // User should choose an email client he likes
        // For features of Intent.createChooser see https://stackoverflow.com/questions/3804233/what-is-the-purpose-of-using-intent-createchooser-in-startactivity-while-sen
        Intent chooser = Intent.createChooser(email, getResources().getString(R.string.chooserTitleMail));

        // Verify that the intent will resolve to an activity
        if (email.resolveActivity(getPackageManager()) != null)
            startActivity(chooser);
        else
            Log.d("ImplicitIntent", "Can't handle this!");

    }

}
