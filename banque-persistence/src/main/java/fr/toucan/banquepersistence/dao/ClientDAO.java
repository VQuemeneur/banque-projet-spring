package fr.toucan.banquepersistence.dao;

import fr.toucan.banquepersistence.entities.Client;
import fr.toucan.banquepersistence.util.BanqueException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface ClientDAO {

    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=BanqueException.class)
    public abstract void ajouterClient(Client client) throws BanqueException;

    @Transactional(propagation=Propagation.MANDATORY)
    public abstract Client rechercherClientParId(long id) throws BanqueException;
    @Transactional(propagation=Propagation.MANDATORY)
    public abstract List<Client> rechercherTousLesClients() throws BanqueException;
}
