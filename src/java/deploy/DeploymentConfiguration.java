/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deploy;

import entity.Flight;
import facade.FlightFacade;
import java.util.Date;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Bancho
 */




//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


//    VERY IMPORTANT WHEN CONNECTING TO AZURE:
//    Your URL should be jdbc:sqlserver://server:port;DatabaseName=dbname 
//    and Class name should be like com.microsoft.sqlserver.jdbc.SQLServerDriver 



//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

















@WebListener
public class DeploymentConfiguration implements ServletContextListener {
    
    public static String PU_NAME = "AirlinePU";
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        Map<String, String> env = System.getenv();     //If we are running in the Azure environment change the pu-name
//        if (env.keySet().contains("OPENSHIFT_MYSQL_DB_HOST")) {
//            PU_NAME = "pu_Azure";
//        }
        PU_NAME = "pu_Azure";
        setUpTable();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    private void setUpTable() {
        FlightFacade ff = new FlightFacade();
        Flight flight = new Flight();
        flight.setFlightDate(new Date());
        flight.setFlightID("ABC");
        flight.setDestination("SFX");
        flight.setNumberOfSeats(100);
        flight.setOrigin("CPH");
        flight.setTotalPrice(150);
        flight.setTravelTime(60);
        ff.addFlight(flight);
    }

}
