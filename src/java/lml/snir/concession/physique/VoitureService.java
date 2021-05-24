/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.concession.physique;

import java.util.List;
import lml.persistence.CrudService;
import lml.snir.concession.metier.entity.Commande;
import lml.snir.concession.metier.entity.Voiture;

/**
 *
 * @author saturne
 */
public interface VoitureService extends CrudService<Voiture>{
    Voiture getById(long id) throws Exception;
    List<Voiture> getByMarque(String marque, int begin, int size) throws Exception;
    int getCountByMarque(String marque) throws Exception;
    Voiture getByModele(String modele, int begin, int size) throws Exception;
    List<Voiture> getByCategorie(String categorie, int begin, int size) throws Exception;
    int getCountByCategorie(String categorie) throws Exception;
    List<Voiture> getByPrix(int prix, int begin, int size) throws Exception;
    int getCountByPrix(int prix) throws Exception;
    List<Voiture> getByPuissance(int puissance, int begin, int size) throws Exception;
    int getCountByPuissance(int puissance) throws Exception;
    List<Voiture> getByMotorisation(String motorisation, int begin, int size) throws Exception;
    int getCountbyMotorisation(String motorisation) throws Exception;
    List<Voiture> getByCommande(Commande c, int begin, int size) throws Exception;
    int getCountbyCommande(Commande c) throws Exception;
    List<String>getCategorie()throws Exception;
    List<Voiture> getByAnnee(int annee, int begin, int size) throws Exception;
    int getCountbyAnnee(int annee) throws Exception;
    List<Integer> getAnnee() throws Exception;
    List<String> getMarque() throws Exception;
    List<String> getModele() throws Exception;
    
}
