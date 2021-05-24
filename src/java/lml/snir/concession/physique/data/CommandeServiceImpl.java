/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.concession.physique.data;

import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import lml.snir.concession.metier.entity.Client;
import lml.snir.concession.metier.entity.Commande;
import lml.snir.concession.metier.entity.Voiture;
import lml.snir.concession.physique.CommandeService;
import lml.persistence.jpa.AbstracCrudServiceJPA;

/**
 *
 * @author saturne
 */
public class CommandeServiceImpl extends AbstracCrudServiceJPA<Commande> implements CommandeService{

    public CommandeServiceImpl(){
        super("MyLittleConcessionPU");
    }

    

    @Override
    public Commande getById(long id) throws Exception {
        Commande c = null;
        try {
            this.open();
            c = this.em.find(Commande.class, id);
        } finally {
            this.close();
        }
        return c;
    }


    @Override
    public List<Commande> getByDates(Date dates, int begin ,int size) throws Exception {
        List<Commande> commandes = null;
        try {
            this.open();
            Query query = em.createQuery("select c from Commande c where c.dates = :fdates order by c.client asc");
            query.setParameter("fdates", dates);
            query.setFirstResult(begin);
            query.setMaxResults(size);
            commandes = query.getResultList();
        } finally {
            this.close();
        }
        return commandes;
    }

    @Override
    public List<Commande> getByClient(Client c, int begin, int size) throws Exception {
        List<Commande> commandes = null;
        try {
            this.open();
            Query query = em.createQuery("select c from Commande c where c.client = :fclient order by c.dates asc");
            query.setParameter("fclient", c);
            query.setFirstResult(begin);
            query.setMaxResults(size);
            commandes = query.getResultList();
        } finally {
            this.close();
        }
        return commandes;
    }

    @Override
    public int getCountByDates(Date dates) throws Exception {
        Long count = -1l;
        try {
            this.open();
            Query query = em.createQuery("select count(c) from Commande c where c.dates = :fdates");
            query.setParameter("fdates", dates);
            count = ((Long) query.getSingleResult()).longValue();
        } finally {
            this.close();
        }
        return count.intValue();
    }

    @Override
    public List<Commande> getByVoiture(Voiture v, int begin, int size) throws Exception {
        List<Commande> commandes = null;
        try {
            this.open();
            Query query = em.createQuery("select c from Commande c where c.voitures = :fvoiture order by c.dates asc");
            query.setParameter("fvoitures", v);
            query.setFirstResult(begin);
            query.setMaxResults(size);
            commandes = query.getResultList();
        } finally {
            this.close();
        }
        return commandes;
    }

    @Override
    public int getCountByVoiture(Voiture v) throws Exception {
        Long count = -1l;
        try {
            this.open();
            Query query = em.createQuery("select count(c) from Commande c where c.voitures = :fvoitures");
            query.setParameter("fvoitures", v);
            count = ((Long) query.getSingleResult()).longValue();
        } finally {
            this.close();
        }
        return count.intValue();
    }

    @Override
    public List<Date> getDates() throws Exception {
        List<Date> date = null;
        try {
            this.open();
            Query query = em.createQuery("select DISTINCT(c.dates) from Commande c order by c.dates asc");
            date = query.getResultList();
        } finally {
            this.close();
        }
        return date;
    }
}
