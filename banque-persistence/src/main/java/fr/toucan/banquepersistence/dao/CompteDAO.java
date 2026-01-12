package fr.toucan.banquepersistence.dao;

import fr.toucan.banquepersistence.entities.Client;
import fr.toucan.banquepersistence.entities.Compte;
import fr.toucan.banquepersistence.util.BanqueException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CompteDAO {
    @Transactional(propagation= Propagation.REQUIRED, rollbackFor =  BanqueException.class)
    public abstract void ajouterCompte(Compte compte) throws BanqueException;

    @Transactional(propagation= Propagation.MANDATORY)
    public abstract Compte rechercherCompteParNumero(long numero) throws BanqueException;

    @Transactional(propagation= Propagation.MANDATORY)
    public abstract List<Compte> rechercherComptesClient(Client client) throws BanqueException;

    @Transactional(propagation= Propagation.REQUIRED, rollbackFor =  BanqueException.class)
    public abstract void modifierCompte(Compte compte) throws BanqueException;
}
