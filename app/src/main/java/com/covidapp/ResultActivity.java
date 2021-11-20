package com.covidapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.google.android.material.button.MaterialButton;
import com.ramijemli.percentagechartview.PercentageChartView;

import org.parceler.Parcels;

import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {

    User user = null;
    double result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result);

        TextView recTv, recSubTv;
        PercentageChartView resultPcv, ageSexPcv, locationPcv;
        BarChart ageSexChart, locationChart;
        MaterialButton diagAgainButton, homeButton;

        recTv = findViewById(R.id.res_act_rec_title_tv);
        recSubTv = findViewById(R.id.res_act_rec_subtitle_tv);

        resultPcv = findViewById(R.id.res_act_result_pcv);
        ageSexPcv = findViewById(R.id.res_act_agesex_pcv);
        locationPcv = findViewById(R.id.res_act_loc_pcv);

        ageSexChart = findViewById(R.id.res_act_agesex_bc);
        locationChart = findViewById(R.id.res_act_loc_bc);

        diagAgainButton = findViewById(R.id.res_act_try_again_btn);
        homeButton = findViewById(R.id.res_act_home_btn);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            user = Parcels.unwrap(bundle.getParcelable("user"));
            result = bundle.getDouble("result");
        }

        String ageSexKey = user.ageGroup + " " + user.gender;
        String locationKey = user.region;

        Data data = new Data();
        HashMap<String, Float> ageSexMap = data.getAgeSexMap();
        float ageSexValue = ageSexMap.get(ageSexKey);
        HashMap<String, Float> locationMap = data.getRegionMap();
        float locationValue = locationMap.get(locationKey);

        resultPcv.setProgress((float) result, true);
        resultPcv.setTextFormatter(progress -> String.format("%.2f", result));

        if(result<=50) {
            recTv.setText(R.string.low_prob_recom);
            recSubTv.setText(R.string.low_prob_recom_sub);
        } else {
            recTv.setText(R.string.high_prob_recom);
            recSubTv.setText(R.string.high_prob_recom_sub);
        }

        ageSexPcv.setProgress((float) ageSexValue, true);
        ageSexPcv.setTextFormatter(progress -> String.format("%.2f", ageSexValue));

        AgeSexBarChart ageSexBarChart = new AgeSexBarChart();
        BarData ageSexChartData = ageSexBarChart.createChartData();
        ageSexBarChart.initBarChart(ageSexChart, ageSexChartData, this);

        locationPcv.setProgress((float) locationValue, true);
        locationPcv.setTextFormatter(progress -> String.format("%.2f", locationValue));

        LocationBarChart locationBarChart = new LocationBarChart();
        BarData locationChartData = locationBarChart.createChartData();
        locationBarChart.initBarChart(locationChart, locationChartData, this);

        diagAgainButton.setOnClickListener(view -> {
            Intent demographics = new Intent(this, DemographicActivity.class);
            startActivity(demographics);
            finish();
        });

        homeButton.setOnClickListener(view -> {
            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);
            finish();
        });
    }
}
