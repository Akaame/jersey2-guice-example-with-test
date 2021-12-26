import java.net.URI;
import java.util.EnumSet;

import example.guice.ApplicationGuiceServletContextListener;
import example.jersey.Main;
import jakarta.servlet.DispatcherType;

import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

public class EmbeddedJetty {

    private Server server;

    public void start() throws Exception {
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
    
    public void stop() throws Exception{
        server.stop();
    }
    
    public URI getBaseUri(){
        return server.getURI();
    }
    
}