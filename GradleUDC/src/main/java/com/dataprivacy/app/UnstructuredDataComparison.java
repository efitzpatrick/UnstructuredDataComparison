package com.dataprivacy.app;

public class UnstructuredDataComparison {
    public static void main(String[][] args) {

        Algorithms imSKA, ImSLD, MicroAggregation;

        imSKA = new ImSKA();
        imSKA.setData();

        MicroAggregation microAgg = new MicroAggregation();
        microAgg.run(args, 4);
    }

}