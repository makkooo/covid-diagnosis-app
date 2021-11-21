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

//        Network net = new Network();
//
//        if(selectedItems.isEmpty()) {
//            return 0.02587925091;
//        } else {
//            try {
//                InputStream is = context.getAssets().open("covid-static-model.xdsl");
//                BufferedReader br = new BufferedReader(new InputStreamReader(is));
//                StringBuilder sb = new StringBuilder();
//                for (String line; (line = br.readLine()) != null; ) {
//                    sb.append(line).append('\n');
//                }
//                net.readString(sb.toString());
//            } catch (IOException e) {
//                Log.e("IOExceptionError", e.toString(), e.getCause());
//            }

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
