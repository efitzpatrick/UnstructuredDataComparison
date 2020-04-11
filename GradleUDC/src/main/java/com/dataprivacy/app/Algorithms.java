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
        int anonCheck = 0;
        for(int i = 0; i < processedData.length; i++){
            for(int m = 0; m < processedData[i].length; i++) {
                if (isSensitive[i]) {
                    for (int j = i; j < processedData.length; i++) {
                        if (processedData[i][m] ==processedData[j][m]){
                            anonCheck++;
                        }
                    }
                    if (anonCheck >= k) {
                        return false;
                    }
                    anonCheck = 0;
                }
            }
        }
        return true;
    }

    abstract float checkDataLoss();
}