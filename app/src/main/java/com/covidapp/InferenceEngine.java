package com.covidapp;

import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import smile.Network;

public class InferenceEngine {
    private Context context;

    public InferenceEngine(Context context) {
        this.context = context;
    }

    public double getProbability(ArrayList<String> selectedItems, ArrayList<String>  notSelectedItems,
                                 int sex, int age) {

        BayesianNetwork bayesianNetwork = new BayesianNetwork(this.context);
        Network net = bayesianNetwork.learnNewNetwork();

        net.setEvidence("SEX", sex);
        net.setEvidence("AGE_CAT", age);

        for(String selectedItem : selectedItems) {
            net.setEvidence(selectedItem, 1);
        }

        for(String notSelectedItem : notSelectedItems) {
            net.setEvidence(notSelectedItem, 0);
        }

        net.updateBeliefs();
        double[] beliefs = net.getNodeValue("COVID");
        return beliefs[0];
    }
}
