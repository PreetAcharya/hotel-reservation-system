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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Preet
 */
@Entity
@Table(name = "roomtypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roomtypes.findAll", query = "SELECT r FROM Roomtypes r"),
    @NamedQuery(name = "Roomtypes.findByRoomTypeId", query = "SELECT r FROM Roomtypes r WHERE r.roomTypeId = :roomTypeId"),
    @NamedQuery(name = "Roomtypes.findByRoomtype", query = "SELECT r FROM Roomtypes r WHERE r.roomtype = :roomtype"),
    @NamedQuery(name = "Roomtypes.findByRoomdetails", query = "SELECT r FROM Roomtypes r WHERE r.roomdetails = :roomdetails"),
    @NamedQuery(name = "Roomtypes.findByPrice", query = "SELECT r FROM Roomtypes r WHERE r.price = :price")})
public class Roomtypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "room_type_id")
    private Integer roomTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "roomtype")
    private String roomtype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "roomdetails")
    private String roomdetails;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomTypeId")
    private Collection<Rooms> roomsCollection;

    public Roomtypes() {
    }

    public Roomtypes(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Roomtypes(Integer roomTypeId, String roomtype, String roomdetails, double price) {
        this.roomTypeId = roomTypeId;
        this.roomtype = roomtype;
        this.roomdetails = roomdetails;
        this.price = price;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getRoomdetails() {
        return roomdetails;
    }

    public void setRoomdetails(String roomdetails) {
        this.roomdetails = roomdetails;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @XmlTransient
    public Collection<Rooms> getRoomsCollection() {
        return roomsCollection;
    }

    public void setRoomsCollection(Collection<Rooms> roomsCollection) {
        this.roomsCollection = roomsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomTypeId != null ? roomTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roomtypes)) {
            return false;
        }
        Roomtypes other = (Roomtypes) object;
        if ((this.roomTypeId == null && other.roomTypeId != null) || (this.roomTypeId != null && !this.roomTypeId.equals(other.roomTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Roomtypes[ roomTypeId=" + roomTypeId + " ]";
    }
    
}
