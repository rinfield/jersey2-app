package com.github.rinfield;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.HttpMethodOverrideFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.github.rinfield.app.log.AppLogger;
import com.github.rinfield.app.log.AppLogs;

@ApplicationPath("")
public class MyApplication extends ResourceConfig {

    private static final AppLogger log = AppLogger.of(MyApplication.class);

    @Inject
    public MyApplication(final ServiceLocator locator,
        final DynamicConfigurationService dynamicConf) throws Exception {
        ServiceLocatorUtilities.enableImmediateScope(locator);

        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        log.write(AppLogs.APPLICATION_BEGIN);

        setApplicationName(MyApplication.class.getSimpleName());
        packages(true, MyApplication.class.getPackage().toString());

        // for debug
        register(new LoggingFilter());
        property(ServerProperties.MONITORING_STATISTICS_MBEANS_ENABLED, true);
        property(ServerProperties.TRACING, "ALL"); // "OFF"/"ON_DEMAND"/"ALL"

        // Support for HTTP method override via query parameter
        register(new HttpMethodOverrideFilter(
            HttpMethodOverrideFilter.Source.QUERY));
        // Support for javax.annotation.security annotations
        register(RolesAllowedDynamicFeature.class);

        dynamicConf.getPopulator().populate(
            new ClasspathDescriptorFileFinderFix());
    }
}