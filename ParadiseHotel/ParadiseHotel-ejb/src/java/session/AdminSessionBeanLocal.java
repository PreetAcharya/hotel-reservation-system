/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import ejb.Reservations;
import ejb.Rooms;
import ejb.Roomstatus;
import ejb.Roomtypes;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Preet
 */
@Local
public interface AdminSessionBeanLocal {

    void addRoom(int room_type_id, int roomnumber, int floor, int status_id);

    void editRoom(int roomid, int room_type_id, int roomnumber, int floor, int status_id);

    void deleteRoom(int roomid);

    void createAdmin(String username, String password, String status);

    void editAdmin(int id, String username, String password, String status);

    int adminLogin(String username, String password);

    String getAdminNameById(int adminid);

    Collection allReservations();

    Collection allFloors();
    
    Collection allReservationsByFloor(int floor);

    Collection allReservationsByRoomTypeId(int room_type_id);

    Collection allRoomTypes();

    Integer roomIdFromPrice(double price);

    Collection allReservationsByDates(Date startdate, Date enddate);

    Collection allRooms();

    Collection allRoomStatus();

    Collection roomById(int roomid);
    
    List<Reservations> getReservationByFloor(int floor);

    List<Reservations> getReservationByDate(Date startdate, Date enddate);

    List<Reservations> getReservationByPrice(double startPrice, double endPrice);

    List<Rooms> getAllRooms();

    Rooms getRoomById(int roomid);

    List<Roomtypes> getAllRoomTypes();

    List<Roomstatus> getAllRoomStatus();

    Roomstatus getRoomstatusByStatus(String status);

    Roomtypes getRoomtypesByType(String type);

}
