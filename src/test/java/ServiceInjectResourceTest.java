import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.ws.rs.core.Response;

import org.glassfish.hk2.api.ServiceLocator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.rinfield.app.resource.ServiceInjectResource;
import com.github.rinfield.app.service.PerLookupService;
import com.github.rinfield.app.service.PerThreadService;
import com.github.rinfield.app.service.RequestScopedService;
import com.github.rinfield.app.service.SingletonService;

public class ServiceInjectResourceTest {

    @Mock
    ServiceLocator serviceLocatorMock;
    @Mock
    PerLookupService perLookupServiceMock;
    @Mock
    PerThreadService perThreadServiceMock;
    @Mock
    RequestScopedService requestScopedServiceMock;
    @Mock
    SingletonService singletonServiceMock;

    @InjectMocks
    ServiceInjectResource testee;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getShouldReturnServiceNames() throws Exception {
        when(serviceLocatorMock.getService(PerLookupService.class)).thenReturn(
            perLookupServiceMock);
        when(serviceLocatorMock.getService(PerThreadService.class)).thenReturn(
            perThreadServiceMock);
        when(serviceLocatorMock.getService(RequestScopedService.class))
            .thenReturn(requestScopedServiceMock);
        when(serviceLocatorMock.getService(SingletonService.class)).thenReturn(
            singletonServiceMock);

        final Response res = testee.get();
        final Object body = res.getEntity();
        assertThat(body, instanceOf(List.class));
        final List<?> list = (List<?>) body;
        assertThat(
            list,
            containsInAnyOrder("perLookupServiceMock", "perThreadServiceMock",
                "requestScopedServiceMock", "singletonServiceMock"));
        assertThat(list.size(), is(4));
    }
}
