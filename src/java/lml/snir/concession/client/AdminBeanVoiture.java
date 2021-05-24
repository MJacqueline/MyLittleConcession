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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import lml.snir.concession.metier.MetierFactory;
import lml.snir.concession.metier.entity.Voiture;
import lml.snir.concession.physique.VoitureService;

/**
 *
 * @author saturne
 */
@ManagedBean
@SessionScoped
public class AdminBeanVoiture {

    private Voiture selectedVoiture;
    private Voiture voiture;
    private List<String> modeles;
    private VoitureService voitureSrv;
    
    /**
     * Creates a new instance of AdminPage
     */
    @PostConstruct
    public void init() {
        try {
            this.voiture = new Voiture();
            this.selectedVoiture = new Voiture();
            this.setVoitureSrv(MetierFactory.getVoitureService());
            this.setModeles(this.voitureSrv.getModele());
            
            System.out.println("-------------------------INIT OK---------------------------");
        } catch (Exception ex) {
            // do nothing for this moment
            System.err.println("BOBO");
        }
    }

    public void ajoutVoiture() {
        int i = 0;
        int count = 0;
        String modele;
        FacesMessage message = null;
        
        
        try {
            modele = this.voiture.getModele();
            count = (int) this.voitureSrv.getCount();
            count = count - 1; 
            while((modele.compareToIgnoreCase(this.modeles.get(i)) != 0) && (i < count)) {
                i++;
            }
             if(i == count){
                 this.voitureSrv.add(this.voiture);
                 message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Voiture", "Ajouter");
             }else{
                 message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Voiture", "Déjà Enregistrer");
             }
            
        } catch (Exception ex) {
            Logger.getLogger(AdminBeanVoiture.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void modifVoiture(){
        FacesMessage message = null;
        try{
            this.voitureSrv.update(this.voiture);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Voiture", "Modifier");
        }catch (Exception ex) {
            Logger.getLogger(AdminBeanVoiture.class.getName()).log(Level.SEVERE, null, ex);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Voiture", "Non Modifier");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void supprVoiture(){
        FacesMessage message = null;
        try{
            this.voitureSrv.remove(this.selectedVoiture);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Voiture", "Supprimer");
        }catch (Exception ex) {
            Logger.getLogger(AdminBeanVoiture.class.getName()).log(Level.SEVERE, null, ex);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Voiture", "Non Supprimer");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    /**
     * @return the voiture
     */
    public Voiture getVoiture() {
        return voiture;
    }

    /**
     * @param voiture the voiture to set
     */
    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
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
     * @return the modeles
     */
    public List<String> getModeles() {
        return modeles;
    }

    /**
     * @param modeles the modeles to set
     */
    public void setModeles(List<String> modeles) {
        this.modeles = modeles;
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
    
}
