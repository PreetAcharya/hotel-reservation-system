/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Preet
 */
@ManagedBean
@SessionScoped
public class Roomcart implements Serializable{
    
    private int roomid;
    private String roomtype;
    private int roomno;
    private Date startdate;
    private Date enddate;
    private double price;
    private String specialrequest;
    
    private Roomcart rc;
    private List<Roomcart> list = new ArrayList<Roomcart>();
    
    private double total;
    
    public Roomcart(){
        
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public int getRoomno() {
        return roomno;
    }

    public void setRoomno(int roomno) {
        this.roomno = roomno;
    }
    
    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public Date getStartdate() {
        return startdate;
    }
            
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSpecialrequest() {
        return specialrequest;
    }

    public void setSpecialrequest(String specialrequest) {
        this.specialrequest = specialrequest;
    }

    public Roomcart getRc() {
        return rc;
    }

    public void setRc(Roomcart rc) {
        this.rc = rc;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public String frmtStartDate(){
        return new SimpleDateFormat("dd-MMM").format(startdate);
    } 
    public String frmtEndDate(){
        return new SimpleDateFormat("dd-MMM").format(enddate);
    } 

    public List<Roomcart> getList() {
        return list;
    }

    public void setList(List<Roomcart> list) {
        this.list = list;
    }
    
    public void addToCart(int roomid, String roomtype, int roomno, 
            Date startdate, Date enddate, double price, String specialrequest){
        /*boolean flag = false;
        if(list.isEmpty()){
            flag = true;
        }else{
            for(Roomcart l : list){
                if(l.getRoomid()!=0 && l.getRoomid()!=roomid){
                    flag = true;
                }
            }
        }
        
        if(flag == true){*/
            rc = new Roomcart();
            rc.setRoomid(roomid);
            rc.setRoomtype(roomtype);
            rc.setRoomno(roomno);
            rc.setStartdate(startdate);
            rc.setEnddate(enddate);
            rc.setPrice(price);
            rc.setSpecialrequest(specialrequest);
            list.add(rc);  
        //}
    }
    public List<Roomcart> viewCart(){
        return list;
    }
    public void removeFromCart(int index)
    {
        list.remove(index);
    }
    public double totalAmount(){
        total = 0;
        for (Roomcart l : list) {
            total += l.getPrice();
        }
        total += total*0.13;
        return total;
    }
    public void emptyCart(){
        list = new ArrayList<Roomcart>();
    }
}
