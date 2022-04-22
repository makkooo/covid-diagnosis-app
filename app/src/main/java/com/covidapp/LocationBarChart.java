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

public class LocationBarChart {

    public BarData createChartData() {

        ArrayList<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(0, 130101));
        entries.add(new BarEntry(1, 158354));
        entries.add(new BarEntry(2, 353622));
        entries.add(new BarEntry(3, 643081));
        entries.add(new BarEntry(4, 42631));
        entries.add(new BarEntry(5, 64131));
        entries.add(new BarEntry(6, 185707));
        entries.add(new BarEntry(7, 186119));
        entries.add(new BarEntry(8, 63075));
        entries.add(new BarEntry(9, 63316));
        entries.add(new BarEntry(10, 102427));
        entries.add(new BarEntry(11, 133962));
        entries.add(new BarEntry(12, 71306));
        entries.add(new BarEntry(13, 60124));
        entries.add(new BarEntry(14, 1144862));
        entries.add(new BarEntry(15, 115718));
        entries.add(new BarEntry(16, 24929));

        BarDataSet locationBarDataSet = new BarDataSet(entries, "Number of Cases");

        locationBarDataSet.setColor(Color.parseColor("#b4e7ff"));
        locationBarDataSet.setDrawValues(false);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(locationBarDataSet);

        BarData data = new BarData(dataSets);

        return data;
    }

    public void initBarChart(BarChart barChart, BarData data, Context context) {

        String[] labels = {"RegionI", "RegionII", "RegionIII", "RegionIV-A", "RegionIV-B", "RegionV",
                "RegionVI", "RegionVII", "RegionVIII", "RegionIX", "RegionX", "RegionXI", "RegionXII",
                "RegionXIII", "NCR", "CAR", "BARMM"};

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
        //set x-axis maximum entry value
        xAxis.setAxisMaximum(17);
        //set number of labels
        xAxis.setLabelCount(17);
        //rotates the label vertically
        xAxis.setLabelRotationAngle(90f);
        //hiding the x-axis line, default true if not set
        xAxis.setDrawAxisLine(false);
        //hiding the vertical grid lines, default true if not set
        xAxis.setDrawGridLines(false);
        //set labels to x-axis
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

        barChart.setData(data);
        barChart.invalidate();
    }
}
