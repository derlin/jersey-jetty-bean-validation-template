package ch.derlin.jaxrs;


import ch.derlin.jaxrs.ws.BBService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public final class EmbeddedServer {

    private static final int SERVER_PORT = 8680;

    private EmbeddedServer() {
    }

    public static void main(String[] args) throws Exception {
        URI baseUri = UriBuilder.fromUri("http://localhost").port(SERVER_PORT).build();

        ResourceConfig config = new ResourceConfig(BBService.class)
                // Now you can expect validation errors to be sent to the client.
                .property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true)
                        // @ValidateOnExecution annotations on subclasses won't cause errors.
                .property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);


        Server server = JettyHttpContainerFactory.createServer(baseUri, config, false);
        ContextHandler contextHandler = new ContextHandler("/");
        contextHandler.setHandler(server.getHandler());

        server.start();
        server.join();
    }
}
