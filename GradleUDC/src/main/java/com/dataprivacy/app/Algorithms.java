package com.dataprivacy.app;

import java.time.Duration;

public interface Algorithms {
    void setData();
    void run();
    boolean checkKAnonymous();
    float checkDataloss();
    Duration checkRuntime();
}