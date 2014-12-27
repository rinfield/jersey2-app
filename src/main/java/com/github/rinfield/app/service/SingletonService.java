package com.github.rinfield.app.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;

import org.jvnet.hk2.annotations.Service;

@Service
@Singleton
public class SingletonService {
    @PostConstruct
    public void postConstruct() {
        System.out.println("@PostConstruct: " + this);
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy: " + this);
    }
}
