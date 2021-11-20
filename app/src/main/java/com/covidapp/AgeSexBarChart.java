package com.covidapp;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

public class AgeSexBarChart {

    public BarData createChartData() {

        ArrayList<BarEntry> entries1 = new ArrayList<>();
        ArrayList<BarEntry> entries2 = new ArrayList<>();

        // Male Data
        entries1.add(new BarEntry(0, 302154));
        entries1.add(new BarEntry(1, 361382));
        entries1.add(new BarEntry(2, 257752));
        entries1.add(new BarEntry(3, 188033));
        entries1.add(new BarEntry(4, 139563));
        entries1.add(new BarEntry(4, 119425));

        // Female Data
        entries2.add(new BarEntry(0, 316600));
        entries2.add(new BarEntry(1, 352905));
        entries2.add(new BarEntry(2, 230000));
        entries2.add(new BarEntry(3, 181610));
        entries2.add(new BarEntry(4, 152989));
        entries2.add(new BarEntry(4, 145920));

        BarDataSet maleBarDataSet = new BarDataSet(entries1, "Male");
        BarDataSet femaleBarDataSet = new BarDataSet(entries2, "Female");

        maleBarDataSet.setColor(Color.parseColor("#b4e7ff"));
        maleBarDataSet.setDrawValues(false);
        femaleBarDataSet.setColor(Color.parseColor("#ffe2f5"));
        femaleBarDataSet.setDrawValues(false);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(maleBarDataSet);
        dataSets.add(femaleBarDataSet);

        BarData data = new BarData(dataSets);

        return data;
    }

    public void initBarChart(BarChart barChart, BarData data, Context context) {

        String[] labels = {"< 24", "25 to 34", "35 to 44", "45 to 54", "55 to 64", "65 >"};

        IMarker marker = new ChartMarkerView(context, R.layout.view_tv_content);
        barChart.setMarker(marker);

        barChart.setExtraOffsets(20,20,20,20);
        //hiding the grey background of the chart, default false if not set
        barChart.setDrawGridBackground(false);
        //remove the bar shadow, default false if not set
        barChart.setDrawBarShadow(false);
        //remove border of the chart, default false if not set
        barChart.setDrawBorders(false);
        //remove the description label text located at the lower right corner
        barChart.getDescription().setEnabled(false);
        //setting animation for y-axis, the bar will pop up from 0 to its value within the time we set
        barChart.animateY(1500);
        //setting animation for x-axis, the bar will pop up separately within the time we set
        barChart.animateX(1500);


        XAxis xAxis = barChart.getXAxis();
        //change the position of x-axis to the bottom
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //set the horizontal distance of the grid line
        xAxis.setGranularity(1);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(6);
        xAxis.setCenterAxisLabels(true);
        //hiding the x-axis line, default true if not set
        xAxis.setDrawAxisLine(false);
        //hiding the vertical grid lines, default true if not set
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        YAxis leftAxis = barChart.getAxisLeft();
        //hiding the left y-axis line, default true if not set
        leftAxis.setDrawAxisLine(false);
        leftAxis.setAxisMinimum(0);

        //hiding the right y-axis line, default true if not set
        barChart.getAxisRight().setEnabled(false);

        Legend legend = barChart.getLegend();
        //setting the text size of the legend
        legend.setTextSize(11f);
        //setting the alignment of legend toward the chart
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        //setting the stacking direction of legend
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);

        float groupSpace = 0.1f;
        float barSpace = 0.05f; // x2 dataset
        float barWidth = 0.40f; // x2 dataset
        // (0.46 + 0.02) * 2 + 0.04 = 1.00 -> interval per "group"

        barChart.setData(data);
        barChart.getBarData().setBarWidth(barWidth);
        barChart.groupBars(0, groupSpace, barSpace);
        barChart.invalidate();
    }
}
