package com.dataprivacy.app;

import java.time.Duration;

public interface Algorithms {
    public void setData();
    public void run();
    public boolean checkKAnonymous();
    public float checkDataloss();
    public Duration checkRuntime();
}