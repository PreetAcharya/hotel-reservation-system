package ejb;

import ejb.Customers;
import ejb.Rooms;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-22T23:51:21")
@StaticMetamodel(Reservations.class)
public class Reservations_ { 

    public static volatile SingularAttribute<Reservations, String> specialrequest;
    public static volatile SingularAttribute<Reservations, Date> enddate;
    public static volatile SingularAttribute<Reservations, Customers> custid;
    public static volatile SingularAttribute<Reservations, Integer> reservid;
    public static volatile SingularAttribute<Reservations, Date> startdate;
    public static volatile SingularAttribute<Reservations, Rooms> roomid;

}