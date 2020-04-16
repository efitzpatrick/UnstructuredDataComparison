package com.dataprivacy.app;
import java.lang.reflect.Type;
import java.time.Duration;

public abstract class Algorithms {
    String[][] processedData;
    boolean[] isSensitive;
    String[][] setData() {return null;}
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
                    } else {
                        uncensored++;
                    }
                    index++;
                }
                index = 0;
            }
        }
        return (float)(censored/(censored + uncensored));
    }
}