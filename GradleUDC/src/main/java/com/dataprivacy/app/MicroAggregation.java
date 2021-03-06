package com.dataprivacy.app;
import java.io.*;
import java.util.Arrays;

public class MicroAggregation extends Algorithms {
    int numAggs = 0;
    int[] setStructure;
    boolean firstAggBoolean = true;
    String[][] set;

    private void setData(String path) throws FileNotFoundException {
        File f = new File(path);
        FileReader fReader = new FileReader(f);
        int r = 0;
        int c = 0;
        while(true){
            char temp = 0;
            try {
                if (!fReader.ready()) break;
                temp = (char) fReader.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(temp != ';' && temp != '"'){
                set[r][c] += temp;
            }
            else if (temp == ';'){
                if(c == 13){
                    c = 0;
                    r++;
                }
                else{
                    c++;
                }
            }
        }
    }

    public void run(String path, int k) throws FileNotFoundException {
        setData(path);
        long microAggStartTime = System.nanoTime();
        anonymize(k, super.isSensitive);
        while(numAggs != 1){
            aggregate(k);
            for(int i = 0; i < numAggs; i += set.length/numAggs){
                if(i + set.length/numAggs > set.length){
                    if(!microKAnonymous(Arrays.copyOfRange(set, i, set.length), k)){
                        anonymize(k, super.isSensitive);
                    }
                }
                else {
                    if(!microKAnonymous(Arrays.copyOfRange(set, i, i + set.length/numAggs), k)){
                        anonymize(k, super.isSensitive);
                    }
                }
            }
        }
        long microAggEndTime = System.nanoTime();
        System.out.println(runTime(microAggStartTime,microAggEndTime));
        System.out.println(set);
    }

    public String[][] aggregate (int k) {
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
        boolean isMatching = true;
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
        public String[][] anonymize(int k, boolean[] isSensitive){
        int j = numAggs;
        String str = "";
        int[] identity = new int[set.length];
        boolean[] identityChecker = new boolean[j];
        if(numAggs == 0){
            j = set.length/16/k;
        }
        for(int i = 0; i < set.length; i+=j){
            for(int n = i; n < i+j; n++) {
                for (int m = 0; m < set[0].length; m++) {
                    if (isSensitive[m]) {
                        for(int s = 0; s < identityChecker.length; s++){
                            identityChecker[s] = false;
                        }
                        for(int p = n; p < i+j; p++){
                            if(identity[p] == 0){
                                identity[0] = 1;
                                if(p != 0){
                                    identity[p] = identity[p-1] + 1;
                                }
                                while(!identityChecker[identity[p]]){
                                    int r = 0;
                                    identity[p + r] = identity[p];
                                    if(set[p][m] != set[p+r][m]){
                                        int v = 0;
                                        while(set[p][m] != set[p+1][m]) {
                                            if (set[p][m].charAt(v) != set[p + 1][m].charAt(v)) {
                                                for(int w = 0; w < v; w++) {
                                                   str+= set[p][m].charAt(w);
                                                }
                                                for(int y = v; y < set[p][m].length(); y++){
                                                    str+='*';
                                                }
                                            }
                                            v++;
                                        }
                                        for(int t = p+1; t < p + k; t++){
                                               set[t][m] = set[p][m];
                                       }
                                    }
                                    else {
                                        r++;
                                        if (r == k) {
                                            identityChecker[identity[p]] = true;
                                            r = 0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return set;
    }
}
