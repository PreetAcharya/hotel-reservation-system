/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import ejb.Admin;
import ejb.Reservations;
import ejb.Rooms;
import ejb.Roomstatus;
import ejb.Roomtypes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
public class AdminSessionBean implements AdminSessionBeanRemote, AdminSessionBeanLocal {
    @javax.persistence.PersistenceContext(unitName="ParadiseHotel-ejbPU")
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void addRoom(int room_type_id, int roomnumber, int floor, int status_id) {
        Rooms room = new Rooms();
        Roomtypes roomtype = em.find(Roomtypes.class, room_type_id);
        Roomstatus roomstatus = em.find(Roomstatus.class, status_id);
        
        room.setRoomTypeId(roomtype);
        room.setRoomnumber(roomnumber);
        room.setFloor(floor);
        room.setStatusId(roomstatus);
        
        em.persist(room);
    }

    @Override
    public void editRoom(int roomid, int room_type_id, int roomnumber, int floor, int status_id) {
        Rooms room = em.find(Rooms.class, roomid);
        Roomtypes roomtype = em.find(Roomtypes.class, room_type_id);
        Roomstatus roomstatus = em.find(Roomstatus.class, status_id);
        
        room.setRoomTypeId(roomtype);
        room.setRoomnumber(roomnumber);
        room.setFloor(floor);
        room.setStatusId(roomstatus);
        
        em.persist(room);
    }

    @Override
    public void deleteRoom(int roomid) {
        Rooms room = em.find(Rooms.class, roomid);
        em.remove(room);
    }

    @Override
    public void createAdmin(String username, String password, String status) {
        Admin admin = new Admin();
        
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setStatus(status);
        
        em.merge(admin);
    }

    @Override
    public void editAdmin(int id, String username, String password, String status) {
        Admin admin = em.find(Admin.class, id);
        
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setStatus(status);
        
        em.merge(admin);
    }

    @Override
    public int adminLogin(String username, String password) {
        int adminid = 0;
        String select = "SELECT ua FROM Admin ua WHERE ua.username=:username and ua.password=:password";
        Query query = em.createQuery(select);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Admin> results = (List<Admin>)query.getResultList();
        
        if(!results.isEmpty()){
            for(Admin c: results){
                adminid = c.getId();
            }
        }
        return adminid;
    }

    @Override
    public String getAdminNameById(int adminid) {
        String adminname="";
        String select = "SELECT c FROM Admin c WHERE c.id = :adminid";
        Query query = em.createQuery(select);
        query.setParameter("adminid", adminid);
        List<Admin> results = (List<Admin>)query.getResultList();
        
        if(!results.isEmpty()){
            for(Admin cs: results){
                adminname = cs.getUsername();
            }
        }
        return adminname;
    }

    @Override
    public Collection allReservations() {
        Query query = em.createQuery("SELECT cs.firstname,cs.lastname,rm.roomnumber,rs.startdate,rs.enddate,rs.specialrequest "
                + "FROM Reservations rs, Customers cs, Rooms rm WHERE rs.custid = cs AND rs.roomid = rm");
        return (Collection) query.getResultList();
    }

    @Override
    public Collection allFloors() {
        Query query = em.createQuery("SELECT f.floor FROM Rooms f GROUP BY f.floor");
        return (Collection) query.getResultList();
    }
    
    @Override
    public Collection allReservationsByFloor(int floor) {
        Query query = em.createQuery("SELECT cs.firstname,cs.lastname,rm.roomnumber,rs.startdate,rs.enddate,rs.specialrequest "
                + "FROM Reservations rs, Customers cs, Rooms rm WHERE rs.custid = cs AND rs.roomid = rm AND rm.floor=:floor");
        query.setParameter("floor", floor);
        return (Collection) query.getResultList();
    }

    @Override
    public Collection allReservationsByRoomTypeId(int room_type_id) {
        Query query = em.createQuery("SELECT cs.firstname,cs.lastname,rm.roomnumber,rs.startdate,rs.enddate,rs.specialrequest "
                + "FROM Reservations rs, Customers cs, Rooms rm WHERE rs.custid = cs AND rs.roomid = rm AND rm.roomTypeId=:room_type_id");
        Roomtypes roomtypes = em.find(Roomtypes.class, room_type_id);
        query.setParameter("room_type_id", roomtypes);
        return (Collection) query.getResultList();
    }

