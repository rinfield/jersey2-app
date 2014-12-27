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

import com.github.rinfield.app.service.PerLookupService;
import com.github.rinfield.app.service.PerThreadService;
import com.github.rinfield.app.service.RequestScopedService;
import com.github.rinfield.app.service.SingletonService;

@Path("/inject")
@Produces("application/json")
public class ServiceInjectResource {

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

        locator.getService(PerLookupService.class);
        locator.getService(PerThreadService.class);
        locator.getService(RequestScopedService.class);
        locator.getService(SingletonService.class);

        return Response.ok(res).build();
    }

    @PreDestroy
    public void separator() {
        System.out.println("=========================================");
    }
}
