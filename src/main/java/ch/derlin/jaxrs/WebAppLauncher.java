package ch.derlin.jaxrs;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @author: Lucy Linder
 * @date: 09.05.2016
 */
public class WebAppLauncher {

    public static void main(String[] args) throws Exception {

        Server server = new Server(8680);

        WebAppContext context = new WebAppContext();
        context.setParentLoaderPriority(true);
        context.setDescriptor(WebAppLauncher.class.getResource("/WEB-INF/web.xml").toString());
        // use the externalForm so that it works inside a jar
        context.setResourceBase(WebAppLauncher.class.getClassLoader().getResource("WEB-INF").toExternalForm());
        context.setContextPath("/");
        context.setParentLoaderPriority(true);

        server.setHandler(context);

        server.start();
        server.join();
    }
}