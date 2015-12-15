/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import deploy.DeploymentConfiguration;
import entity.Flight;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Bancho
 */
public class FlightFacade {
    
    private EntityManagerFactory emf;

    public FlightFacade() {
        emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
    }
    public List<Flight> getFlights(String from, Date date, int num){
        EntityManager em = getEntityManager();
        List<Flight> flights = null;
        try {
            Query query = em.createQuery("Select f FROM Flight f Where f.origin = :origin and f.flightDate:date and f.numberOfSeats:num");
            query.setParameter("origin", from);
            query.setParameter("date", date);
            query.setParameter("num", num);
            flights = query.getResultList();
        } catch (Exception e) {
        }
        return flights;
    }

    public Flight getSingleFlight(String ID){
        EntityManager em = getEntityManager();
        Flight flight = null;
        
        try{
            Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightID = :ID");
            query.setParameter("flightId", ID);
            flight = (Flight) query.getSingleResult();
        }catch(NoResultException ex){
            //return null
        }finally{
            em.close();
        }
        return flight;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Flight addFlight(Flight flight){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(flight);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return flight;
    }
    
    public List<Flight> getFlights(String from, String to, Date date, int numOfSeats){
        EntityManager em = getEntityManager();
        List<Flight> flights = null;
        try{
            Query query = em.createQuery("SELECT f FROM Flight f WHERE f.origin = :origin AND f.destination = :destination AND f.flightDate = :date AND f.numberOfSeats >= :numOfSeats");
            query.setParameter("origin", from);
            query.setParameter("destination", to);
            query.setParameter("date", date);
            query.setParameter("numOfSeats", numOfSeats);
            flights = query.getResultList();
        }finally{
            em.close();
        }
        return flights;
    }
    
}
