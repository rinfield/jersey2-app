package com.github.rinfield.app.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.glassfish.hk2.api.ServiceLocator;

@Consumes(AbstractResource.ACCEPT_MIME_TYPE)
@Produces(AbstractResource.ACCEPT_MIME_TYPE)
public abstract class AbstractResource {
    public static final String ACCEPT_MIME_TYPE = MediaType.APPLICATION_JSON
        + "; " + MediaType.CHARSET_PARAMETER + "=utf-8";

    @Inject
    protected ServiceLocator locator;
    @Inject
    protected UriInfo uriInfo;
}
