package com.github.rinfield.provider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.rinfield.app.thread.NamedThreadFactory;
import com.github.rinfield.app.thread.PeriodicBeatLogger;

@Provider
public class ContainerLifecycleListenerImpl implements
    ContainerLifecycleListener {

    private static final Logger log = LoggerFactory
        .getLogger(ContainerLifecycleListenerImpl.class);

    final ExecutorService executorService = Executors.newFixedThreadPool(2,
        NamedThreadFactory.poolName("periodicbeat-pool"));

    @Override
    public void onStartup(final Container container) {
        log.info("onStartup: " + this);
        executorService.execute(new PeriodicBeatLogger("1"));
        executorService.execute(new PeriodicBeatLogger("2"));
    }

    @Override
    public void onReload(final Container container) {
        log.info("onReload: " + this);
    }

    @Override
    public void onShutdown(final Container container) {
        log.info("onShutdown: " + this);
        executorService.shutdownNow();
    }
}