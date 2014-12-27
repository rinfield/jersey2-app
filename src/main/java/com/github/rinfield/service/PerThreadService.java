package com.github.rinfield.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.glassfish.hk2.api.PerThread;
import org.jvnet.hk2.annotations.Service;

@Service
@PerThread
public class PerThreadService {
    @PostConstruct
    public void postConstruct() {
        System.out.println("@PostConstruct: " + this);
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy: " + this);
    }
}
