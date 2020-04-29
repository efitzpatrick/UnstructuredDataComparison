package com.dataprivacy.app;

import java.io.FileNotFoundException;

public class UnstructuredDataComparison {
    public static void main(String[][] args) throws FileNotFoundException {

        Algorithms imSKA, ImSLD, MicroAggregation;

        imSKA = new ImSKA();
        imSKA.setData("/user/root/input/twitteruser.txt");

        MicroAggregation microAgg = new MicroAggregation();
        microAgg.run("", 4);
    }

}