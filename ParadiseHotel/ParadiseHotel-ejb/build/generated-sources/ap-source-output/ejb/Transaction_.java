package ejb;

import ejb.Customers;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-22T23:51:21")
@StaticMetamodel(Transaction.class)
public class Transaction_ { 

    public static volatile SingularAttribute<Transaction, String> expirydate;
    public static volatile SingularAttribute<Transaction, Integer> transid;
    public static volatile SingularAttribute<Transaction, Double> price;
    public static volatile SingularAttribute<Transaction, String> cardname;
    public static volatile SingularAttribute<Transaction, Customers> custid;
    public static volatile SingularAttribute<Transaction, String> cardholder;
    public static volatile SingularAttribute<Transaction, String> cardno;
    public static volatile SingularAttribute<Transaction, String> paymenttype;

}