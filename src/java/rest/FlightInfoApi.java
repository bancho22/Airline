/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Flight;
import exceptions.NoFlightsFoundException;
import facade.FlightFacade;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Bancho
 */
@Path("flightinfo")
public class FlightInfoApi {

    @Context
    private UriInfo context;
    private FlightFacade flf;
    private Gson gson;
    
    public final static String AIRLINE_NAME = "InfamousLines";

    public FlightInfoApi() {
        flf = new FlightFacade();
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").setPrettyPrinting().create();
    }

    @GET
     @Path("api/flightinfo/{from}/{date}/{tickets}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlights(@PathParam("from") String from, @PathParam("date") String date, @PathParam("tickets") String tickets) throws ParseException{
        DateFormat sdfISO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"); 
        Date date2 = sdfISO.parse(date); 
        
        
        List<Flight> flights = flf.getFlights(from, date2, Integer.parseInt(tickets));
        Gson g = new Gson();
        JsonArray array = new JsonArray();
        JsonObject job = new JsonObject();
        for (Flight flight : flights) {
            array.add(new JsonParser().parse(g.toJson(flight)));
        }
        job.addProperty("airline", AIRLINE_NAME);
        job.add("flights", array);
        
        
        
        
        
        
        
        
        return Response.status(Response.Status.OK).entity(job.toString()).type(MediaType.APPLICATION_JSON).build();
        
        
        
    }
    
    @GET
    @Path("{from}/{to}/{date}/{numTickets}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlights2(@PathParam("from") String from, @PathParam("to") String to, @PathParam("date") String dateStr, @PathParam("numTickets") String numTickets) throws NumberFormatException, NoFlightsFoundException, ParseException{
        DateFormat sdfISO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = sdfISO.parse(dateStr);
        System.out.println(date.toString());
        int numOfSeats = Integer.parseInt(numTickets);
        
        List<Flight> flights = flf.getFlights(from, to, date, numOfSeats);
        if (flights.isEmpty()) {
            throw new NoFlightsFoundException();
        }
        
        JsonObject response = new JsonObject();
        JsonArray jsonFlights = new JsonArray();
        for (Flight flight : flights) {
            JsonObject temp = new JsonParser().parse(gson.toJson(flight)).getAsJsonObject();
            dateStr = temp.get("flightDate").getAsString();
            
            JsonObject jsonFlight = new JsonObject();
            jsonFlight.addProperty("flightID", flight.getFlightID());
            jsonFlight.addProperty("totalPrice", flight.getTotalPrice());
            jsonFlight.addProperty("numberOfSeats", flight.getNumberOfSeats());
            jsonFlight.addProperty("date", dateStr);
            jsonFlight.addProperty("traveltime", flight.getTravelTime());
            jsonFlight.addProperty("origin", flight.getOrigin());
            jsonFlight.addProperty("destination", flight.getDestination());
            jsonFlights.add(jsonFlight);
        }
        response.addProperty("airline", AIRLINE_NAME);
        response.add("flights", jsonFlights);
        return Response.status(Response.Status.OK).entity(response.toString()).type(MediaType.APPLICATION_JSON).build();
    }
}
