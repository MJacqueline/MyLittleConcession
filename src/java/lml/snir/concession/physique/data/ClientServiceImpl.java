/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.concession.physique.data;

import java.util.List;
import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.snir.concession.metier.entity.Client;
import lml.snir.concession.metier.entity.Commande;
import lml.snir.concession.physique.ClientService;

/**
 *
 * @author saturne
 */
public class ClientServiceImpl extends AbstracCrudServiceJPA<Client> implements ClientService{

    public ClientServiceImpl(){
        super("MyLittleConcessionPU");
    }
    
    @Override
    public Client getById(long id) throws Exception {
        Client c = null;
        try {
            this.open();
            c = this.em.find(Client.class, id);
        } finally {
            this.close();
        }
        return c;
    }

    @Override
    public List<Client> getByNom(String nom, int begin, int size) throws Exception {
        List<Client> clients = null;
        try{
            this.open();
            Query query = em.createQuery("select c from Client c where c.nom = :fnom order by c.nom asc");
            query.setParameter("fnom",nom);
            query.setFirstResult(begin);
            query.setMaxResults(size);
            clients = query.getResultList();
        }finally{
            this.close();
        }
        return clients;
    }

    @Override
    public List<Client> getByPrenom(String prenom, int begin, int size) throws Exception {
        List<Client> clients = null;
        try{
            this.open();
            Query query = em.createQuery("select c from Client c where c.prenom = :fprenom order by c.nom asc");
            query.setParameter("fprenom",prenom);
            query.setFirstResult(begin);
            query.setMaxResults(size);
            clients = query.getResultList();
        }finally{
            this.close();
        }
        return clients;
    }

    @Override
    public int getCountByPrenom(String prenom) throws Exception {
        Long count = -1l;
        try{
            this.open();
            Query query = em.createQuery("select count(c) from Client c where c.prenom = :fprenom");
            query.setParameter("fprenom", prenom);
            count = ((Long) query.getSingleResult()).longValue();
        }finally{
            this.close();
        }
        return count.intValue();
    }

    @Override
    public Client getByAdresse(String adresse) throws Exception {
        Client c = null;
        try{
            this.open();
            Query query = em.createQuery("select c from Client c where c.adresse = :fadresse order by c.nom asc");
            query.setParameter("fadresse",adresse);
            c = (Client) query.getSingleResult();
        }finally{
            this.close();
        }
        return c;
    }

    @Override
    public Client getByLogin(String login) throws Exception {
        Client c = null;
        try{
            this.open();
            Query query = em.createQuery("select c from Client c where c.login = :flogin order by c.nom asc");
            query.setParameter("flogin",login);
            c = (Client) query.getSingleResult();
        }finally{
            this.close();
        }
        return c;
    }

    @Override
    public List<Client> getByAge(int age, int begin, int size) throws Exception {
        List<Client> clients = null;
        try{
            this.open();
            Query query = em.createQuery("select c from Client c where c.age = :fage order by c.nom asc");
            query.setParameter("fage",age);
            query.setFirstResult(begin);
            query.setMaxResults(size);
            clients = query.getResultList();
        }finally{
            this.close();
        }
        return clients;
    }

    @Override
    public int getCountByAge(int age) throws Exception {
        Long count = -1l;
        try{
            this.open();
            Query query = em.createQuery("select count(c) from Client c where c.age = :fage");
            query.setParameter("fage", age);
            count = ((Long) query.getSingleResult()).longValue();
        }finally{
            this.close();
        }
        return count.intValue();
    }

    @Override
    public List<Client> getAdiministrator(int begin, int size) throws Exception {
        List<Client> clients = null;
        try{
            this.open();
            Query query = em.createQuery("select c from Client c where c.administrator = :faadministrator order by c.nom asc");
            query.setParameter("fadministrator",true);
            query.setFirstResult(begin);
            query.setMaxResults(size);
            clients = query.getResultList();
        }finally{
            this.close();
        }
        return clients;
    }

    @Override
    public int getCountByAdministrator() throws Exception {
        Long count = -1l;
        try{
            this.open();
            Query query = em.createQuery("select count(c) from Client c where c.age = :fage");
            query.setParameter("fadministrator", true);
            count = ((Long) query.getSingleResult()).longValue();
        }finally{
            this.close();
        }
        return count.intValue();
    }

    @Override
    public Client getByCommande(Commande commande) throws Exception {
        Client c = null;
        try{
            this.open();
            Query query = em.createQuery("select c from Client c where c.commandes = :fcommandes order by c.nom asc");
            query.setParameter("fcommandes",commande);
            c = (Client) query.getSingleResult();
        }finally{
            this.close();
        }
        return c;
    }

    @Override
    public List<String> getLogins() throws Exception {
        List<String> logins = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT c.login from Client c order by c.login ASC");
            logins = query.getResultList();
        } finally {
            this.close();
        }
        return logins;
    }

    @Override
    public List<String> getPasswords() throws Exception {
        List<String> passwords = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT c.mdp from Client c order by c.mdp ASC");
            passwords = query.getResultList();
        } finally {
            this.close();
        }
        return passwords;
    }

    @Override
    public List<String> getPrenoms() throws Exception {
        List<String> prenom = null;
        try {
            this.open();
            Query query = em.createQuery("select DISTINCT(c.prenom) from Client c order by c.prenom asc");
            prenom = query.getResultList();
        } finally {
            this.close();
        }
        return prenom;
    }

    @Override
    public List<String> getNoms() throws Exception {
        List<String> nom = null;
        try {
            this.open();
            Query query = em.createQuery("select DISTINCT(c.nom) from Client c order by c.nom asc");
            nom = query.getResultList();
        } finally {
            this.close();
        }
        return nom;
    }

    @Override
    public int getCountByNom(String nom) throws Exception {
        Long count = -1l;
        try{
            this.open();
            Query query = em.createQuery("select count(c) from Client c where c.nom = :fnom");
            query.setParameter("fnom", nom);
            count = ((Long) query.getSingleResult()).longValue();
        }finally{
            this.close();
        }
        return count.intValue();
    }
    
}
