/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.concession.client;

import java.sql.Date;
import lml.snir.concession.metier.MetierFactory;
import lml.snir.concession.metier.entity.Client;
import lml.snir.concession.metier.entity.Commande;
import lml.snir.concession.metier.entity.Voiture;
import lml.snir.concession.physique.ClientService;
import lml.snir.concession.physique.CommandeService;
import lml.snir.concession.physique.VoitureService;

/**
 *
 * @author saturne
 */
public class MyLittleConcession {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        ClientService clientSrv = MetierFactory.getClientService();
        CommandeService commandeSrv = MetierFactory.getCommandeService();
        VoitureService voitureSrv = MetierFactory.getVoitureService();
        
        Client cl = new Client();
        Commande c = new Commande();
        Voiture v = new Voiture();
        
        cl.setAdministrator(true);
        cl.setAdresse("Evreux");
        cl.setAge(25);
        cl.setLogin("Artanis");
        cl.setMdp("Protoss");
        cl.setNom("Hélicoptère");
        cl.setPrenom("Albert");
        
        System.out.println(cl);
        cl= clientSrv.add(cl);
        
        v.setCategorie("Grand Tourisme");
        v.setMarque("Aston Martin");
        v.setModele("DB11");
        v.setMotorisation("5,2 V12 biturbo");
        v.setPrix(211364);
        v.setPuissance(639);
        v.setBackdrop("http://img.over-blog-kiwi.com/1/40/60/51/20170916/ob_6341b9_aston-martin-db11-v8-2018-101.jpg");
        
        c.setDates(Date.valueOf("2018-10-09"));
        c.setClient(cl);
        
        System.out.println(c);
        c = commandeSrv.add(c);
             
        System.out.println(v);
        v = voitureSrv.add(v);
        
    }
    
}
