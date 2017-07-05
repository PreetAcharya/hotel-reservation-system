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
public interface GuestSessionBeanRemote {

    Collection getAvailableRooms();

    Collection getAllRoomTypes();

    Boolean checkAvailableRooms(Date startdate, Date enddate, int room_type_id, int qty);
    
}
