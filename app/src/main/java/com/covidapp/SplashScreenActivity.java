package com.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 4000;

    //Variables
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView appName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        AssetUtils assetUtils = new AssetUtils();

        File dataset = new File(getFilesDir(), "dataset.txt");
        if(!dataset.exists()) {
            try {
                assetUtils.copyAsset(this, getFilesDir(), "dataset.txt");
            } catch (IOException e) {
                Log.e("IOException", e.getCause().toString());
            }
        }

        File covidModel= new File(getFilesDir(), "covid-model.xdsl");
        if(!covidModel.exists()) {
            try {
                assetUtils.copyAsset(this, getFilesDir(), "covid-model.xdsl");
            } catch (IOException e) {
                Log.e("IOException", e.getCause().toString());
            }
        }

        // License issued by BayesFusion Licensing Server
        // This code must be executed before any other jSMILE object is created
        new smile.License(
            "SMILE LICENSE fcb0e5ba b046dd1f 5172ff56 " +
                    "THIS IS AN EVALUATION LICENSE THAT " +
                    "CANNOT BE USED FOR COMMERCIAL PURPOSES. " +
                    "Serial #: 63fvnmcxechm12fgd7vzu61l " +
                    "Valid until: 2022-02-04 " +
                    "Issued for: Mark (mrobrigado03@gmail.com) " +
                    "Organization: Polytechnic University of the Philippines " +
                    "Issued by BayesFusion activation server",
            new byte[] {
                    42,114,7,-84,-63,3,-87,-100,-118,75,-14,-99,6,57,14,109,
                    15,61,-114,-92,-80,55,104,52,113,65,-15,-9,-102,-31,48,94,
                    51,-29,83,-122,27,44,5,-9,-65,47,1,-45,-15,9,-84,21,
                    46,-57,0,-37,21,-84,118,-108,101,-93,63,-108,-120,101,-59,-55
            }
        );

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.imageView);
        appName = findViewById(R.id.textView);

        image.setAnimation(topAnim);
        appName.setAnimation(bottomAnim);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        },SPLASH_SCREEN);
    }
}