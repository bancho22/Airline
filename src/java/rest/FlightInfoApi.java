/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import facade.FlightFacade;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    FlightFacade ff = new FlightFacade();

    @Context
    private UriInfo context;
    private FlightFacade flf;
    
    public final static String AIRLINE_NAME = "TheNameOfOurAirline";

    public FlightInfoApi() {
        flf = new FlightFacade();
    }

    @GET
     @Path("api/flightinfo/{from}/{date}/{tickets}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlights(@PathParam("from") String from, @PathParam("date") String date, @PathParam("tickets") String tickets){
        
        
        
        
        
        
        
        
        return null;
        
        
        
    }
    
    @GET
    @Path("{from}/{to}/{date}/{numTickets}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlights2(@PathParam("from") String from, @PathParam("to") String to, @PathParam("date") String date, @PathParam("numTickets") String numTickets) throws ParseException{
        DateFormat sdfISO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date date2 = sdfISO.parse(date);
        return null;
    }
}
