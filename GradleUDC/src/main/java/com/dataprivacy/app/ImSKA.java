package com.dataprivacy.app;

import java.time.Duration;



public class ImSKA extends Algorithms {

    public void run(String[][] data, int k)
    {
        //processedData is a String[]

        //filter quasi-identifiers from input file

        //rearrange columns in ascending order of numbers of unique values in it

        //sort the data in ascending order of numbers of unique values in it
        //group above data by all attributes in QID_DATA
        //for each group in group_QID
            //eqClass = group_qid + count(group_qid)
        //mergeEquivalenceClass = empty array size of eq_class
        //for each row in eq class
            //merged equivalence class = merged equivalence class + eq class
            //if merged equivalence class
                //ncp_eq = ncp(mrged equivalence class)

    }

    private void pigTry(){

    }

    public boolean checkKAnonymous(int k)
    {
        return false;
    }

    public float checkDataLoss(String[][] data)
    {
        return 0;
    }

    public Duration checkRuntime()
    {
        return null;
    }
}
