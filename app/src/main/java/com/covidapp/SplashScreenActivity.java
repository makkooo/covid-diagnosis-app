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
                "SMILE LICENSE 33761833 7c31efe3 638e02df " +
                        "THIS IS AN EVALUATION LICENSE THAT " +
                        "CANNOT BE USED FOR COMMERCIAL PURPOSES. " +
                        "Serial #: 8sbmo9g8jxkcdn342eqgo6t54 " +
                        "Valid until: 2022-09-02 " +
                        "Issued for: Mark (mrobrigado03@gmail.com) " +
                        "Organization: Polytechnic University of the Philippines " +
                        "Issued by BayesFusion activation server",
                new byte[] {
                        96,-110,-6,9,27,43,-92,75,-65,11,-101,-29,-32,-76,-53,-51,
                        -127,7,-1,-116,-119,54,-58,81,-3,-107,102,-71,-103,39,110,0,
                        120,-122,-9,77,-47,-53,-74,5,-48,-93,19,89,71,-106,127,-7,
                        -21,0,64,-27,57,-80,86,-29,37,14,-115,111,99,93,-100,8
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