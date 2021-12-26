package example.jersey;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        ResourceConfig config = new ApplicationResourceConfig();
        final Server server =
                JettyHttpContainerFactory.createServer(
                        URI.create("http://localhost:8080/"), config);

        try {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    System.out.println("Shutting down the application...");
                    server.stop();
                    System.out.println("Done, exit.");
                } catch (Exception e) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                }
            }));

            System.out.println(
                    String.format("Application started.%nStop the application using CTRL+C"));

            // block and wait shut down signal, like CTRL+C
            Thread.currentThread().join();

            // alternative
            // Thread.sleep(Long.MAX_VALUE);       // sleep forever...
            // Thread.sleep(Integer.MAX_VALUE);    // sleep around 60+ years

        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
