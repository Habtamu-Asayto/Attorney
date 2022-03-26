/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proccess;

import com.database.AttorneyAccount;
import com.database.AttorneyAccountFacade;
import com.database.JavaUtilsEncryption;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author habtamu
 */
@ManagedBean(name="loginController")
@SessionScoped
public class Logincontroller {

    @EJB
    private AttorneyAccountFacade attorneyAccountFacade;
    
    private String email;    
    private String password;
    private AttorneyAccount attorneyAccount;
    
    
    private String message;
    private boolean isLoggedin=false;
    private AttorneyAccount loggeduser;
    
    private static final long serialVersionUID = 1L;
    FacesContext context = FacesContext.getCurrentInstance();
    HttpSession session
            = (HttpSession) context.getExternalContext().getSession(false);

    public boolean isIsLoggedin() {
        return isLoggedin;
    }

    public void setIsLoggedin(boolean isLoggedin) {
        this.isLoggedin = isLoggedin;
    }

    public AttorneyAccount getAttorneyAccount() {
        return attorneyAccount;
    }

    public void setAttorneyAccount(AttorneyAccount attorneyAccount) {
        this.attorneyAccount = attorneyAccount;
    }
    
    public void verifyLogin(){
        if(!this.isLoggedin){
            doRedirect("/Attorney/faces/index.xhtml");
        }
    }
    
    public void logout(){
        final Logger logger = Logger.getLogger(Logincontroller.class);        
        logger.debug(this.loggeduser.getName() + " " + this.loggeduser.getMiddleName() + " is Logged out");
        this.isLoggedin = false;
        doRedirect("/Attorney/faces/index.xhtml");
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    } 
    public AttorneyAccount getLoggeduser() {
        return loggeduser;
    }

    public void setLoggeduser(AttorneyAccount loggeduser) {
        this.loggeduser = loggeduser;
    }

    public AttorneyAccountFacade getAttorneyAccountFacade() {
        return attorneyAccountFacade;
    }

    public void setAttorneyAccountFacade(AttorneyAccountFacade attorneyAccountFacade) {
        this.attorneyAccountFacade = attorneyAccountFacade;
    }
    
    
    public void loginProccess(){
        
       final Logger logger = Logger.getLogger(Logincontroller.class);        
       logger.debug(this.email + " is Trying to Login");
              
        int x = 0; 
        System.out.println("Trying to Login ");
        String uname = null;
        int id ;
        AttorneyAccount u=null;
        
         String pwdd = JavaUtilsEncryption.generateSaltedHash(this.password.trim(), password.trim());
       //logged= (Users) em.createQuery("SELECT u FROM Users u WHERE lower(u.username) = :username and u.password = :password"  ).setParameter("username",lowerusername).setParameter("password",pwd).getSingleResult();
           try {
               
             u=  getAttorneyAccountFacade().findLogedUser(email.trim(), pwdd);
             System.out.println("The User is Logged with after facade call ");
             if (u != null) { 
                 System.out.println("The User is Logged with ");
                 x = 1;
                 this.loggeduser=u;
                 uname = u.getName() + " "+ u.getMiddleName();
                 id=u.getId(); 
                    if (x == 1) { 
                        logger.debug(uname +" is Logged using "+this.email); 
                        session.setAttribute("name", uname);
                        this.isLoggedin=true;
                        doRedirect("dashboard.xhtml"); 
                    }
                    }else{
                           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Please Enter Valid username and password"));
                    }

               } catch (Exception e) {
                    if(e.getMessage() == null)
                    {
                         System.out.println("There is Some Error "); 
                         FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage());
                         FacesContext.getCurrentInstance().addMessage(null, message);
                    }

                    else{
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "There is Error in the login"+e.getMessage());
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    }
                  }
    }
    
    public void doRedirect(String url){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error","zerror");
            FacesContext.getCurrentInstance().addMessage(null, message);  
        } catch (IOException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);  
        }
     }

}
