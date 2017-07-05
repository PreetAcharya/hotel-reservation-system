package ejb;

import ejb.Reservations;
import ejb.Roomstatus;
import ejb.Roomtypes;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-22T23:51:21")
@StaticMetamodel(Rooms.class)
public class Rooms_ { 

    public static volatile CollectionAttribute<Rooms, Reservations> reservationsCollection;
    public static volatile SingularAttribute<Rooms, Roomstatus> statusId;
    public static volatile SingularAttribute<Rooms, Integer> roomnumber;
    public static volatile SingularAttribute<Rooms, Integer> floor;
    public static volatile SingularAttribute<Rooms, Integer> roomid;
    public static volatile SingularAttribute<Rooms, Roomtypes> roomTypeId;

}