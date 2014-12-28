package com.github.rinfield.app.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

@Service
@PerLookup
public class PerLookupService {
    @PostConstruct
    public void postConstruct() {
        System.out.println("@PostConstruct: " + this);
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy: " + this);
    }
}