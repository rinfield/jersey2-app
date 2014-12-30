package com.github.rinfield.app.resource;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;

@Path("nested")
public class NestedResource extends AbstractResource {

    @Inject
    private Provider<SubResource> subResourceProvider;

    @GET
    public Response get() {
        return Response.ok("top-level").build();
    }

    // must not be http method annotated!
    @Path("sub")
    public SubResource subresource() {
        return subResourceProvider.get();
    }

    // with @Path annotation, it will be registered as top-level resource class.
    // basically subresource class should have @Service instead of @Path.
    @Service
    @RequestScoped
    public static class SubResource extends AbstractResource {
        @GET
        public Response get() {
            return Response.ok(uriInfo.getAbsolutePath().toString()).build();
        }

        @GET
        @Path("2")
        public Response get2() {
            return Response.ok(uriInfo.getAbsolutePath().toString()).build();
        }
    }
}
