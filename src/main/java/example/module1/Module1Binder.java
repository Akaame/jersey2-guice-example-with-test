package example.module1;

import example.service.MyService;
import example.service.MyServiceImpl;
import org.glassfish.jersey.internal.inject.AbstractBinder;

public class Module1Binder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(Module1.class);
    }
}