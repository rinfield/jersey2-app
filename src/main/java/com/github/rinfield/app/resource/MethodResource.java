package com.github.rinfield.app.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("method")
public class MethodResource extends AbstractResource {

    @GET
    public Response get() {
        return Response.ok("GET").build();
    }

    @POST
    public Response post() {
        return Response.ok("POST").build();
    }

    @PUT
    public Response put() {
        return Response.ok("PUT").build();
    }

    @DELETE
    public Response delete() {
        return Response.ok("DELETE").build();
    }
}
