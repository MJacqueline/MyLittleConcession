/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.concession.client;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import lml.snir.concession.metier.MetierFactory;
import lml.snir.concession.metier.entity.Client;
import lml.snir.concession.metier.entity.Commande;
import lml.snir.concession.metier.entity.Voiture;
import lml.snir.concession.physique.ClientService;
import lml.snir.concession.physique.CommandeService;
import lml.snir.concession.physique.VoitureService;

/**
 *
 * @author saturne
 */
@ManagedBean
@SessionScoped
public class UserBean {
    
    private Client connectedClient;
    private String connectedLogin;
    private String categorie;
    private int annee;
    private String marque;
    private CommandeService commandeSrv;
    private VoitureService voitureSrv;
    private Map<Integer, Integer> annees;
    private Map<String, String> categories; // you need to use a map for populate a dropdown !
    private Map<String, String> marques;
    private List<Voiture> voitures;
    private int count;
    private Commande commande;
    private Voiture selectedVoiture;
    private ClientService clientSrv;

    /**
     * Creates a new instance of UserBean
     */
    @PostConstruct
    public void init() {
        try {
            this.setClientSrv(MetierFactory.getClientService());
            this.setCommandeSrv(MetierFactory.getCommandeService());
            this.setConnectedClient(new Client());
            this.selectedVoiture = new Voiture();
            this.setCommande(new Commande());
            this.setVoitureSrv(MetierFactory.getVoitureService());
            List<String> datas = this.getVoitureSrv().getCategorie();
            List<Integer> datasAnnees = this.getVoitureSrv().getAnnee();
            List<String> datasMarques = this.getVoitureSrv().getMarque();
            this.setMarques(new HashMap<String, String>());
            this.setCategories(new HashMap<String, String>());
            this.setAnnees(new HashMap<Integer, Integer>());
            for (String st : datas) {
                if (st != null) {
                    st = st.trim();
                    this.getCategories().put(st, st);
                }
            }
            for (String st : datasMarques) {
                if (st != null) {
                    st = st.trim();
                    this.getMarques().put(st, st);
                }
            }
            for (Integer st : datasAnnees) {
                if (st != null) {
                    st = st.intValue();
                    this.getAnnees().put(st, st);
                }
            }
            this.setCount((int) this.getVoitureSrv().getCount());
            this.setVoitures(this.getVoitureSrv().getAll(0, this.getCount()));
            
            System.out.println("-------------------------INIT OK---------------------------");
        } catch (Exception ex) {
            // do nothing for this moment
            System.err.println("BOBO");
        }
    }
    
