/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.Roomtypes;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import session.GuestSessionBeanRemote;

/**
 * REST Web Service
 *
 * @author Preet
 */
@Path("service")
public class GuestService {
    
    GuestSessionBeanRemote guestSessionBean = lookupGuestSessionBeanRemote();    
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GuestService
     */
    public GuestService() {
    }

    /**
     * Retrieves representation of an instance of service.GuestService
     * @return an instance of java.util.Collection
     */
    @GET
    @Produces("application/json")
    public Collection<Roomtypes> getJson() {
        //TODO return proper representation object
        return (Collection<Roomtypes>)guestSessionBean.getAllRoomTypes();
    }

    /**
     * PUT method for updating or creating an instance of GuestService
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Produces("application/json")
    public void putJson() {
        
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response checkAvailableRooms(@FormParam("startdate") String startdate, @FormParam("enddate") String enddate,
            @FormParam("room_type_id") String room_type_id, @FormParam("qty") String qty) throws ParseException{
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date stDate = df.parse(startdate);
        Date enDate = df.parse(enddate);
        int roomTypeId = Integer.parseInt(room_type_id.trim());
        int quantity = Integer.parseInt(qty);
        
        boolean res = guestSessionBean.checkAvailableRooms(stDate, enDate, roomTypeId, quantity);
        
        URI uri = UriBuilder.fromPath("http://localhost:8080/ParadiseWebServiceClient/index.jsp").queryParam("msg", res)
                .queryParam("roomtypeid", roomTypeId).build();
        return Response.seeOther(uri).build();
    }
    
    private GuestSessionBeanRemote lookupGuestSessionBeanRemote() {
        try {
            javax.naming.Context c = new InitialContext();
            return (GuestSessionBeanRemote) c.lookup("java:global/ParadiseHotel/ParadiseHotel-ejb/GuestSessionBean!session.GuestSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
