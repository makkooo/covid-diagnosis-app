package com.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

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

        new smile.License(
                "SMILE LICENSE 39e75867 7569a67d 2a10428b " +
                        "THIS IS AN EVALUATION LICENSE THAT " +
                        "CANNOT BE USED FOR COMMERCIAL PURPOSES. " +
                        "Serial #: a7evmzalm3e8jdb9vzahexx8t " +
                        "Valid until: 2021-12-20 " +
                        "Issued for: Mark (mrobrigado03@gmail.com) " +
                        "Organization: Polytechnic University of the Philippines " +
                        "Issued by BayesFusion activation server",
                new byte[] {
                        69,96,-107,6,48,113,2,-112,-47,113,58,-18,-36,-104,23,-108,
                        67,-52,72,-23,99,-24,86,-121,62,75,-18,-98,38,-106,-73,59,
                        -49,-105,48,-115,12,3,67,-19,78,-121,-35,-92,89,50,71,-73,
                        -40,14,-97,68,104,-34,70,-18,-90,-93,-76,8,3,76,-101,-85
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