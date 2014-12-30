package com.github.rinfield.app.resource;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.ws.rs.core.Response;

import org.glassfish.hk2.api.ServiceLocator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.rinfield.app.service.PerLookupService;
import com.github.rinfield.app.service.PerThreadService;
import com.github.rinfield.app.service.RequestScopedService;
import com.github.rinfield.app.service.SingletonService;

@RunWith(Enclosed.class)
public class ServiceInjectResourceTest {
    static ServiceInjectResourceTest t;

    @InjectMocks
    ServiceInjectResource testee;

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

    @BeforeClass
    public static void beforeClass() throws Exception {
        t = new ServiceInjectResourceTest();
    }

    public static class NormalScenario {

        @Before
        public void initMocks() {
            MockitoAnnotations.initMocks(t);
        }

        @Test
        public void getShouldReturnServiceNames() throws Exception {
            when(t.serviceLocatorMock.getService(PerLookupService.class))
                .thenReturn(t.perLookupServiceMock);
            when(t.serviceLocatorMock.getService(PerThreadService.class))
                .thenReturn(t.perThreadServiceMock);
            when(t.serviceLocatorMock.getService(RequestScopedService.class))
                .thenReturn(t.requestScopedServiceMock);
            when(t.serviceLocatorMock.getService(SingletonService.class))
                .thenReturn(t.singletonServiceMock);

            final Response res = t.testee.get();
            final Object body = res.getEntity();
            assertThat(body, instanceOf(List.class));
            final List<?> list = (List<?>) body;
            assertThat(
                list,
                containsInAnyOrder("perLookupServiceMock",
                    "perThreadServiceMock", "requestScopedServiceMock",
                    "singletonServiceMock"));
            assertThat(list.size(), is(4));
        }
    }

    public static class ExceptionalScenario {
        @Test
        public void testName() throws Exception {
            assertTrue(true); // something goes here
        }
    }
}
