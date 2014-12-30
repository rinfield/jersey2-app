package com.github.rinfield.provider;

import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ContainerLifecycleListenerImpl implements
    ContainerLifecycleListener {

    private static final Logger log = LoggerFactory
        .getLogger(ContainerLifecycleListenerImpl.class);

    @Override
    public void onStartup(final Container container) {
        log.info("onStartup: " + this);
    }

    @Override
    public void onReload(final Container container) {
        log.info("onReload: " + this);
    }

    @Override
    public void onShutdown(final Container container) {
        log.info("onShutdown: " + this);
    }
}