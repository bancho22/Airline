/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
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
    
    public final static String AIRLINE_NAME = "TheNameOfOurAirline";

    public FlightInfoApi() {
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlights(){
        return null;
    }
    
    @GET
    @Path("{from}/{to}/{date}/{numTickets}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlights2(@PathParam("from") String from, @PathParam("to") String to, @PathParam("date") String date, @PathParam("numTickets") String numTickets){
        
        return null;
    }
}
