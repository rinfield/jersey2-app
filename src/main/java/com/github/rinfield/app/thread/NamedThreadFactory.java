package com.github.rinfield.app.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public static ThreadFactory poolName(final String poolName) {
        return new NamedThreadFactory(poolName);
    }

    private NamedThreadFactory(final String poolName) {
        final SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread()
            .getThreadGroup();
        namePrefix = poolName + "-";
    }

    @Override
    public Thread newThread(final Runnable r) {
        final Thread t = new Thread(group, r, namePrefix
            + threadNumber.getAndIncrement(), 0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}