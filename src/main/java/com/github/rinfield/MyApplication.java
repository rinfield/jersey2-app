package com.github.rinfield;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        packages(true, "com.github.rinfield");
    }
}