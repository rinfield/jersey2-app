package com.github.rinfield.app.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.wadl.WadlApplicationContext;
import org.glassfish.jersey.server.wadl.internal.ApplicationDescription;

import com.sun.research.ws.wadl.Application;
import com.sun.research.ws.wadl.Resources;

@Path("")
public class RootResource extends AbstractResource {

    @Inject
    private WadlApplicationContext wadlContext;

    @GET
    public Response get() {
        final ApplicationDescription appDescription = wadlContext
            .getApplication(uriInfo, false);
        final Application appWadl = appDescription.getApplication();
        final List<Resources> result = appWadl.getResources();
        return Response.ok(result).build();
    }
}
