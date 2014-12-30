package com.github.rinfield;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.HttpMethodOverrideFilter;
import org.slf4j.bridge.SLF4JBridgeHandler;

@ApplicationPath("")
public class MyApplication extends ResourceConfig {

    @Inject
    public MyApplication(final DynamicConfigurationService dynamicConf)
        throws Exception {

        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        packages(true, MyApplication.class.getPackage().toString());
        register(new LoggingFilter());
        // Support for HTTP method override via query parameter
        register(new HttpMethodOverrideFilter(
            HttpMethodOverrideFilter.Source.QUERY));
        dynamicConf.getPopulator().populate(
            new ClasspathDescriptorFileFinderFix());
    }
}