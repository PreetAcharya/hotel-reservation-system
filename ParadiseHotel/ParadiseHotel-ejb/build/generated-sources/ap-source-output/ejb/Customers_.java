package ejb;

import ejb.Reservations;
import ejb.Transaction;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-22T23:51:21")
@StaticMetamodel(Customers.class)
public class Customers_ { 

    public static volatile CollectionAttribute<Customers, Transaction> transactionCollection;
    public static volatile SingularAttribute<Customers, String> firstname;
    public static volatile SingularAttribute<Customers, String> password;
    public static volatile CollectionAttribute<Customers, Reservations> reservationsCollection;
    public static volatile SingularAttribute<Customers, String> phone;
    public static volatile SingularAttribute<Customers, Integer> custid;
    public static volatile SingularAttribute<Customers, String> email;
    public static volatile SingularAttribute<Customers, String> lastname;
    public static volatile SingularAttribute<Customers, String> username;

}