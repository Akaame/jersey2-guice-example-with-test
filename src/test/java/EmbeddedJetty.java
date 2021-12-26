import java.net.URI;
import org.eclipse.jetty.server.Server;

public class EmbeddedJetty {

    private Server server;

    public void start() throws Exception {
    }
    
    public void stop() throws Exception{
        server.stop();
    }
    
    public URI getBaseUri(){
        return server.getURI();
    }
    
}