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

        File bkKno = new File(getFilesDir(), "network-knowledge.gkno");
        if(!bkKno.exists()) {
            try {
                assetUtils.copyAsset(this, getFilesDir(), "network-knowledge.gkno");
            } catch (IOException e) {
                Log.e("IOException", e.getCause().toString());
            }
        }

        // License issued by BayesFusion Licensing Server
        // This code must be executed before any other jSMILE object is created
        new smile.License(
                "SMILE LICENSE 70c62b52 3fdb9f94 13f931be " +
                        "THIS IS AN EVALUATION LICENSE THAT " +
                        "CANNOT BE USED FOR COMMERCIAL PURPOSES. " +
                        "Serial #: 8bzq85hktef0c49gwgj5y3hsp " +
                        "Valid until: 2022-03-15 " +
                        "Issued for: Mark (mrobrigado03@gmail.com) " +
                        "Organization: Polytechnic University of the Philippines " +
                        "Issued by BayesFusion activation server",
                new byte[] {
                        -71,67,62,-119,67,-107,-58,-15,70,121,112,-53,38,-49,-35,-35,
                        -72,73,-114,-104,115,103,118,16,72,-64,-12,-2,87,-24,37,-81,
                        65,-8,5,74,-110,34,-77,-115,-52,-34,109,-20,85,79,45,-121,
                        117,-91,1,85,2,-124,-74,1,-104,42,-86,79,-123,-35,110,8
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