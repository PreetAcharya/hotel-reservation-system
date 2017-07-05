/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import ejb.Rooms;
import ejb.Roomtypes;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Preet
 */
@Stateless
@TransactionManagement(value=TransactionManagementType.CONTAINER)
public class GuestSessionBean implements GuestSessionBeanRemote, GuestSessionBeanLocal {
    @javax.persistence.PersistenceContext(unitName="ParadiseHotel-ejbPU")
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Collection getAvailableRooms() {
        Query query = em.createQuery("SELECT rm FROM Rooms rm JOIN rm.roomTypeId rt WHERE rm.roomTypeId = :roomTypeId");
        Roomtypes roomtypes = em.find(Roomtypes.class, 1);
        query.setParameter("roomTypeId", roomtypes);
        return (Collection<Rooms>) query.getResultList();
    }

    @Override
    public Collection getAllRoomTypes() {
        Query query = em.createQuery("SELECT rt FROM Roomtypes rt");
        return (Collection<Roomtypes>) query.getResultList();
    }

    @Override
    public Boolean checkAvailableRooms(Date startdate, Date enddate, int room_type_id, int qty) {
        
        boolean flag = false;
        Query query = em.createQuery("SELECT COUNT(rs) FROM Reservations rs JOIN rs.roomid rm WHERE "
                + "rm.roomTypeId = :room_type_id AND ((:startdate BETWEEN rs.startdate AND rs.enddate) OR "
                + "(:enddate BETWEEN rs.startdate AND rs.enddate) OR (rs.startdate < :enddate AND rs.enddate > :startdate))");
        
        Roomtypes roomtypes = em.find(Roomtypes.class, room_type_id);
        query.setParameter("startdate", startdate);
        query.setParameter("enddate", enddate);
        query.setParameter("room_type_id", roomtypes);
        long count = (long) query.getSingleResult();
        //System.out.println("booked "+count);
        
        Query query2 = em.createQuery("SELECT COUNT(r) FROM Rooms r WHERE r.roomTypeId = :room_type_id");
        query2.setParameter("room_type_id", roomtypes);
        long total = (long) query2.getSingleResult();
        //System.out.println("total "+total);
        //System.out.println("QTY "+qty);
        int available = (int) (total-count);
        //System.out.println("Available "+available);
        if(qty<=available){
            flag = true;
        }
        return flag;
    }
      
}
