package com.github.rinfield.app.resource;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("secure")
@RolesAllowed("authenticated")
public class SecureResouce extends AbstractResource {

    @Inject
    private SecurityContext securityContext;

    @GET
    public Response get() {
        return Response.ok(securityContext).build();
    }

    @GET
    @Path("permit_all")
    @PermitAll
    public Response getPermitAll() {
        return Response.ok(securityContext).build();
    }
}
