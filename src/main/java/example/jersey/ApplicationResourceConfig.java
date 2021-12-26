package example.jersey;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class ApplicationResourceConfig extends ResourceConfig {
    public ApplicationResourceConfig() {
        packages("example.jersey");
        register(MyResource.class);
        register(new ApplicationBinder());
    }
}