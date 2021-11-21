package com.covidapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.util.TypedValue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import smile.Network;
import smile.learning.BayesianSearch;
import smile.learning.BkKnowledge;
import smile.learning.DataSet;

public class BayesianNetwork {

    private Context context;

    public BayesianNetwork(Context context) {
        this.context = context;
    }

    public Network learnNewNetwork() {

        File dir = context.getFilesDir();

        DataSet ds = new DataSet();
        String dataset = dir + "/dataset.txt";
        ds.readFile(dataset);

        BkKnowledge bkKnowledge = new BkKnowledge();
        String bkKno = dir + "/network-knowledge.gkno";
        bkKnowledge.readFile(bkKno);

        BayesianSearch bayesianSearch = new BayesianSearch();
        bayesianSearch.setBkKnowledge(bkKnowledge);
        bayesianSearch.setMaxParents(7);
        bayesianSearch.setIterationCount(50);
        bayesianSearch.setPriorSampleSize(50);

        Network net = bayesianSearch.learn(ds);
        String outFile = dir + "/network.xdsl";
        net.writeFile(outFile);
        return net;
    }
}
