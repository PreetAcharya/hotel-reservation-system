/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import ejb.Reservations;
import ejb.Rooms;
import ejb.Transaction;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ejb.Customers;
import ejb.Roomtypes;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Preet
 */
@ManagedBean(name = "CustomerSessionBean")
@Stateless
@TransactionManagement(value=TransactionManagementType.CONTAINER)
public class CustomerSessionBean implements CustomerSessionBeanRemote, CustomerSessionBeanLocal {
    @javax.persistence.PersistenceContext(unitName="ParadiseHotel-ejbPU")
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void createCustomer(Object cust) {
        em.persist(cust);
    }

    @Override
    public void editCustomer(int custid, String firstname, String lastname, String email, 
            String phone, String username, String password) {
        Customers cust = em.find(Customers.class, custid);
        
        cust.setFirstname(firstname);
        cust.setLastname(lastname);
        cust.setEmail(email);
        cust.setPhone(phone);
        cust.setUsername(username);
        cust.setPassword(password);
        
        em.merge(cust);
    }

    @Override
    public void deleteCustomer(int custid) {
        Customers cust = em.find(Customers.class, custid);
        em.remove(cust);
    }

    @Override
    public void createReservation(int custid, int roomid, Date startdate, Date enddate, String specialrequest) {
        
        Reservations reserve = new Reservations();
        Customers cust = em.find(Customers.class, custid);
        Rooms room = em.find(Rooms.class, roomid);
        
        reserve.setCustid(cust);
        reserve.setRoomid(room);
        reserve.setStartdate(startdate);
        reserve.setEnddate(enddate);
        reserve.setSpecialrequest(specialrequest);
        
        em.persist(reserve);
    }    

    @Override
    public int customerLogin(String username, String password) {
        int custid = 0;
        String select = "SELECT ua FROM Customers ua WHERE ua.username=:username and ua.password=:password";
        Query query = em.createQuery(select);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Customers> results = (List<Customers>)query.getResultList();
        
        if(!results.isEmpty()){
            for(Customers c: results){
                custid = c.getCustid();
            }
        }
        return custid;
    }
     
    @Override
    public void createTransaction(int custid, String paymenttype, double price, String cardholder, String cardname, String cardno, String expirydate) {
        Transaction trans = new Transaction();
        Customers customers = em.find(Customers.class, custid);
        
        trans.setCustid(customers);
        trans.setPaymenttype(paymenttype);
        trans.setPrice(price);
        trans.setCardholder(cardholder);
        trans.setCardname(cardname);
        trans.setCardno(cardno);
        trans.setExpirydate(expirydate);
        
        em.persist(trans);
    }

    @Override
    public String getCustname(int custid) {
        String custname="";
        String select = "SELECT c FROM Customers c WHERE c.custid = :custid";
        Query query = em.createQuery(select);
        query.setParameter("custid", custid);
        List<Customers> results = (List<Customers>)query.getResultList();
        
        if(!results.isEmpty()){
            for(Customers cs: results){
                custname = cs.getFirstname()+" "+cs.getLastname();
            }
        }
        return custname;
    }

    @Override
    public Collection getAllRooms() {
        Query query = em.createQuery("SELECT rm FROM Rooms rm");
        return (Collection<Rooms>) query.getResultList();
    }

    @Override
    public Collection getRoomsByType(int room_type_id) {
        Query query = em.createQuery("SELECT r FROM Rooms r JOIN r.statusId rs WHERE r.roomTypeId = :room_type_id AND rs.statusId=1");
        Roomtypes roomtypes = em.find(Roomtypes.class, room_type_id);
        query.setParameter("room_type_id", roomtypes);
        return (Collection<Rooms>) query.getResultList();
    }

    @Override
    public boolean checkAvailabilityWithRoomNo(Date startdate, Date enddate, int roomno) {
        boolean flag = false;
        Query query = em.createQuery("SELECT COUNT(rs) FROM Reservations rs JOIN rs.roomid rm WHERE "
                + "rm.roomnumber = :roomno AND ((:startdate BETWEEN rs.startdate AND rs.enddate) OR "
                + "(:enddate BETWEEN rs.startdate AND rs.enddate) OR (rs.startdate < :enddate AND rs.enddate > :startdate))");
        
        query.setParameter("startdate", startdate);
        query.setParameter("enddate", enddate);
        query.setParameter("roomno", roomno);
        
        long count = (long) query.getSingleResult();
        
        if(count==0){
            flag = true;
        }
        return flag;
    }

    @Override
    public String getRoomTypeById(int room_type_id) {
        Query query = em.createQuery("SELECT rt.roomtype FROM Roomtypes rt WHERE rt.roomTypeId=:room_type_id");
        query.setParameter("room_type_id", room_type_id);
        return (String)query.getSingleResult();
    }

    @Override
    public Double getPriceById(int room_type_id) {
        Query query = em.createQuery("SELECT rt.price FROM Roomtypes rt WHERE rt.roomTypeId=:room_type_id");
        query.setParameter("room_type_id", room_type_id);
        return (double)query.getSingleResult();
    }

    @Override
    public Integer getRoomIdByNo(int roomno) {
        Query query = em.createQuery("SELECT rm.roomid FROM Rooms rm WHERE rm.roomnumber=:roomno");
        query.setParameter("roomno", roomno);
        return (int)query.getSingleResult();
    }

    @Override
    public Collection getReservationsByCustomer(int custid) {
        Query query = em.createQuery("SELECT rt.roomtype, rm.roomnumber,rs.startdate,rs.enddate,rs.specialrequest "
                + "FROM Reservations rs, Rooms rm, Roomtypes rt WHERE rs.roomid = rm AND rm.roomTypeId = rt AND rs.custid = :custid");
        Customers customers = em.find(Customers.class, custid);
        query.setParameter("custid", customers);
        return (Collection) query.getResultList();
    }
    
    
    
 
    
}
