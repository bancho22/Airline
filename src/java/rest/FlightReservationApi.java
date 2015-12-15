/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
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
import exceptions.InvalidInputException;
import exceptions.NoFlightsFoundException;

/**
 *
 * @author Bancho
 */
@Path("flightreservation")
public class FlightReservationApi {

    FlightFacade ff;
    Gson gson;

    public FlightReservationApi() {
        ff = new FlightFacade();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeReservation(String json) throws InvalidInputException, NoFlightsFoundException {
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        if (obj.get("flightID") == null || obj.get("flightID") == null || obj.get("Passengers") == null || obj.get("numberOfSeats") == null) {
            throw new InvalidInputException();
        }
        String id = obj.get("flightID").getAsString();
        String reserveeName = obj.get("ReserveeName").getAsString();
        JsonArray passangers = obj.get("Passengers").getAsJsonArray();
        int numberOfSeats = obj.get("numberOfSeats").getAsInt();
        entity.Flight flight = ff.getSingleFlight(id);
        
        if (flight == null) {
            throw new NoFlightsFoundException();
        }
        
        JsonObject response = new JsonParser().parse(gson.toJson(flight)).getAsJsonObject();
        response.addProperty("ReserveeName", reserveeName);
        response.add("Passengers", passangers);
        response.addProperty("numberOfSeats", numberOfSeats);

        return Response.status(Response.Status.OK).entity(response.toString()).type(MediaType.APPLICATION_JSON).build();
    }

}
