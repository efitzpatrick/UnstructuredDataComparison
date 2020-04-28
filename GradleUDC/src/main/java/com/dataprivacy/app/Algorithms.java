package com.dataprivacy.app;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.Properties;

import org.apache.pig.ExecType;
import org.apache.pig.PigServer;
import org.apache.hadoop.io.*;

public abstract class Algorithms {
    String[][] processedData;
    boolean[] isSensitive;



    String[][] setData() {
        try {
            PigServer pigServer = new PigServer(ExecType.MAPREDUCE);
            runQuery(pigServer);
            Properties props = new Properties();
            props.setProperty("fs.default.name", "hdfs://localhost:90000");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void runQuery(PigServer pigServer) {
        try {
            pigServer.registerQuery("data = " +
                            "LOAD 'hdfs://localhost:9000/pig_data/twitterusers.txt' USING PigStorage(';')" +
                    "as ( id:long, firstlogin:long,  freetext:chararray, gender:chararray, groundtruth:int, language:chararray, lastactivity:long, lasttweet:chararray, location:chararray, name:chararray," +
                    " profilepictureurl:chararray, screenname:chararray, timezone:chararray, foursqr_id:long");
            pigServer.registerQuery("filtered records = FILTER records BY temperature != 9999;");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    abstract void run(String[][] data, int k);

    boolean checkKAnonymous(int k){
        int[] tupleMarker = new int[processedData.length];
        boolean isMatching = false;
        int anonCheck = 0;
        for(int i = 0; i < processedData.length; i++){
            if(tupleMarker[i] != 1) {
                for (int j = 0; j < processedData.length; j++) {
                    int n = 0;
                    while (isMatching && n < processedData[i].length) {
                        if (processedData[i][n] != processedData[j][n]) {
                            isMatching = false;
                        }
                        n++;
                    }
                    if (isMatching) {
                        anonCheck++;
                        tupleMarker[i] = 1;
                        tupleMarker[j] = 1;
                    }
                    isMatching = true;
                }
                if(anonCheck < k){
                    return false;
                }
                anonCheck = 0;
            }

        }
        return true;
    }

    public float checkDataLoss(String[][] set)
    {
        int censored = 0;
        int uncensored = 0;
        int index = 0;
        for(int i = 0; i < set.length; i++){
            for(int j = 0; j < set[i].length; j++) {
                while (set[i][j].charAt(index) < set[i][j].length()) {
                    if (set[i][j].charAt(index) != '*') {
                        censored++;
                    }
                    else {
                        uncensored++;
                    }
                    index++;
                }
                index = 0;
            }
        }
        return (float)(censored/(censored + uncensored));
    }

    public long runTime(long start, long end)
    {
        return end - start;
    }

}