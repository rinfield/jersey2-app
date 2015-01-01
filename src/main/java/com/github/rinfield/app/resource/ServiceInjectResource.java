package com.github.rinfield.app.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.github.rinfield.app.log.AppLogger;
import com.github.rinfield.app.log.AppLogs;
import com.github.rinfield.app.service.PerLookupService;
import com.github.rinfield.app.service.PerThreadService;
import com.github.rinfield.app.service.RequestScopedService;
import com.github.rinfield.app.service.SingletonService;

@Path("inject")
public class ServiceInjectResource extends AbstractResource {

    private static final AppLogger log = AppLogger
        .of(ServiceInjectResource.class);

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

        log.write(AppLogs.SEVICE_INJECTED,
            locator.getService(PerLookupService.class));
        log.write(AppLogs.SEVICE_INJECTED,
            locator.getService(PerThreadService.class));
        log.write(AppLogs.SEVICE_INJECTED,
            locator.getService(RequestScopedService.class));
        log.write(AppLogs.SEVICE_INJECTED,
            locator.getService(SingletonService.class));

        return Response.ok(res).build();
    }

    @PreDestroy
    public void separator() {
        log.write(AppLogs.SEVICE_INJECTED,
            "=========================================");
    }
}
