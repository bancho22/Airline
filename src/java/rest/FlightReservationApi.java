/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.JsonElement;
import facade.FlightFacade;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 *
 * @author Bancho
 */
@Path("flightreservation")
public class FlightReservationApi {

    FlightFacade ff = new FlightFacade();

    public FlightReservationApi() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeReservation(String json) {
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        String id = obj.get("flightId").getAsString();
        entity.Flight flight = ff.getSingleFlight(id);

        return Response.status(Response.Status.OK).entity(flight.toString()).type(MediaType.APPLICATION_JSON).build();
    }

}
