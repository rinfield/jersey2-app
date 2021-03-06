package com.github.rinfield.app.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;

import com.github.rinfield.app.log.AppLogger;
import com.github.rinfield.app.log.AppLogs;

@Service
@RequestScoped
public class RequestScopedService {

    private static final AppLogger log = AppLogger
        .of(RequestScopedService.class);

    @PostConstruct
    public void postConstruct() {
        log.write(AppLogs.POST_CONSTRUCT, this);
    }

    @PreDestroy
    public void preDestroy() {
        log.write(AppLogs.PRE_DESTROY, this);
    }
}
