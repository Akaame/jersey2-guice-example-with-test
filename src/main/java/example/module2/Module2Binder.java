package example.module2;

import example.module1.Module1;
import org.glassfish.jersey.internal.inject.AbstractBinder;

public class Module2Binder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(Module2.class);
    }
}