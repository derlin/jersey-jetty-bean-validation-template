package ch.derlin.jaxrs;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

/**
 * this class configures the application.
 * <p/>
 * ! Important: note the two properties used to enable bean validation.
 * <p/>
 * Important: note the
 * date: 09.05.2016
 *
 * @author Lucy Linder
 */
public class ExampleApp extends ResourceConfig {

    public ExampleApp() {
        // list all the packages containing jersey services, separated by ";"
        packages("ch.derlin.jaxrs.ws"); // TODO: change package name
        // allows the validation errors to be sent to the client.
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        // avoid errors from the @ValidateOnExecution in subclasses
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
    }
}//end class
