/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.concession.client;

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
import lml.snir.concession.physique.ClientService;

/**
 *
 * @author saturne
 */
@ManagedBean
@SessionScoped
public class AdminBeanClient {

    private String prenom;
    private String nom;
    private Client client;
    private ClientService clientSrv;
    private Map<String, String> prenoms; // you need to use a map for populate a dropdown !
    private Map<String, String> noms;
    private List<Client> clients;
    private List<String> logins;
    private int count;
    private Client selectedClient;
    
    /**
     * Creates a new instance of AdminBeanClient
     */
    @PostConstruct
    public void init() {
        try {
            
            
            this.selectedClient = new Client();
            this.setClientSrv(MetierFactory.getClientService());
            List<String> datas = this.getClientSrv().getNoms();
            List<String> datasPrenoms = this.getClientSrv().getPrenoms();
            this.setNoms(new HashMap<String, String>());
            this.setPrenoms(new HashMap<String, String>());
            for (String st : datas) {
                if (st != null) {
                    st = st.trim();
                    this.getNoms().put(st, st);
                }
            }
            for (String st : datasPrenoms) {
                if (st != null) {
                    st = st.trim();
                    this.getPrenoms().put(st, st);
                }
            }
            this.setCount((int) this.getClientSrv().getCount());
            this.setClients(this.getClientSrv().getAll(0, this.getCount()));
            this.setLogins(this.getClientSrv().getLogins());
            this.client = new Client();
            
            System.out.println("-------------------------INIT OK---------------------------");
        } catch (Exception ex) {
            // do nothing for this moment
            System.err.println("BOBO");
        }
    }

    public void onNomChange(){
        if (this.getNom()!= null && !"".equals(this.nom)) {
            try {
                this.setCount(this.getClientSrv().getCountByNom(this.getNom()));
                this.setClients(this.getClientSrv().getByNom(this.getNom(), 0, this.getCount()));
            } catch (Exception ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.setCount((int) this.getClientSrv().getCount());
                this.setClients(this.getClientSrv().getAll(0, this.getCount()));
            } catch (Exception ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void onPrenomChange(){
        if (this.getPrenom()!= null && !"".equals(this.prenom)) {
            try {
                this.setCount(this.getClientSrv().getCountByPrenom(this.getPrenom()));
                this.setClients(this.getClientSrv().getByPrenom(this.getPrenom(), 0, this.getCount()));
            } catch (Exception ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.setCount((int) this.getClientSrv().getCount());
                this.setClients(this.getClientSrv().getAll(0, this.getCount()));
            } catch (Exception ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void ajoutAdmin(){
        int i = 0;
        int count = 0;
        String login;
        FacesMessage message = null;
        
        
        try {
            this.client.setAdministrator(true);
            login = this.client.getLogin();
            count = (int) this.clientSrv.getCount();
            count = count - 1; 
            while((login.compareToIgnoreCase(this.getLogins().get(i)) != 0) && (i < count)) {
                i++;
            }
             if(i == count){
                 this.clientSrv.add(this.client);
                 message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Client", "Ajouter");
             }else{
                 message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login", "Déjà Utiliser");
             }
            
        } catch (Exception ex) {
            Logger.getLogger(AdminBeanVoiture.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void modifClient(){
        FacesMessage message = null;
        try{
            this.clientSrv.update(this.client);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Client", "Modifier");
        }catch (Exception ex) {
            Logger.getLogger(AdminBeanVoiture.class.getName()).log(Level.SEVERE, null, ex);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Client", "Non Modifier");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void supprClient(){
        FacesMessage message = null;
        try{
            this.clientSrv.remove(this.selectedClient);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Client", "Supprimer");
        }catch (Exception ex) {
            Logger.getLogger(AdminBeanVoiture.class.getName()).log(Level.SEVERE, null, ex);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Client", "Non Supprimer");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
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

    /**
     * @return the prenoms
     */
    public Map<String, String> getPrenoms() {
        return prenoms;
    }

    /**
     * @param prenoms the prenoms to set
     */
    public void setPrenoms(Map<String, String> prenoms) {
        this.prenoms = prenoms;
    }

    /**
     * @return the noms
     */
    public Map<String, String> getNoms() {
        return noms;
    }

    /**
     * @param noms the noms to set
     */
    public void setNoms(Map<String, String> noms) {
        this.noms = noms;
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
     * @return the clients
     */
    public List<Client> getClients() {
        return clients;
    }

    /**
     * @param clients the clients to set
     */
    public void setClients(List<Client> clients) {
        this.clients = clients;
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
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the logins
     */
    public List<String> getLogins() {
        return logins;
    }

    /**
     * @param logins the logins to set
     */
    public void setLogins(List<String> logins) {
        this.logins = logins;
    }

    
}
