package com.github.rinfield.app.resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.server.wadl.WadlApplicationContext;

import com.sun.research.ws.wadl.Application;
import com.sun.research.ws.wadl.Method;
import com.sun.research.ws.wadl.Resource;

@Path("")
public class ApiListingResource extends AbstractResource {

    @Inject
    private WadlApplicationContext wadlContext;

    @GET
    public Response get() {
        final Application appWadl = wadlContext.getApplication(uriInfo, false)
            .getApplication();
        final Stream<EndPoint> endPoints = appWadl
            .getResources()
            .stream()
            .flatMap(
                rs -> rs.getResource().stream()
                    .flatMap(r -> EndPoint.from(rs.getBase(), r)));
        return Response.ok(endPoints.collect(Collectors.toList())).build();
    }

    public static class EndPoint {
        public final String url;
        public final List<String> methods;

        public static Stream<EndPoint> from(final String url, final Resource r) {
            final String joinedUrl = UriBuilder.fromUri(url).path(r.getPath())
                .toString();
            final Map<Class<?>, List<Object>> grouped = r.getMethodOrResource()
                .stream().collect(Collectors.groupingBy(x -> x.getClass()));

            final List<String> methods = grouped
                .getOrDefault(Method.class, new ArrayList<>()).stream()
                .map(x -> ((Method) x).getName()).collect(Collectors.toList());
            final Stream<EndPoint> currentEndPoint = Stream.of(new EndPoint(
                joinedUrl, methods));

            final Stream<EndPoint> nestedEndPoints = grouped
                .getOrDefault(Resource.class, new ArrayList<>()).stream()
                .flatMap(x -> from(joinedUrl, (Resource) x));
            return Stream.concat(currentEndPoint, nestedEndPoints);
        }

        public EndPoint(final String url, final List<String> methods) {
            this.url = url;
            this.methods = Collections
                .unmodifiableList(new ArrayList<>(methods));
        }
    }
}
