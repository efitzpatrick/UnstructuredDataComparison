package com.dataprivacy.app;

import java.time.Duration;

public class ImSKA extends Algorithms {

    public void run()
    {
        //filter quasi-identifiers from input file
        //rearrange columns in ascending order of numbers of unique values in it
        //sort the data in ascending order of numbers of unique values in it
        //group above data by all attributes in QID_DATA
    }

    public boolean checkKAnonymous(int k)
    {
        return false;
    }

    public float checkDataLoss()
    {
        return 0;
    }

    public Duration checkRuntime()
    {
        return null;
    }
}
