package com.covidapp;

import android.content.Context;
import java.io.File;
import smile.Network;
import smile.learning.BayesianSearch;
import smile.learning.BkKnowledge;
import smile.learning.DataMatch;
import smile.learning.DataSet;
import smile.learning.EM;

public class BayesianNetwork {

    private Context context;

    public BayesianNetwork(Context context) {
        this.context = context;
    }

    public Network learnNewNetwork() {

        File dir = context.getFilesDir();

        // For model validation only
//        Network net = new Network();
//        String covidModel = dir + "/covid-model.xdsl";
//        net.readFile(covidModel);

        // For production
        DataSet ds = new DataSet();
        String dataset = dir + "/dataset.txt";
        ds.readFile(dataset);

        BkKnowledge bkKnowledge = new BkKnowledge();
        String bkKno = dir + "/network-knowledge.gkno";
        bkKnowledge.readFile(bkKno);

        BayesianSearch bayesianSearch = new BayesianSearch();
        bayesianSearch.setBkKnowledge(bkKnowledge);
        bayesianSearch.setMaxParents(8);
        bayesianSearch.setIterationCount(20);
        bayesianSearch.setPriorSampleSize(50);
        bayesianSearch.setRandSeed(3);
        bayesianSearch.setLinkProbability(0.1);
        bayesianSearch.setPriorLinkProbability(0.001);

        Network net = bayesianSearch.learn(ds);
        return net;
    }
}
