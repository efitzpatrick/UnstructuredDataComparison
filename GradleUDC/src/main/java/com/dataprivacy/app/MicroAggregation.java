package com.dataprivacy.app;
import java.util.Arrays;
import java.time.Duration;


public class MicroAggregation extends Algorithms {
    int numAggs = 0;
    int[] setStructure;
    boolean firstAggBoolean = true;
    public void run(String[][] set, int k)
    {
        //initial anonymization
        while(numAggs != 1){
            aggregate(set, k);
            for(int i = 0; i < numAggs; i += set.length/numAggs){
                if(i + set.length/numAggs > set.length){
                    if(!microKAnonymous(Arrays.copyOfRange(set, i, set.length), k)){
                        //if some section isn't k-anonymous, we need to re-anonymize
                    }
                }
                else {
                    if(!microKAnonymous(Arrays.copyOfRange(set, i, i + set.length/numAggs), k)){
                        //if final section isn't k-anonymous, we need to re-anonymize
                    }
                }
            }
        }
    }

    public String[][] aggregate (String[][] set, int k) {
        int timerCounter = 0;
        if(firstAggBoolean){
            setStructure = new int[set.length];
            for(int x = 0; x < setStructure.length; x++){
                setStructure[x] = x/(16*k);
                numAggs = x/16/k;
            }
        }
        for(int x = 0; x < setStructure.length; x++){
            setStructure[x] = x/8;
            numAggs /= 8;
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
                        if (!set[i][n].equals(set[j][n])) {
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
