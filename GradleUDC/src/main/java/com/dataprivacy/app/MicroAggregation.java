package com.dataprivacy.app;

import java.time.Duration;

public class MicroAggregation extends Algorithms {
    int[] setStructure;
    boolean firstAggBoolean = true;
    public void run()
    {

    }

    public String[][] aggregate (String[][] set, int k)
    {
        if(firstAggBoolean){
            setStructure = new int[set.length];
            for(int x = 0; x < setStructure.length; x++){
                setStructure[x] = x/4;
            }
        }
        for(int i = 0; i < set.length; i++) {

        }
        return null;
    }

    boolean microKAnon(int[] structure, String[][] data, int k){
        int anonCheck = 0;
        boolean newGroup = false;
        boolean restOfGroup = false;
        int i = 0;
        int j = 0;
        int y = 0;
        for(int m = 0; m < data.length; m++) {
            while (!newGroup) {
                for (int n = 0; n < data[i].length; n++) {
                    while (!restOfGroup) {
                        if (data[i][n] == data[j][n]) {
                            anonCheck++;
                        }
                        if (structure[j] != structure[i]) {
                            restOfGroup = true;
                        }
                    }
                    if(anonCheck < k){
                        return false;
                    }
                    anonCheck = 0;
                    restOfGroup = false;
                }
                i++;
                if(i != y){
                    y = i;
                    newGroup = true;
                }

                j = i;

            }
            newGroup = false;
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
