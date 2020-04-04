package com.dataprivacy.app;
import java.lang.reflect.Type;
import java.time.Duration;

public interface Algorithms {
    String[][] setData();
    void run();
    boolean checkKAnonymous(int k);
    float checkDataLoss();
    Duration checkRuntime();
}