    @Override
    public Collection allRoomTypes() {
        Query query = em.createQuery("SELECT rt FROM Roomtypes rt");
        return (Collection) query.getResultList();
    }

    @Override
    public Integer roomIdFromPrice(double price) {
        Query query = em.createQuery("SELECT rt.roomTypeId FROM Roomtypes rt WHERE rt.price = :price");
        query.setParameter("price", price);
        return (int)query.getSingleResult();
    }

    @Override
    public Collection allReservationsByDates(Date startdate, Date enddate) {
        Query query = em.createQuery("SELECT cs.firstname,cs.lastname,rm.roomnumber,rs.startdate,rs.enddate,rs.specialrequest "
                + "FROM Reservations rs, Customers cs, Rooms rm WHERE rs.custid = cs AND rs.roomid = rm AND "
                + "rs.startdate>=:startdate AND rs.enddate<=:enddate");
        query.setParameter("startdate", startdate);
        query.setParameter("enddate", enddate);
        return (Collection) query.getResultList();
    }

    @Override
    public Collection allRooms() {
        Query query = em.createQuery("SELECT rm.roomnumber, rt.roomtype, rm.floor, rs.status, rm.roomid FROM Rooms rm, "
                + "Roomtypes rt, Roomstatus rs WHERE rm.roomTypeId = rt AND rm.statusId = rs");
        return (Collection) query.getResultList();
    }

    @Override
    public Collection allRoomStatus() {
        Query query = em.createQuery("SELECT rs FROM Roomstatus rs");
        return (Collection) query.getResultList();
    }

    @Override
    public Collection roomById(int roomid) {
        Query query = em.createQuery("SELECT rm FROM Rooms rm WHERE rm.roomid=:roomid");
        query.setParameter("roomid", roomid);
        return (Collection<Rooms>) query.getResultList();
    }
    
    @Override
    public List<Reservations> getReservationByFloor(int floor) {
        Query query = em.createNamedQuery("Reservations.findAll");
        List<Reservations> result = new ArrayList();
        List<Reservations> reservations = query.getResultList();
        for(Reservations reservation : reservations){
            if(reservation.getRoomid().getFloor() == floor){
                result.add(reservation);
            }
        }
        return result;
    }

    @Override
    public List<Reservations> getReservationByDate(Date startdate, Date enddate) {
        Query query =  em.createQuery("SELECT r FROM Reservations r WHERE r.startdate >= :startdate and r.enddate <= :enddate")
                    .setParameter("startdate", startdate)
                    .setParameter("enddate", enddate);
        List<Reservations> reservations = query.getResultList();
        List<Reservations> result = new ArrayList();
        for(Reservations reservation : reservations){
            result.add(reservation);
        }
        return result;
        
    }

    @Override
    public List<Reservations> getReservationByPrice(double startPrice, double endPrice) {
        Query query = em.createNamedQuery("Reservations.findAll");
        List<Reservations> result = new ArrayList();
        List<Reservations> reservations = query.getResultList();
        for(Reservations reservation : reservations){
            if(reservation.getRoomid().getRoomTypeId().getPrice() >= startPrice 
                    && reservation.getRoomid().getRoomTypeId().getPrice() <= endPrice){
                result.add(reservation);
            }
        }
        return result;
    }

    @Override
    public List<Rooms> getAllRooms() {
        return em.createNamedQuery("Rooms.findAll").getResultList();
    }

    @Override
    public Rooms getRoomById(int roomid) {
//        return (Rooms)em.find(Rooms.class, roomid);
        return (Rooms) em.createNamedQuery("Rooms.findByRoomid").setParameter("roomid", roomid).getSingleResult();
    }

    @Override
    public List<Roomtypes> getAllRoomTypes() {
        return em.createNamedQuery("Roomtypes.findAll").getResultList();
    }

    @Override
    public List<Roomstatus> getAllRoomStatus() {
        return em.createNamedQuery("Roomstatus.findAll").getResultList();
    }

    @Override
    public Roomstatus getRoomstatusByStatus(String status) {
        return (Roomstatus) em.createNamedQuery("Roomstatus.findByStatus").setParameter("status", status).getSingleResult();
    }

    @Override
    public Roomtypes getRoomtypesByType(String type) {
        return (Roomtypes) em.createNamedQuery("Roomtypes.findByRoomtype").setParameter("roomtype", type).getSingleResult();
    }

    
}
