package com.github.rinfield;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.HttpMethodOverrideFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

@ApplicationPath("")
public class MyApplication extends ResourceConfig {

    private static final Logger log = LoggerFactory
        .getLogger(MyApplication.class);

    @Inject
    public MyApplication(final DynamicConfigurationService dynamicConf)
        throws Exception {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        log.info("configuring application");

        setApplicationName(MyApplication.class.getSimpleName());
        packages(true, MyApplication.class.getPackage().toString());

        // for debug
        register(new LoggingFilter());
        property(ServerProperties.MONITORING_STATISTICS_MBEANS_ENABLED, true);
        property(ServerProperties.TRACING, "ALL"); // "OFF"/"ON_DEMAND"/"ALL"

        // Support for HTTP method override via query parameter
        register(new HttpMethodOverrideFilter(
            HttpMethodOverrideFilter.Source.QUERY));
        dynamicConf.getPopulator().populate(
            new ClasspathDescriptorFileFinderFix());
    }
}