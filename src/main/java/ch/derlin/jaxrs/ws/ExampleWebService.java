package ch.derlin.jaxrs.ws;


import ch.derlin.jaxrs.beans.InputExample;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * A simple webservice which uses complete bean validation.
 * <p/>
 * date: 09.05.2016
 *
 * @author Lucy Linder
 */
@Path("/")
public class ExampleWebService {

    @GET
    public String test() {
        return "It works !";
    }

    // note the @Valid annotation, which ensures the annotations
    // of the InputExample class are validated.
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public InputExample inputValidation(@Valid InputExample input) {
        System.out.println(input);
        return input;  // return it, to test the custom serializer
    }
}//end class
