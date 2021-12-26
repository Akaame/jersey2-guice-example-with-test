package example.jersey;

import com.google.inject.servlet.GuiceFilter;
import example.guice.ApplicationGuiceServletContextListener;
import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.EnumSet;

public class Main {

    public static void main(String[] args) {
        Server server = new Server(8081);

        ServletContextHandler bb = new ServletContextHandler(server, "/*", ServletContextHandler.NO_SESSIONS);

        bb.addEventListener(new ApplicationGuiceServletContextListener());
        bb.addFilter(GuiceFilter.class, "/*",
                EnumSet.of(DispatcherType.REQUEST,
                        DispatcherType.ASYNC));
        ServletHolder sh = new ServletHolder(ServletContainer.class);
        sh.setInitParameter(ServerProperties.PROVIDER_PACKAGES, "example.jersey");
        sh.setInitParameter(ServerProperties.PROVIDER_SCANNING_RECURSIVE, "true");
        sh.setInitParameter(ServerProperties.TRACING, "ALL");
        sh.setInitParameter("jakarta.ws.rs.Application", "example.jersey.MyApplication");
        sh.setInitParameter("jersey.config.server.tracing", "ALL");
        sh.setInitOrder(1);
        sh.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
        bb.addServlet(sh, "/*");

        System.out.println(">>> STARTING EMBEDDED JETTY SERVER");
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
