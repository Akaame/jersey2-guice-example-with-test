package example.jersey;

import example.module1.Module1Binder;
import example.service.MyService;
import example.service.MyServiceImpl;
import jakarta.ws.rs.ext.Provider;
import org.glassfish.jersey.internal.inject.AbstractBinder;

@Provider
public class ApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(MyServiceImpl.class).to(MyService.class);
        install(new Module1Binder());
        install(new Module1Binder());
    }
}
