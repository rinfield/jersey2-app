package com.github.rinfield.app.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.hk2.api.ServiceLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.rinfield.app.service.PerLookupService;
import com.github.rinfield.app.service.PerThreadService;
import com.github.rinfield.app.service.RequestScopedService;
import com.github.rinfield.app.service.SingletonService;

@Path("/inject")
@Produces("application/json")
public class ServiceInjectResource {

    private static final Logger log = LoggerFactory
        .getLogger(ServiceInjectResource.class);

    @Inject
    private ServiceLocator locator;

    @Inject
    private PerLookupService perLookupService;
    @Inject
    private PerThreadService perThreadService;
    @Inject
    private RequestScopedService requestScopedService;
    @Inject
    private SingletonService singletonService;

    @GET
    public Response get() {
        final List<String> res = Arrays
            .asList(perLookupService, perThreadService, requestScopedService,
                singletonService).stream().map(Objects::toString)
            .collect(Collectors.toList());

        log.info(locator.getService(PerLookupService.class).toString());
        log.info(locator.getService(PerThreadService.class).toString());
        log.info(locator.getService(RequestScopedService.class).toString());
        log.info(locator.getService(SingletonService.class).toString());

        return Response.ok(res).build();
    }

    @PreDestroy
    public void separator() {
        log.info("=========================================");
    }
}
