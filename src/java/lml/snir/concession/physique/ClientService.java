/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.concession.physique;

import java.util.List;
import lml.persistence.CrudService;
import lml.snir.concession.metier.entity.Client;
import lml.snir.concession.metier.entity.Commande;

/**
 *
 * @author saturne
 */
public interface ClientService extends CrudService<Client>{
    Client getById(long id) throws Exception;
    List<Client> getByNom(String nom, int begin, int size) throws Exception;
    List<String> getLogins() throws Exception;
    List<String> getPasswords() throws Exception;
    List<Client> getByPrenom(String prenom, int begin, int size) throws Exception;
    int getCountByPrenom(String prenom) throws Exception;
    int getCountByNom(String nom) throws Exception;
    Client getByAdresse(String adresse) throws Exception;
    Client getByLogin(String login) throws Exception;
    List<Client> getByAge(int age, int begin, int size) throws Exception;
    int getCountByAge(int age) throws Exception;
    List<Client> getAdiministrator(int begin, int size) throws Exception;
    int getCountByAdministrator() throws Exception;
    Client getByCommande(Commande commande) throws Exception;
    List<String> getPrenoms() throws Exception;
    List<String> getNoms() throws Exception;
}
