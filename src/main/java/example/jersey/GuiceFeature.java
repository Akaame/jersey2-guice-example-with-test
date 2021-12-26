package example.jersey;

import example.guice.ApplicationGuiceServletContextListener;
import example.guice.Service;
import example.guice.ServiceImpl;
import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.InjectionManagerProvider;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

public class GuiceFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        ServiceLocator locator = InjectionManagerProvider.getInjectionManager(context)
                .getInstance(ServiceLocator.class);
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(locator);
        GuiceIntoHK2Bridge guiceBridge = locator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(ApplicationGuiceServletContextListener.injector);

        Service service = InjectionManagerProvider.getInjectionManager(context)
                .getInstance(ServiceImpl.class);

        Service guiceService = ApplicationGuiceServletContextListener.injector.getInstance(Service.class);

        return true;
    }
}