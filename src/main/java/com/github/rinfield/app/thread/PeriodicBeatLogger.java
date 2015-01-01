package com.github.rinfield.app.thread;

import com.github.rinfield.app.log.AppLogger;
import com.github.rinfield.app.log.AppLogs;

public class PeriodicBeatLogger implements Runnable {
    private static final AppLogger log = AppLogger.of(PeriodicBeatLogger.class);

    public static final int INTERVAL = 20_000;

    private final String prefix;

    public PeriodicBeatLogger(final String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void run() {
        try {
            while (true) {
                log.write(AppLogs.HEART_BEAT, prefix + ": ping!");
                Thread.sleep(INTERVAL);
            }
        } catch (final InterruptedException ie) {
            log.write(AppLogs.HEART_BEAT_END, prefix
                + ": interrupted. terminate loop.");
        }
    }
}