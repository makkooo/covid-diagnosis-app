package com.covidapp;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import smile.Network;

public class InferenceEngine {
    private Context context;

    public InferenceEngine(Context context) {
        this.context = context;
    }

    public double getProbability(ArrayList<String> selectedItems, ArrayList<String>  notSelectedItems,
                                 String sex, String age) {

        Network net = new Network();

        if(selectedItems.isEmpty()) {
            return 0.02587925091;
        } else {
            try {
                InputStream is = context.getAssets().open("Covid-new.xdsl");
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                for (String line; (line = br.readLine()) != null; ) {
                    sb.append(line).append('\n');
                }
                net.readString(sb.toString());
            } catch (IOException e) {
                Log.e("IOExceptionError", e.toString(), e.getCause());
            }

            net.setEvidence("SEX", sex);
            net.setEvidence("AGE_CAT", age);

            for(String selectedItem : selectedItems) {
                net.setEvidence(selectedItem, "Yes");
            }

            for(String notSelectedItem : notSelectedItems) {
                net.setEvidence(notSelectedItem, "No");
            }

            net.updateBeliefs();
            double[] beliefs = net.getNodeValue("COVID");
            return beliefs[0];
        }
    }
}
