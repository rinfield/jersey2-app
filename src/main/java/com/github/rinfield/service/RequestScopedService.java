package com.github.rinfield.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;

@Service
@RequestScoped
public class RequestScopedService {

    @PostConstruct
    public void postConstruct() {
        System.out.println("@PostConstruct: " + this);
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy: " + this);
    }
}
