/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.concession.physique.data;

import java.util.List;
import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.snir.concession.metier.entity.Commande;
import lml.snir.concession.metier.entity.Voiture;
import lml.snir.concession.physique.VoitureService;

/**
 *
 * @author saturne
 */
public class VoitureServiceImpl extends AbstracCrudServiceJPA<Voiture> implements VoitureService{

    public VoitureServiceImpl(){
        super("MyLittleConcessionPU");
    }
    
    @Override
    public Voiture getById(long id) throws Exception {
        Voiture v = null;
        try {
            this.open();
            v = this.em.find(Voiture.class, id);
        } finally {
            this.close();
        }
        return v;
    }

    @Override
    public List<Voiture> getByMarque(String marque, int begin, int size) throws Exception {
        List<Voiture> voitures = null;
        try {
            this.open();
            Query query = em.createQuery("select v from Voiture v where v.marque = :fmarque order by v.categorie asc");
            query.setParameter("fmarque", marque);
            query.setFirstResult(begin);
            query.setMaxResults(size);
            voitures = query.getResultList();
        } finally {
            this.close();
        }
        return voitures;
    }

    @Override
    public int getCountByMarque(String marque) throws Exception {
        Long count = -1l;
        try {
            this.open();
            Query query = em.createQuery("select count(v) from Voiture v where v.marque = :fmarque");
            query.setParameter("fmarque", marque);
            count = ((Long) query.getSingleResult()).longValue();
        } finally {
            this.close();
        }

        return count.intValue();
    }

    @Override
    public Voiture getByModele(String modele, int begin, int size) throws Exception {
        Voiture v = null;
        try{
            this.open();
            Query query = em.createQuery("select v from Voiture v where v.modele = :fmodele order by v.marque asc");
            query.setParameter("fmodele",modele);
            v = (Voiture) query.getSingleResult();
        }finally{
            this.close();
        }
        return v;    
    }

    @Override
    public List<Voiture> getByCategorie(String categorie, int begin, int size) throws Exception {
        List<Voiture> voitures = null;
        try {
            this.open();
            Query query = em.createQuery("select v from Voiture v where v.categorie = :fcategorie order by v.marque asc");
            query.setParameter("fcategorie", categorie);
            query.setFirstResult(begin);
            query.setMaxResults(size);
            voitures = query.getResultList();
        } finally {
            this.close();
        }
        return voitures;
    }

    @Override
    public int getCountByCategorie(String categorie) throws Exception {
        Long count = -1l;
        try {
            this.open();
            Query query = em.createQuery("select count(v) from Voiture v where v.categorie = :fcategorie");
            query.setParameter("fcategorie", categorie);
            count = ((Long) query.getSingleResult()).longValue();
        } finally {
            this.close();
        }

        return count.intValue();
    }

    @Override
    public List<Voiture> getByPrix(int prix, int begin, int size) throws Exception {
        List<Voiture> voitures = null;
        try {
            this.open();
            Query query = em.createQuery("select v from Voiture v where v.prix = :fprix order by v.marque asc");
            query.setParameter("fprix", prix);
            query.setFirstResult(begin);
            query.setMaxResults(size);
            voitures = query.getResultList();
        } finally {
            this.close();
        }
        return voitures;
    }

    @Override
    public int getCountByPrix(int prix) throws Exception {
        Long count = -1l;
        try {
            this.open();
            Query query = em.createQuery("select count(v) from Voiture v where v.prix = :fprix");
            query.setParameter("fprix", prix);
            count = ((Long) query.getSingleResult()).longValue();
        } finally {
            this.close();
        }

        return count.intValue();
    }

    @Override
    public List<Voiture> getByPuissance(int puissance, int begin, int size) throws Exception {
        List<Voiture> voitures = null;
        try {
            this.open();
            Query query = em.createQuery("select v from Voiture v where v.puissance = :fpuissance order by v.marque asc");
            query.setParameter("fpuissance", puissance);
            query.setFirstResult(begin);
            query.setMaxResults(size);
            voitures = query.getResultList();
        } finally {
            this.close();
        }
        return voitures;
    }

