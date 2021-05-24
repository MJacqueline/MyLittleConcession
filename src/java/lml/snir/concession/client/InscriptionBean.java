/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.concession.client;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import lml.snir.concession.metier.MetierFactory;
import lml.snir.concession.metier.entity.Client;
import lml.snir.concession.physique.ClientService;

/**
 *
 * @author saturne
 */
@ManagedBean
@RequestScoped
public class InscriptionBean {

    private List<String> logins;
    private ClientService clientSrv;
    private String nom;
    private String prenom;
    private String adresse;
    private String login;
    private String mdp;
    private int age;
    private Client client;

    /**
     * Creates a new instance of inscription
     */
    @PostConstruct
    public void init() {
        try {
            this.clientSrv = MetierFactory.getClientService();
            this.setLogins(this.clientSrv.getLogins());
            System.out.println("-------------------------INIT OK---------------------------");
        } catch (Exception ex) {
            // do nothing for this moment
            System.err.println("BOBO");
        }
    }

    public void onClick() {
        int i = 0;
        FacesMessage message = null;
        boolean creation = false;

        this.client = new Client();
        this.client.setAdministrator(false);
        this.client.setAdresse(getAdresse());
        this.client.setAge(getAge());
        this.client.setLogin(getLogin());
        this.client.setMdp(getMdp());
        this.client.setNom(getNom());
        this.client.setPrenom(getPrenom());

        try {
            for (i = 0; i < this.clientSrv.getCount(); i++) {
                if (login != null && !login.equals(logins.get(i))) {
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Utilisateur créer", this.login);
                    creation = true;
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur", "Nom d'utilisateur déjà attribué");
                    creation = false;
                }
            }
            if (creation) {
                this.clientSrv.add(client);
            }
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the mdp
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * @param mdp the mdp to set
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
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

}
