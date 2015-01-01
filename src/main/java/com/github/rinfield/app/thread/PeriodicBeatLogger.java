package com.github.rinfield.app.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeriodicBeatLogger implements Runnable {
    private static final Logger log = LoggerFactory
        .getLogger(PeriodicBeatLogger.class);

    public static final int INTERVAL = 20_000;

    private final String prefix;

    public PeriodicBeatLogger(final String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void run() {
        try {
            while (true) {
                log.info(prefix + ": ping!");
                Thread.sleep(INTERVAL);
            }
        } catch (final InterruptedException ie) {
            log.info(prefix + ": interrupted. terminate loop.");
        }
    }
}