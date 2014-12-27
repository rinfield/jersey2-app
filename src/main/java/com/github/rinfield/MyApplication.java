package com.github.rinfield;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("")
public class MyApplication extends ResourceConfig {

    @Inject
    public MyApplication(final DynamicConfigurationService dynamicConf)
        throws Exception {
        packages(true, MyApplication.class.getPackage().toString());
        dynamicConf.getPopulator().populate(
            new ClasspathDescriptorFileFinderFix());
    }
}