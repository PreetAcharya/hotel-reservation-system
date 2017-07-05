/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Preet
 */
@Entity
@Table(name = "transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t"),
    @NamedQuery(name = "Transaction.findByTransid", query = "SELECT t FROM Transaction t WHERE t.transid = :transid"),
    @NamedQuery(name = "Transaction.findByPaymenttype", query = "SELECT t FROM Transaction t WHERE t.paymenttype = :paymenttype"),
    @NamedQuery(name = "Transaction.findByPrice", query = "SELECT t FROM Transaction t WHERE t.price = :price"),
    @NamedQuery(name = "Transaction.findByCardholder", query = "SELECT t FROM Transaction t WHERE t.cardholder = :cardholder"),
    @NamedQuery(name = "Transaction.findByCardname", query = "SELECT t FROM Transaction t WHERE t.cardname = :cardname"),
    @NamedQuery(name = "Transaction.findByCardno", query = "SELECT t FROM Transaction t WHERE t.cardno = :cardno"),
    @NamedQuery(name = "Transaction.findByExpirydate", query = "SELECT t FROM Transaction t WHERE t.expirydate = :expirydate")})
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "transid")
    private Integer transid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "paymenttype")
    private String paymenttype;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @Size(max = 255)
    @Column(name = "cardholder")
    private String cardholder;
    @Size(max = 255)
    @Column(name = "cardname")
    private String cardname;
    @Size(max = 255)
    @Column(name = "cardno")
    private String cardno;
    @Size(max = 255)
    @Column(name = "expirydate")
    private String expirydate;
    @JoinColumn(name = "custid", referencedColumnName = "custid")
    @ManyToOne(optional = false)
    private Customers custid;

    public Transaction() {
    }

    public Transaction(Integer transid) {
        this.transid = transid;
    }

    public Transaction(Integer transid, String paymenttype, double price) {
        this.transid = transid;
        this.paymenttype = paymenttype;
        this.price = price;
    }

    public Integer getTransid() {
        return transid;
    }

    public void setTransid(Integer transid) {
        this.transid = transid;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public Customers getCustid() {
        return custid;
    }

    public void setCustid(Customers custid) {
        this.custid = custid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transid != null ? transid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.transid == null && other.transid != null) || (this.transid != null && !this.transid.equals(other.transid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Transaction[ transid=" + transid + " ]";
    }
    
}
