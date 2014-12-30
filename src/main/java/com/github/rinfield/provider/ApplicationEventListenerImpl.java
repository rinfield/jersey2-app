package com.github.rinfield.provider;

import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ApplicationEventListenerImpl implements ApplicationEventListener {

    private static final Logger log = LoggerFactory
        .getLogger(ApplicationEventListenerImpl.class);

    @Override
    public void onEvent(final ApplicationEvent applicationEvent) {
        log.info("onEvent: " + applicationEvent.getType());
    }

    @Override
    public RequestEventListener onRequest(final RequestEvent requestEvent) {
        log.info("onRequest: " + requestEvent.getType());
        return new RequestEventListenerImpl();
    }

    public static class RequestEventListenerImpl implements
        RequestEventListener {

        private static final Logger log = LoggerFactory
            .getLogger(RequestEventListenerImpl.class);

        @Override
        public void onEvent(final RequestEvent requestEvent) {
            log.info("onEvent: " + requestEvent.getType());
        }
    }
}
