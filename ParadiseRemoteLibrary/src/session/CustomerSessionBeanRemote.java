/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Collection;
import java.util.Date;
import javax.ejb.Remote;
/**
 *
 * @author Preet
 */
@Remote
public interface CustomerSessionBeanRemote {

    void createCustomer(Object cust);

    void editCustomer(int custid, String firstname, String lastname, String email, String phone, String username, String password);

    void deleteCustomer(int custid);

    void createReservation(int custid, int roomid, Date startdate, Date enddate, String specialrequest);

    void createTransaction(int custid, String paymenttype, double price, String cardholder, String cardname, String cardno, String expirydate);

    int customerLogin(String username, String password);

    String getCustname(int custid);

    Collection getAllRooms();
    
    Collection getRoomsByType(int room_type_id);

    boolean checkAvailabilityWithRoomNo(Date startdate, Date enddate, int roomno);

    String getRoomTypeById(int room_type_id);

    Double getPriceById(int room_type_id);

    Integer getRoomIdByNo(int roomno);

    Collection getReservationsByCustomer(int custid);
}
