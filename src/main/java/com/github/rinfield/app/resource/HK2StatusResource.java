package com.github.rinfield.app.resource;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.DescriptorType;
import org.glassfish.hk2.api.DescriptorVisibility;
import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.api.ServiceLocator;

@Path("/hk2")
@Produces("application/json")
public class HK2StatusResource {

    @Inject
    private ServiceLocator locator;

    @GET
    public Response get() {
        final List<?> result = locator.getAllServiceHandles(sh -> true)
            .stream().map(DescriptorResponse::from)
            .sorted((a, b) -> a.id.compareTo(b.id))
            .collect(Collectors.toList());
        return Response.ok(result).build();
    }

    public static class DescriptorResponse {
        public final Long id;
        public final Long locatorId;
        public final Set<String> contracts;
        public final String implementation;
        public final String scope;
        public final Integer rank;
        public final Set<String> qualifiers;
        public final DescriptorType descriptorType;
        public final DescriptorVisibility descriptorVisibility;
        public final Map<String, List<String>> metadata;
        public final String loader;
        public final String analysisName;
        public final Integer identityHashCode;
        public final Boolean proxyable;
        public final Boolean proxyForSameScope;
        public final Boolean reified;

        public static DescriptorResponse from(final ServiceHandle<?> handle) {
            return new DescriptorResponse(handle.getActiveDescriptor());
        }

        public DescriptorResponse(final ActiveDescriptor<?> d) {
            implementation = d.getImplementation();
            contracts = d.getAdvertisedContracts();
            scope = d.getScope();
            qualifiers = d.getQualifiers();
            descriptorType = d.getDescriptorType();
            descriptorVisibility = d.getDescriptorVisibility();
            metadata = d.getMetadata();
            rank = d.getRanking();
            loader = d.getLoader() == null ? null : d.getLoader().toString();
            proxyable = d.isProxiable();
            proxyForSameScope = d.isProxyForSameScope();
            analysisName = d.getClassAnalysisName();
            id = d.getServiceId();
            locatorId = d.getLocatorId();
            identityHashCode = d.hashCode();
            reified = d.isReified();
        }
    }
}
