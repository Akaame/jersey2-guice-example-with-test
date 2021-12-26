package example.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class ApplicationGuiceServletContextListener extends GuiceServletContextListener {
    public static Injector injector;

    @Override
    protected Injector getInjector() {
        System.out.println("Getting injector");
        injector = Guice.createInjector(new Module());

        return injector;
    }

}
