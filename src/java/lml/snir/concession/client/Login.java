/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.concession.client;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import lml.snir.concession.metier.MetierFactory;
import lml.snir.concession.physique.ClientService;

/**
 *
 * @author saturne
 */
@ManagedBean
@SessionScoped
public class Login implements Serializable {

    private String username;
    private String password;
    private List<String> logins;
    private List<String> passwords;
    private ClientService clientSrv;
    private String clientPrenom;

    /**
     * Creates a new instance of login
     */
    
    @PostConstruct
    public void init() {
        try {
            this.clientSrv = MetierFactory.getClientService();
            this.setLogins(this.clientSrv.getLogins());
            this.setPasswords(this.clientSrv.getPasswords());
            
            System.out.println("-------------------------INIT OK---------------------------");
        } catch (Exception ex) {
            // do nothing for this moment
            System.err.println("BOBO");
        }
    }

    public void onClick() {
        int i = 0;
        FacesMessage message = null;
        String user, pass;
        
        try {
            this.clientPrenom = this.clientSrv.getByLogin(username).getPrenom();
            for(i = 0; i < this.clientSrv.getCount(); i++){
                user = getLogins().get(i);
                pass = getPasswords().get(i);
                
                if(username != null && username.equals(getLogins().get(i))){
                    
                    if(password != null && password.equals(this.clientSrv.getByLogin(this.username).getMdp())){
                        
                        if(this.clientSrv.getByLogin(username).isAdministrator()){
                            
                        FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8084/MyLittleConcession/faces/adminPageVoiture.xhtml");
                    }else{
                        FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8084/MyLittleConcession/faces/userPage.xhtml");
                    }
                    }else{
                        message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Mot de Passe", "Invalid");
                    }
                    
                }else{
                    message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login", "Invalid");
                }
                
            }
        } catch (Exception ex) {            
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the clientLogin
     */
    public String getClientPrenom() {
        return clientPrenom;
    }

    /**
     * @param clientPrenom the clientLogin to set
     */
    public void setClientPrenom(String clientPrenom) {
        this.clientPrenom = clientPrenom;
    }

    /**
     * @return the logins
     */
    public List<String> getLogins() {
        return logins;
    }

    /**
     * @param logins the logins to set
     */
    public void setLogins(List<String> logins) {
        this.logins = logins;
    }

    /**
     * @return the passwords
     */
    public List<String> getPasswords() {
        return passwords;
    }

    /**
     * @param passwords the passwords to set
     */
    public void setPasswords(List<String> passwords) {
        this.passwords = passwords;
    }

}