    @Override
    public int getCountByPuissance(int puissance) throws Exception {
        Long count = -1l;
        try {
            this.open();
            Query query = em.createQuery("select count(v) from Voiture v where v.puissance = :fpuissance");
            query.setParameter("fpuissance", puissance);
            count = ((Long) query.getSingleResult()).longValue();
        } finally {
            this.close();
        }

        return count.intValue();
    }

    @Override
    public List<Voiture> getByMotorisation(String motorisation, int begin, int size) throws Exception {
        List<Voiture> voitures = null;
        try {
            this.open();
            Query query = em.createQuery("select v from Voiture v where v.motorisation = :fmotorisation order by v.marque asc");
            query.setParameter("fmotorisation", motorisation);
            query.setFirstResult(begin);
            query.setMaxResults(size);
            voitures = query.getResultList();
        } finally {
            this.close();
        }
        return voitures;
    }

    @Override
    public int getCountbyMotorisation(String motorisation) throws Exception {
        Long count = -1l;
        try {
            this.open();
            Query query = em.createQuery("select count(v) from Voiture v where v.motorisation = :fmotorisation");
            query.setParameter("fmotorisation", motorisation);
            count = ((Long) query.getSingleResult()).longValue();
        } finally {
            this.close();
        }

        return count.intValue();
    }

    @Override
    public List<Voiture> getByCommande(Commande c, int begin, int size) throws Exception {
        List<Voiture> voitures = null;
        try {
            this.open();
            Query query = em.createQuery("select v from Voiture v where v.commande = :fcommande order by v.marque asc");
            query.setParameter("fcommande", c);
            query.setFirstResult(begin);
            query.setMaxResults(size);
            voitures = query.getResultList();
        } finally {
            this.close();
        }
        return voitures;
    }

    @Override
    public int getCountbyCommande(Commande c) throws Exception {
        Long count = -1l;
        try {
            this.open();
            Query query = em.createQuery("select count(v) from Voiture v where v.commande = :fcommande");
            query.setParameter("fcommande", c);
            count = ((Long) query.getSingleResult()).longValue();
        } finally {
            this.close();
        }

        return count.intValue();    
    }

    @Override
    public List<String> getCategorie() throws Exception {
        List<String> categorie = null;
        try {
            this.open();
            Query query = em.createQuery("select DISTINCT(v.categorie) from Voiture v order by v.categorie asc");
            categorie = query.getResultList();
        } finally {
            this.close();
        }
        return categorie;
    }

    @Override
    public List<Voiture> getByAnnee(int annee, int begin, int size) throws Exception {
        List<Voiture> voitures = null;
        try {
            this.open();
            Query query = em.createQuery("select v from Voiture v where v.anneeDeSortie = :fanneeDeSortie order by v.marque asc");
            query.setParameter("fanneeDeSortie", annee);
            query.setFirstResult(begin);
            query.setMaxResults(size);
            voitures = query.getResultList();
        } finally {
            this.close();
        }
        return voitures;
    }

    @Override
    public int getCountbyAnnee(int annee) throws Exception {
        Long count = -1l;
        try {
            this.open();
            Query query = em.createQuery("select count(v) from Voiture v where v.anneeDeSortie = :fanneeDeSortie");
            query.setParameter("fanneeDeSortie", annee);
            count = ((Long) query.getSingleResult()).longValue();
        } finally {
            this.close();
        }

        return count.intValue();
    }

    @Override
    public List<Integer> getAnnee() throws Exception {
        List<Integer> annee = null;
        try {
            this.open();
            Query query = em.createQuery("select DISTINCT(v.anneeDeSortie) from Voiture v order by v.anneeDeSortie asc");
            annee = query.getResultList();
        } finally {
            this.close();
        }
        return annee;
    }

    @Override
    public List<String> getMarque() throws Exception {
        List<String> marque = null;
        try {
            this.open();
            Query query = em.createQuery("select DISTINCT(v.marque) from Voiture v order by v.marque asc");
            marque = query.getResultList();
        } finally {
            this.close();
        }
        return marque;
    }

    @Override
    public List<String> getModele() throws Exception {
        List<String> modele = null;
        try {
            this.open();
            Query query = em.createQuery("select DISTINCT(v.modele) from Voiture v order by v.marque asc");
            modele = query.getResultList();
        } finally {
            this.close();
        }
        return modele;
    }
    
}
