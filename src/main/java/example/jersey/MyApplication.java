package example.jersey;

import org.glassfish.jersey.server.ResourceConfig;

public class MyApplication extends ResourceConfig {
    public MyApplication() {
        System.out.println("Registering injectables...");
        packages("example.jersey");
        register(GuiceFeature.class);
        register(MyResource.class);
    }
}