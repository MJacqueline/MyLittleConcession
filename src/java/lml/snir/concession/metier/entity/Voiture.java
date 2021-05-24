/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.concession.metier.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author saturne
 */
@Entity
public class Voiture implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marque;
    private String modele;
    private String categorie;
    private int prix;
    private int puissance;
    private String motorisation;
    private String backdrop;
    private int anneeDeSortie;

    public Voiture(){}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voiture)) {
            return false;
        }
        Voiture other = (Voiture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lml.snir.concession.metier.entity.Voiture[ id=" + id + " ]";
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
     * @return the modele
     */
    public String getModele() {
        return modele;
    }

    /**
     * @param modele the modele to set
     */
    public void setModele(String modele) {
        this.modele = modele;
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
     * @return the prix
     */
    public int getPrix() {
        return prix;
    }

    /**
     * @param prix the prix to set
     */
    public void setPrix(int prix) {
        this.prix = prix;
    }

    /**
     * @return the puissance
     */
    public int getPuissance() {
        return puissance;
    }

    /**
     * @param puissance the puissance to set
     */
    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    /**
     * @return the motorisation
     */
    public String getMotorisation() {
        return motorisation;
    }

    /**
     * @param motorisation the motorisation to set
     */
    public void setMotorisation(String motorisation) {
        this.motorisation = motorisation;
    }

    /**
     * @return the backdrop
     */
    public String getBackdrop() {
        return backdrop;
    }

    /**
     * @param backdrop the backdrop to set
     */
    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    /**
     * @return the anneeDeSortie
     */
    public int getAnneeDeSortie() {
        return anneeDeSortie;
    }

    /**
     * @param anneeDeSortie the anneeDeSortie to set
     */
    public void setAnneeDeSortie(int anneeDeSortie) {
        this.anneeDeSortie = anneeDeSortie;
    }
    
}
