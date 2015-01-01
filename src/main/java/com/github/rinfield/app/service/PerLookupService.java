package com.github.rinfield.app.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

import com.github.rinfield.app.log.AppLogger;
import com.github.rinfield.app.log.AppLogs;

@Service
@PerLookup
public class PerLookupService {

    private static final AppLogger log = AppLogger.of(PerLookupService.class);

    @PostConstruct
    public void postConstruct() {
        log.write(AppLogs.POST_CONSTRUCT, this);
    }

    @PreDestroy
    public void preDestroy() {
        log.write(AppLogs.PRE_DESTROY, this);
    }
}
