package com.example.lotterycamera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> someActivityResultLauncher;
    // Define the picture id
    private static final int PIC_ID = 123;
    // GUI variables
    Button btCamera;
    ImageView ivPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btCamera = findViewById(R.id.btCamera);
        ivPhoto = findViewById(R.id.ivPhoto);

        // New way to send and receive data: startActivityForResult() is deprecated!
        // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                    /*
                    BitMap is a data structure of image file
                    which stores the image in memory
                    Disadvantage: low resolution quality (only thumbnail)
                    */
                            Intent data = result.getData();
                            if (data.getExtras() != null) {     // Check if Intent is empty
                                Bitmap photo = (Bitmap) data.getExtras().get("data");
                                // Set the image in ImageView for display
                                ivPhoto.setImageBitmap(photo);
                            }
                        }
                    }
                });
    }


    public void getPicture(View view) {
    /* Create Intent
    Create the camera_intent ACTION_IMAGE_CAPTURE
    it will open the built-in camera for capturing the image
    */
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (camera_intent.resolveActivity(getPackageManager()) == null)
            Log.d("ImplicitIntent", "Can't handle this!");
        else
            someActivityResultLauncher.launch(camera_intent);
    }
}