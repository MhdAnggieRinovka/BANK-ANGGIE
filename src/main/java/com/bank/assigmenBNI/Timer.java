package com.bank.assigmenBNI;

import org.springframework.stereotype.Component;

@Component
public class Timer {
    private long startTime;

    public void start() {
        this.startTime = System.nanoTime();
    }

    public String stop() {
        long endTime = System.nanoTime();
        double duration = (endTime - this.startTime) / 1_000_000.0;
        return String.format("%.3f ms", duration);
    }
}