/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paradisehotel_admin;

import console.Login;
import javax.ejb.EJB;
import session.AdminSessionBeanRemote;

/**
 *
 * @author qianfeng
 */
public class Main {

    @EJB
    private static AdminSessionBeanRemote adminSessionBean;

    public static AdminSessionBeanRemote getAdminSessionBean() {
        return adminSessionBean;
    }

    public static void setAdminSessionBean(AdminSessionBeanRemote adminSessionBean) {
        Main.adminSessionBean = adminSessionBean;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        new Login().setVisible(true);
        
        
        
    }
    
}
