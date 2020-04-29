package com.dataprivacy.app;

public class UnstructuredDataComparison {
    public static void main(String[][] args) {

        Algorithms imSKA, ImSLD, MicroAggregation;

        imSKA = new ImSKA();
        imSKA.setData("/user/root/input/twitteruser.txt");

        MicroAggregation microAgg = new MicroAggregation();
        microAgg.setData(args[0][0]); //<- This should be a path like up above is
        microAgg.run(4);
    }

}