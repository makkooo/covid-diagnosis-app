package com.covidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        MaterialButton startButton, learnButton;

        startButton = findViewById(R.id.main_act_start_btn);
        learnButton = findViewById(R.id.main_act_learn_btn);

        startButton.setOnClickListener(view -> {
            Intent demographics = new Intent(this, DemographicActivity.class);
            startActivity(demographics);
        });

        learnButton.setOnClickListener(view -> {
            Intent learn = new Intent(this, LearnMoreActivity.class);
            startActivity(learn);
        });
    }


}