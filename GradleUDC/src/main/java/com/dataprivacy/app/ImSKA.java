package com.dataprivacy.app;

import org.apache.pig.PigServer;

import java.io.IOException;
import java.time.Duration;



public class ImSKA extends HadoopAlgorithms {


    public void run(String path, int k) throws IOException {
        long sKAStartTime = System.nanoTime();
        //processedData is a String[]
        setData(path);
        //filter quasi-identifiers from input file
        pigServer.registerQuery("qid_data = FOREACH data GENERATE ");
        //rearrange columns in ascending order of numbers of unique values in it
        pigServer.registerQuery(";");
        //sort the data in ascending order of numbers of unique values in it

        //group above data by all attributes in QID_DATA
        //for each group in group_QID
            //eqClass = group_qid + count(group_qid)
        //mergeEquivalenceClass = empty array size of eq_class
        //for each row in eq class
            //merged equivalence class = merged equivalence class + eq class
            //if merged equivalence class
                //ncp_eq = ncp(mrged equivalence class)
        long sKAEndTime = System.nanoTime();
    }

    private void pigTry(){

    }
}
