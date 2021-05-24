/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.concession.metier;

import lml.snir.concession.physique.ClientService;
import lml.snir.concession.physique.CommandeService;
import lml.snir.concession.physique.VoitureService;
import lml.snir.concession.physique.data.ClientServiceImpl;
import lml.snir.concession.physique.data.CommandeServiceImpl;
import lml.snir.concession.physique.data.VoitureServiceImpl;

/**
 *
 * @author saturne
 */
public class MetierFactory {
    private MetierFactory(){}
    
    private static final ClientService clientSrv = new ClientServiceImpl();
    
    public static ClientService getClientService() throws Exception{
        return MetierFactory.clientSrv;
    }
    
    private static final VoitureService voitureSrv = new VoitureServiceImpl();
    
    public static VoitureService getVoitureService() throws Exception{
        return MetierFactory.voitureSrv;
    }
    
    private static final CommandeService commandeSrv = new CommandeServiceImpl();
    
    public static CommandeService getCommandeService() throws Exception{
        return commandeSrv;
    }
}
