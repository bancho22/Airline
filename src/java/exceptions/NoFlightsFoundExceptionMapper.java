/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Bancho
 */
@Provider
public class NoFlightsFoundExceptionMapper implements ExceptionMapper<NoFlightsFoundException>  {
    
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(NoFlightsFoundException e) {
        JsonObject eObj = new JsonObject();
        eObj.addProperty("httpError", 404);
        eObj.addProperty("errorCode", 1); // No Flights Found
        eObj.addProperty("message", "No Flights Found");
        return Response.status(404)
                .entity(gson.toJson(eObj))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
