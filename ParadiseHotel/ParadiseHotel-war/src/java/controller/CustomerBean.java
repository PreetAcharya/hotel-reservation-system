/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.Customers;
import ejb.Reservations;
import ejb.Rooms;
import ejb.Roomtypes;
import ejb.Transaction;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import session.AdminSessionBeanRemote;
import session.CustomerSessionBeanRemote;
import session.GuestSessionBeanRemote;

/**
 *
 * @author Preet
 */
@ManagedBean
@SessionScoped
public class CustomerBean implements Serializable {
    

    private static final long serialVersionUID = 1L;
    
    @EJB
    private GuestSessionBeanRemote guestSessionBean;
    @EJB
    private CustomerSessionBeanRemote customerSessionBean;
    
    
    
    private Customers customers = new Customers();
    private Rooms rooms = new Rooms();
    private Roomtypes roomtypes = new Roomtypes();
    private Reservations reservations = new Reservations();
    private Transaction transaction = new Transaction();
    
    private String successmsg = "";
    private String failmsg = "";
    private Date curdate = new Date();
    
    private int qty = 0;
    private String custname = "";
    
    @ManagedProperty(value="#{roomcart}")
    private Roomcart roomcart;
    
    /**
     * Creates a new instance of CustomerBean
     */
    public CustomerBean() {
    }
    
    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }

    public Roomtypes getRoomtypes() {
        return roomtypes;
    }

    public void setRoomtypes(Roomtypes roomtypes) {
        this.roomtypes = roomtypes;
    }
    
    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Reservations getReservations() {
        return reservations;
    }

    public void setReservations(Reservations reservations) {
        this.reservations = reservations;
    }
    
    public String getSuccessmsg() {
        return successmsg;
    }

    public void setSuccessmsg(String successmsg) {
        this.successmsg = successmsg;
    }

    public String getFailmsg() {
        return failmsg;
    }

    public void setFailmsg(String failmsg) {
        this.failmsg = failmsg;
    }
    
    public Date getCurdate() {
        return curdate;
    }

    public void setCurdate(Date curdate) {
        this.curdate = curdate;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Roomcart getRoomcart() {
        return roomcart;
    }

    public void setRoomcart(Roomcart roomcart) {
        this.roomcart = roomcart;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public CustomerSessionBeanRemote getCustomerSessionBean() {
        return customerSessionBean;
    }

    public void setCustomerSessionBean(CustomerSessionBeanRemote customerSessionBean) {
        this.customerSessionBean = customerSessionBean;
    }
    
    
      
    public String addCustomer(){
        successmsg = "You Have Signed Up Successfully! Please LogIn Now";
        customerSessionBean.createCustomer(customers);
        return "login?faces-redirect=true";
    }
    
    public Collection allRoomDet(){
       return guestSessionBean.getAvailableRooms();
    }
    
    public Collection<Roomtypes> allRoomTypes(){
        return guestSessionBean.getAllRoomTypes();
    }
    
    public Collection<Rooms> allRooms(){
        return customerSessionBean.getAllRooms();
    }
    
    public Collection<Rooms> allRoomsByType(int room_type_id){
        return customerSessionBean.getRoomsByType(room_type_id);
    }
    
    public String checkAvalabilty(int rid){
        Date startdate = reservations.getStartdate();
        Date enddate = reservations.getEnddate();
        int room_type_id = rid+1;
        int quantity = qty;
        
        if(quantity>0 && quantity<=10){
            boolean check = guestSessionBean.checkAvailableRooms(startdate, enddate, room_type_id, quantity);
            if(check == true){
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Booking is Available: Please Login to book the room", ""));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Booking is full", ""));
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Enter Qty between 1 to 10", ""));
        }
        reservations.setStartdate(null);
        reservations.setEnddate(null);
        qty = 0;
        return "roombooking?faces-redirect=true";
    }
    
    public String login(){
        String response = "";
        int custid = customerSessionBean.customerLogin(customers.getUsername(), customers.getPassword());
        if(custid > 0){
            customers.setCustid(custid);
            custname = customerSessionBean.getCustname(custid);
            response = "customerhome?faces-redirect=true";
        }
        else{
            failmsg = "Invalid Credentials: Please Try Again";
            response = "login?faces-redirect=true";
        }
        return response;
        
    }
    
    public String logout(){
        
        return "login?faces-redirect=true";
    }
    
    public String checkAvailableByRoomNo(int room_type_id,int roomno){
        Date startdate = reservations.getStartdate();
        Date enddate = reservations.getEnddate();
        
        boolean check = customerSessionBean.checkAvailabilityWithRoomNo(startdate, enddate, roomno);
        if(check == true){    
            roomcart.addToCart(customerSessionBean.getRoomIdByNo(roomno), 
                    customerSessionBean.getRoomTypeById(room_type_id),
                    roomno, startdate, enddate, customerSessionBean.getPriceById(room_type_id), 
                    reservations.getSpecialrequest());
            reservations.setSpecialrequest(null);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "This room is already booked in these dates", ""));
        }
        return "customerhome?faces-redirect=true";
    }
    
    public void save(){
        for(Roomcart r : roomcart.viewCart()){
            customerSessionBean.createReservation(customers.getCustid(), r.getRoomid(), r.getStartdate(), r.getEnddate(), r.getSpecialrequest()); 
        }
        customerSessionBean.createTransaction(customers.getCustid(), transaction.getPaymenttype(), transaction.getPrice(), 
                transaction.getCardholder(), transaction.getCardname(), transaction.getCardno(), transaction.getExpirydate());
        
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Congratulations,\n" + custname +"\nYour Booking is Done"));
        
        transaction.setPaymenttype("");
        transaction.setPrice(0);
        transaction.setCardholder("");
        transaction.setCardname("");
        transaction.setCardno("");
        transaction.setExpirydate("");
        
        roomcart.emptyCart();
    }
    
    public Collection resByCust(){
        return customerSessionBean.getReservationsByCustomer(customers.getCustid());
    }
}