    public void onCategorieChange(){
        if (this.getCategorie() != null && !"".equals(this.categorie)) {
            try {
                this.setCount(this.getVoitureSrv().getCountByCategorie(this.getCategorie()));
                this.setVoitures(this.getVoitureSrv().getByCategorie(this.getCategorie(), 0, this.getCount()));
            } catch (Exception ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.setCount((int) this.getVoitureSrv().getCount());
                this.setVoitures(this.getVoitureSrv().getAll(0, this.getCount()));
            } catch (Exception ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void onAnneeChange(){
        if (this.getAnnee() != 0) {
            try {
                this.setCount(this.getVoitureSrv().getCountbyAnnee(this.getAnnee()));
                this.setVoitures(this.getVoitureSrv().getByAnnee(this.getAnnee(), 0, this.getCount()));
            } catch (Exception ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.setCount((int) this.getVoitureSrv().getCount());
                this.setVoitures(this.getVoitureSrv().getAll(0, this.getCount()));
            } catch (Exception ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void onMarqueChange(){
        if (this.getMarque() != null && !"".equals(this.marque)) {
            try {
                this.setCount(this.getVoitureSrv().getCountByMarque(this.getMarque()));
                this.setVoitures(this.getVoitureSrv().getByMarque(this.getMarque(), 0, this.getCount()));
            } catch (Exception ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.setCount((int) this.getVoitureSrv().getCount());
                this.setVoitures(this.getVoitureSrv().getAll(0, this.getCount()));
            } catch (Exception ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void ajoutCommande(String login) {
        FacesMessage message = null;
        
        try {
                if (this.getSelectedVoiture() != null) {
                    this.setConnectedClient(this.clientSrv.getByLogin(login));
                    this.commande.setClient(this.connectedClient);
                    this.commande.setVoitures(getSelectedVoiture());
                    this.commande.setDates(Date.from(Instant.now()));
                    this.getCommandeSrv().add(this.commande);
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Commande", "Ajouter");

                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Voiture", "Non Sélectionner");
                }

        } catch (Exception ex) {
            Logger.getLogger(AdminBeanCommande.class.getName()).log(Level.SEVERE, null, ex);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erreur", "Erreur");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    /**
     * @return the categories
     */
    public Map<String, String> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(Map<String, String> categories) {
        this.categories = categories;
    }

    /**
     * @return the voitures
     */
    public List<Voiture> getVoitures() {
        return voitures;
    }

    /**
     * @param voitures the voitures to set
     */
    public void setVoitures(List<Voiture> voitures) {
        this.voitures = voitures;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the categorie
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * @param categorie the categorie to set
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * @return the annee
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * @param annee the annee to set
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    /**
     * @return the voitureSrv
     */
    public VoitureService getVoitureSrv() {
        return voitureSrv;
    }

    /**
     * @param voitureSrv the voitureSrv to set
     */
    public void setVoitureSrv(VoitureService voitureSrv) {
        this.voitureSrv = voitureSrv;
    }

    /**
     * @return the annees
     */
    public Map<Integer, Integer> getAnnees() {
        return annees;
    }

    /**
     * @param annees the annees to set
     */
    public void setAnnees(Map<Integer, Integer> annees) {
        this.annees = annees;
    }

    /**
     * @return the marque
     */
    public String getMarque() {
        return marque;
    }

    /**
     * @param marque the marque to set
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * @return the marques
     */
    public Map<String, String> getMarques() {
        return marques;
    }

    /**
     * @param marques the marques to set
     */
    public void setMarques(Map<String, String> marques) {
        this.marques = marques;
    }

    /**
     * @return the commande
     */
    public Commande getCommande() {
        return commande;
    }

    /**
     * @param commande the commande to set
     */
    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    /**
     * @return the selectedVoiture
     */
    public Voiture getSelectedVoiture() {
        return selectedVoiture;
    }

    /**
     * @param selectedVoiture the selectedVoiture to set
     */
    public void setSelectedVoiture(Voiture selectedVoiture) {
        this.selectedVoiture = selectedVoiture;
    }

    /**
     * @return the commandeSrv
     */
    public CommandeService getCommandeSrv() {
        return commandeSrv;
    }

    /**
     * @param commandeSrv the commandeSrv to set
     */
    public void setCommandeSrv(CommandeService commandeSrv) {
        this.commandeSrv = commandeSrv;
    }

    /**
     * @return the connectedClient
     */
    public Client getConnectedClient() {
        return connectedClient;
    }

    /**
     * @param connectedClient the connectedClient to set
     */
    public void setConnectedClient(Client connectedClient) {
        this.connectedClient = connectedClient;
    }

    /**
     * @return the connectedLogin
     */
    public String getConnectedLogin() {
        return connectedLogin;
    }

    /**
     * @param connectedLogin the connectedLogin to set
     */
    public void setConnectedLogin(String connectedLogin) {
        this.connectedLogin = connectedLogin;
    }

    /**
     * @return the clientSrv
     */
    public ClientService getClientSrv() {
        return clientSrv;
    }

    /**
     * @param clientSrv the clientSrv to set
     */
    public void setClientSrv(ClientService clientSrv) {
        this.clientSrv = clientSrv;
    }
    
}
