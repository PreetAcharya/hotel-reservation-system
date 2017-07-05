/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.Admin;
import ejb.Customers;
import ejb.Reservations;
import ejb.Rooms;
import ejb.Roomstatus;
import ejb.Roomtypes;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import session.AdminSessionBeanRemote;

/**
 *
 * @author Preet
 */
@ManagedBean
@SessionScoped
public class AdminBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EJB
    private AdminSessionBeanRemote adminSessionBean;
    
    private Admin admin = new Admin();
    private Reservations reservations = new Reservations();
    private Customers customers = new Customers();
    private Rooms rooms = new Rooms();
    private Roomtypes roomtypes = new Roomtypes();
    private Roomstatus roomstatus = new Roomstatus();
    
    private String successmsg = "";
    private String failmsg = "";
    private String adminname = "";
    
    public AdminBean(){
    }

    public AdminSessionBeanRemote getAdminSessionBean() {
        return adminSessionBean;
    }

    public void setAdminSessionBean(AdminSessionBeanRemote adminSessionBean) {
        this.adminSessionBean = adminSessionBean;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
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

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public Reservations getReservations() {
        return reservations;
    }

    public void setReservations(Reservations reservations) {
        this.reservations = reservations;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
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

    public Roomstatus getRoomstatus() {
        return roomstatus;
    }

    public void setRoomstatus(Roomstatus roomstatus) {
        this.roomstatus = roomstatus;
    }
    
    
    
    public String login(){
        String response = "";
        int adminid = adminSessionBean.adminLogin(admin.getUsername(), admin.getPassword());
        if(adminid > 0){
            admin.setId(adminid);
            rooms.setRoomid(0);
            adminname = adminSessionBean.getAdminNameById(adminid);
            response = "adminhome?faces-redirect=true";
        }
        else{
            failmsg = "Invalid Credentials: Please Try Again";
            response = "adminlogin?faces-redirect=true";
        }
        return response;    
    }
    
    public String logout(){
        reservations.setStartdate(null);
        reservations.setEnddate(null);
        roomtypes.setPrice(0);
        rooms.setFloor(0);
        return "adminlogin?faces-redirect=true";
    }
    
    public Collection viewReservations(){
        Date startdate = reservations.getStartdate();
        Date enddate = reservations.getEnddate();
        int floor = rooms.getFloor();
        double price = roomtypes.getPrice();
        
        
        if(startdate==null && enddate==null && floor!=0 && price==0){
            return adminSessionBean.allReservationsByFloor(floor);
        }
        else if(startdate==null && enddate==null && floor==0 && price!=0){
            int room_type_id = adminSessionBean.roomIdFromPrice(price);
            return adminSessionBean.allReservationsByRoomTypeId(room_type_id);
        }
        else if(startdate!=null && enddate!=null && floor==0 && price==0){
            return adminSessionBean.allReservationsByDates(startdate, enddate);
        }
        else{
            return adminSessionBean.allReservations();
        }
    }
    
    public void clear1(){
        roomtypes.setPrice(0);
        rooms.setFloor(0);
    }
    public void clear2(){
        reservations.setStartdate(null);
        reservations.setEnddate(null);
        roomtypes.setPrice(0);
    }
    public void clear3(){
        reservations.setStartdate(null);
        reservations.setEnddate(null);
        rooms.setFloor(0);
    }
    
    public void roomAdd(){
        if(rooms.getRoomid()==0){
            adminSessionBean.addRoom(roomtypes.getRoomTypeId(), rooms.getRoomnumber(), rooms.getFloor(), roomstatus.getStatusId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("New Room Added!!"));
        }else{
            adminSessionBean.editRoom(rooms.getRoomid(), roomtypes.getRoomTypeId(), rooms.getRoomnumber(), rooms.getFloor(), roomstatus.getStatusId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Room Edited!!"));
        }
        roomtypes.setRoomTypeId(0);
        rooms.setRoomnumber(0);
        rooms.setFloor(0);
        roomstatus.setStatusId(0);
        rooms.setRoomid(0);
    }
    
    public void edit(int roomid){
        for(Rooms rm : (Collection<Rooms>)adminSessionBean.roomById(roomid)){
            roomtypes.setRoomTypeId(rm.getRoomTypeId().getRoomTypeId());
            rooms.setRoomnumber(rm.getRoomnumber());
            rooms.setFloor(rm.getFloor());
            roomstatus.setStatusId(rm.getStatusId().getStatusId());
            rooms.setRoomid(roomid);
        }
    }
    
    public void reset(){
        roomtypes.setRoomTypeId(0);
        rooms.setRoomnumber(0);
        rooms.setFloor(0);
        roomstatus.setStatusId(0);
        rooms.setRoomid(0);
    }
    
}
