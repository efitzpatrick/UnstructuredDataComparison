package com.dataprivacy.app;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UnstructuredDataComparison {
    public static void main(String[][] args) throws FileNotFoundException {

        Algorithms imSKA, ImSLD, MicroAggregation;

        imSKA = new ImSKA();
        try {
            imSKA.run("/user/root/input/twitteruser", 4);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MicroAggregation microAgg = new MicroAggregation();
        microAgg.run("", 4);
    }

}