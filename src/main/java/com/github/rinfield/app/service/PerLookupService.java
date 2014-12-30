package com.github.rinfield.app.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@PerLookup
public class PerLookupService {

    private static final Logger log = LoggerFactory
        .getLogger(PerLookupService.class);

    @PostConstruct
    public void postConstruct() {
        log.info("@PostConstruct: " + this);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("@PreDestroy: " + this);
    }
}
