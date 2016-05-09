package ch.derlin.jaxrs.ws;


import ch.derlin.jaxrs.beans.InputExample;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * @author: Lucy Linder
 * @date: 09.05.2016
 */
@Path("/api")
public class BBService {

    @GET
    public String test(){
        return "yeah";
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public boolean input(@Valid InputExample input) {
        System.out.println(input);
        return true;
    }
}//end class
