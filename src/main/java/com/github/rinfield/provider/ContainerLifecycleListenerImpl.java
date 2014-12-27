package com.github.rinfield.provider;

import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;

@Provider
public class ContainerLifecycleListenerImpl implements
    ContainerLifecycleListener {

    @Override
    public void onStartup(final Container container) {
        System.out.println("onStartup: " + this);
    }

    @Override
    public void onReload(final Container container) {
        System.out.println("onReload: " + this);
    }

    @Override
    public void onShutdown(final Container container) {
        System.out.println("onShutdown: " + this);
    }
}