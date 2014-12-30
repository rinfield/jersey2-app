package com.github.rinfield.app.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;

import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Singleton
public class SingletonService {

    private static final Logger log = LoggerFactory
        .getLogger(SingletonService.class);

    @PostConstruct
    public void postConstruct() {
        log.info("@PostConstruct: " + this);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("@PreDestroy: " + this);
    }
}
