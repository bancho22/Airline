/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sun.org.mozilla.javascript.internal.json.JsonParser;

/**
 *
 * @author Bancho
 */
@Path("flightreservation")
public class FlightReservationApi {

    public FlightReservationApi() {
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeReservation(String json){
            JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
            entity.Flight flight = flight.
            return null;
    }

}
