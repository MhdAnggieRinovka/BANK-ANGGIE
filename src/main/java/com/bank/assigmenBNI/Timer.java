package com.bank.assigmenBNI;

import org.springframework.stereotype.Component;

@Component
public class Timer {
    private long startTime;

    // Method untuk mulai hitung
    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    // Method untuk stop dan dapatkan selisih waktunya dalam format string
    public String stop() {
        long duration = System.currentTimeMillis() - this.startTime;
        return duration + " ms";
    }
}