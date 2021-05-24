/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.concession.client;

import java.time.Instant;
import java.util.ArrayList;
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
import lml.snir.concession.physique.CommandeService;

/**
 *
 * @author saturne
 */
@ManagedBean
@SessionScoped
public class AdminBeanCommande {

    private Date date;
    private Map<Date, Date> dates;
    private Commande commande;
    private Commande selectedCommande;
    private CommandeService commandeSrv;
    private List<Commande> commandes;
    private int count;
    private Client selectedClient;
    private Voiture selectedVoiture;

    /**
     * Creates a new instance of AdminBeanCommande
     */
    @PostConstruct
    public void init() {
        try {
            this.date = new Date();
            this.setCommande(new Commande());
            this.selectedCommande = new Commande();
            this.commandeSrv = MetierFactory.getCommandeService();
            this.setCommandes(this.commandeSrv.getAll(0, this.getCount()));
            this.selectedClient = new Client();
            this.selectedVoiture = new Voiture();
            List<Date> datas = this.getCommandeSrv().getDates();
            this.setDates(new HashMap<Date, Date>());
            for (Date st : datas) {
                if (st != null) {
                    this.getDates().put(st, st);
                }
            }

            System.out.println("-------------------------INIT OK---------------------------");
        } catch (Exception ex) {
            // do nothing for this moment
            System.err.println("BOBO");
        }
    }

    public void onDateChange() {
        if (this.getDate() != null) {
            try {
                this.setCount(this.getCommandeSrv().getCountByDates(this.getDate()));
                this.setCommandes(this.getCommandeSrv().getByDates(this.getDate(), 0, this.getCount()));
            } catch (Exception ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.setCount((int) this.getCommandeSrv().getCount());
                this.setCommandes(this.getCommandeSrv().getAll(0, this.getCount()));
            } catch (Exception ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void ajoutCommande() {
        FacesMessage message = null;
        
        try {
            if (this.selectedClient != null) {

                if (this.selectedVoiture != null) {

                    this.commande.setClient(selectedClient);
                    this.commande.setVoitures(selectedVoiture);
                    this.commande.setDates(Date.from(Instant.now()));
                    this.commandeSrv.add(this.commande);
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Commande", "Ajouter");

                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Voiture", "Non Sélectionner");
                }

            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Client", "Non Sélectionner");
            }

        } catch (Exception ex) {
            Logger.getLogger(AdminBeanCommande.class.getName()).log(Level.SEVERE, null, ex);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aucun Client et Voiture", "Sélectionner");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void supprCommande(){
        FacesMessage message = null;
        try{
            this.commandeSrv.remove(this.selectedCommande);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Commande", "Supprimer");
        }catch (Exception ex) {
            Logger.getLogger(AdminBeanVoiture.class.getName()).log(Level.SEVERE, null, ex);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Commande", "Non Supprimer");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void modifCommande(){
        FacesMessage message = null;
        try{
            this.commande.setClient(selectedClient);
            this.commande.setVoitures(selectedVoiture);
            this.commandeSrv.update(this.commande);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Commande", "Modifier");
        }catch (Exception ex) {
            Logger.getLogger(AdminBeanVoiture.class.getName()).log(Level.SEVERE, null, ex);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Commande", "Non Modifier");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the dates
     */
    public Map<Date, Date> getDates() {
        return dates;
    }

    /**
     * @param dates the dates to set
     */
    public void setDates(Map<Date, Date> dates) {
        this.dates = dates;
    }

    /**
     * @return the selectedCommande
     */
    public Commande getSelectedCommande() {
        return selectedCommande;
    }

    /**
     * @param selectedCommande the selectedCommande to set
     */
    public void setSelectedCommande(Commande selectedCommande) {
        this.selectedCommande = selectedCommande;
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
     * @return the commandes
     */
    public List<Commande> getCommandes() {
        return commandes;
    }

    /**
     * @param commandes the commandes to set
     */
    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
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
     * @return the selectedClient
     */
    public Client getSelectedClient() {
        return selectedClient;
    }

    /**
     * @param selectedClient the selectedClient to set
     */
    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
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

}
