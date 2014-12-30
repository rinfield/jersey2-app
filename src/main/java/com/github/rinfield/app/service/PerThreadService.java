package com.github.rinfield.app.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.glassfish.hk2.api.PerThread;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@PerThread
public class PerThreadService {

    private static final Logger log = LoggerFactory
        .getLogger(PerThreadService.class);

    @PostConstruct
    public void postConstruct() {
        log.info("@PostConstruct: " + this);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("@PreDestroy: " + this);
    }
}
