package com.github.rinfield.provider;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import com.github.rinfield.app.log.AppLogger;
import com.github.rinfield.app.log.AppLogs;

@Provider
@PreMatching
public class AuthorizationRequestFilter implements ContainerRequestFilter {

    private static final AppLogger log = AppLogger
        .of(AuthorizationRequestFilter.class);

    @Override
    public void filter(final ContainerRequestContext requestContext)
        throws IOException {
        final Optional<String> idOption = Optional.ofNullable(
            requestContext.getUriInfo().getQueryParameters().get("user"))
            .flatMap(x -> x.stream().findFirst());
        final String id = idOption.orElse(null);
        log.write(AppLogs.REQUESTED_USER_ID, id);
        requestContext.setSecurityContext(new SimpleSecurityContext(id,
            requestContext.getSecurityContext()));
    }

    public static class SimpleSecurityContext implements SecurityContext {

        private final String id;
        private final SecurityContext securityContext;

        public SimpleSecurityContext(final String id,
            final SecurityContext securityContext) {
            this.id = id;
            this.securityContext = securityContext;
        }

        @Override
        public String getAuthenticationScheme() {
            return securityContext.getAuthenticationScheme();
        }

        @Override
        public Principal getUserPrincipal() {
            return () -> id;
        }

        @Override
        public boolean isSecure() {
            return securityContext.isSecure();
        }

        @Override
        public boolean isUserInRole(final String role) {
            return id != null;
        }
    }
}
