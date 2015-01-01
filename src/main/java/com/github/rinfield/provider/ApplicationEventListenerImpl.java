package com.github.rinfield.provider;

import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;

import com.github.rinfield.app.log.AppLogger;
import com.github.rinfield.app.log.AppLogs;

@Provider
public class ApplicationEventListenerImpl implements ApplicationEventListener {

    private static final AppLogger log = AppLogger
        .of(ApplicationEventListenerImpl.class);

    @Override
    public void onEvent(final ApplicationEvent applicationEvent) {
        log.write(AppLogs.ON_APPLICATION_EVENT, applicationEvent.getType());
    }

    @Override
    public RequestEventListener onRequest(final RequestEvent requestEvent) {
        log.write(AppLogs.ON_REQUEST_EVENT, requestEvent.getType());
        return new RequestEventListenerImpl();
    }

    public static class RequestEventListenerImpl implements
        RequestEventListener {

        private static final AppLogger log = AppLogger
            .of(RequestEventListenerImpl.class);

        @Override
        public void onEvent(final RequestEvent requestEvent) {
            log.write(AppLogs.ON_REQUEST_EVENT, requestEvent.getType());
        }
    }
}
