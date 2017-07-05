/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Preet
 */
@Entity
@Table(name = "reservations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservations.findAll", query = "SELECT r FROM Reservations r"),
    @NamedQuery(name = "Reservations.findByReservid", query = "SELECT r FROM Reservations r WHERE r.reservid = :reservid"),
    @NamedQuery(name = "Reservations.findByStartdate", query = "SELECT r FROM Reservations r WHERE r.startdate = :startdate"),
    @NamedQuery(name = "Reservations.findByEnddate", query = "SELECT r FROM Reservations r WHERE r.enddate = :enddate"),
    @NamedQuery(name = "Reservations.findBySpecialrequest", query = "SELECT r FROM Reservations r WHERE r.specialrequest = :specialrequest")})
public class Reservations implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reservid")
    private Integer reservid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date enddate;
    @Size(max = 255)
    @Column(name = "specialrequest")
    private String specialrequest;
    @JoinColumn(name = "custid", referencedColumnName = "custid")
    @ManyToOne(optional = false)
    private Customers custid;
    @JoinColumn(name = "roomid", referencedColumnName = "roomid")
    @ManyToOne(optional = false)
    private Rooms roomid;

    public Reservations() {
    }

    public Reservations(Integer reservid) {
        this.reservid = reservid;
    }

    public Reservations(Integer reservid, Date startdate, Date enddate) {
        this.reservid = reservid;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Integer getReservid() {
        return reservid;
    }

    public void setReservid(Integer reservid) {
        this.reservid = reservid;
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

    public String getSpecialrequest() {
        return specialrequest;
    }

    public void setSpecialrequest(String specialrequest) {
        this.specialrequest = specialrequest;
    }

    public Customers getCustid() {
        return custid;
    }

    public void setCustid(Customers custid) {
        this.custid = custid;
    }

    public Rooms getRoomid() {
        return roomid;
    }

    public void setRoomid(Rooms roomid) {
        this.roomid = roomid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservid != null ? reservid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservations)) {
            return false;
        }
        Reservations other = (Reservations) object;
        if ((this.reservid == null && other.reservid != null) || (this.reservid != null && !this.reservid.equals(other.reservid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Reservations[ reservid=" + reservid + " ]";
    }
    
}
