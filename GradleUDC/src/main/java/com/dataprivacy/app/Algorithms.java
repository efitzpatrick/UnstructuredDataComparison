package com.dataprivacy.app;
import java.lang.reflect.Type;
import java.time.Duration;

public abstract class Algorithms {
    String[][] processedData;
    boolean[] isSensitive;
    String[][] setData(){
        return null;
    }
    abstract void run();

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

    abstract float checkDataLoss();
}