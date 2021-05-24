/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.concession.physique;

import java.util.Date;
import java.util.List;
import lml.persistence.CrudService;
import lml.snir.concession.metier.entity.Client;
import lml.snir.concession.metier.entity.Commande;
import lml.snir.concession.metier.entity.Voiture;

/**
 *
 * @author saturne
 */
public interface CommandeService extends CrudService<Commande>{
    Commande getById(long id) throws Exception;
    List<Commande> getByDates(Date dates, int begin ,int size) throws Exception;
    List<Commande> getByClient(Client c, int begin, int size) throws Exception;
    int getCountByDates(Date dates) throws Exception;
    List<Commande> getByVoiture(Voiture v, int begin, int size) throws Exception;
    int getCountByVoiture(Voiture v) throws Exception;
    List<Date> getDates() throws Exception;
}
