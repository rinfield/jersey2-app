package com.github.rinfield.app.resource;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/")
@Produces("application/json")
public class RootResource {

    @GET
    public Response get() {
        final Map<Object, Object> result = new LinkedHashMap<>();
        result.put("status", "ok");
        return Response.ok(result).build();
    }
}
