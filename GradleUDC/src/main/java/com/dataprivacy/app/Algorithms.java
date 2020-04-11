package com.dataprivacy.app;
import java.lang.reflect.Type;
import java.time.Duration;

public abstract class Algorithms {
    String[][] setData(){
        return null;
    }
    abstract void run();
    boolean checkKAnonymous(int k){
        return true;
    }
    abstract float checkDataLoss();
}