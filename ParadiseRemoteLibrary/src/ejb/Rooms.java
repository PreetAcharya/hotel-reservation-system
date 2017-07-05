/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Preet
 */
@Entity
@Table(name = "rooms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
    @NamedQuery(name = "Rooms.findByRoomid", query = "SELECT r FROM Rooms r WHERE r.roomid = :roomid"),
    @NamedQuery(name = "Rooms.findByRoomnumber", query = "SELECT r FROM Rooms r WHERE r.roomnumber = :roomnumber"),
    @NamedQuery(name = "Rooms.findByFloor", query = "SELECT r FROM Rooms r WHERE r.floor = :floor")})
public class Rooms implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "roomid")
    private Integer roomid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "roomnumber")
    private int roomnumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "floor")
    private int floor;
    @JoinColumn(name = "room_type_id", referencedColumnName = "room_type_id")
    @ManyToOne(optional = false)
    private Roomtypes roomTypeId;
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @ManyToOne(optional = false)
    private Roomstatus statusId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomid")
    private Collection<Reservations> reservationsCollection;

    public Rooms() {
    }

    public Rooms(Integer roomid) {
        this.roomid = roomid;
    }

    public Rooms(Integer roomid, int roomnumber, int floor) {
        this.roomid = roomid;
        this.roomnumber = roomnumber;
        this.floor = floor;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public int getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(int roomnumber) {
        this.roomnumber = roomnumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Roomtypes getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Roomtypes roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Roomstatus getStatusId() {
        return statusId;
    }

    public void setStatusId(Roomstatus statusId) {
        this.statusId = statusId;
    }

    @XmlTransient
    public Collection<Reservations> getReservationsCollection() {
        return reservationsCollection;
    }

    public void setReservationsCollection(Collection<Reservations> reservationsCollection) {
        this.reservationsCollection = reservationsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomid != null ? roomid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rooms)) {
            return false;
        }
        Rooms other = (Rooms) object;
        if ((this.roomid == null && other.roomid != null) || (this.roomid != null && !this.roomid.equals(other.roomid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Rooms[ roomid=" + roomid + " ]";
    }
    
}
