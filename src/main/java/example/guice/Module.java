package example.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(Service.class).to(ServiceImpl.class).in(Singleton.class);
    }
}
