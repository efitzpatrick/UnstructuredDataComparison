package com.dataprivacy.app;
import java.util.Arrays;
import java.time.Duration;

public class MicroAggregation extends Algorithms {
    int[] setStructure;
    boolean firstAggBoolean = true;
    public void run()
    {

    }

    public String[][] aggregate (String[][] set, int k) {
        int timerCounter = 0;
        if(firstAggBoolean){
            setStructure = new int[set.length];
            for(int x = 0; x < setStructure.length; x++){
                setStructure[x] = x/(16*k);
            }
        }
        for(int x = 0; x < setStructure.length; x++){
            setStructure[x] = x/8;
        }
        for(int i = 0; i < set.length; i++) {
            timerCounter = setStructure[i] - setStructure[i-1];
            if(timerCounter != 0){
                while(timerCounter == 0){
                    timerCounter = setStructure[i] - setStructure[i-1];

                }
            }
        }
        return null;
    }

    boolean microKAnonymous(String[][] set, int k){
        int[] tupleMarker = new int[set.length];
        boolean isMatching = false;
        int anonCheck = 0;
        for(int i = 0; i < set.length; i++){
            if(tupleMarker[i] != 1) {
                for (int j = 0; j < set.length; j++) {
                    int n = 0;
                    while (isMatching && n < set[i].length) {
                        if (set[i][n] != set[j][n]) {
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

    public float checkDataLoss()
    {
        return 0;
    }

    public Duration checkRuntime()
    {
        return null;
    }

}
