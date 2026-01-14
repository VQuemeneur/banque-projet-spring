package fr.toucan.banquebusiness;

import fr.toucan.banquepersistence.entities.Client;
import fr.toucan.banquepersistence.entities.Compte;
import fr.toucan.banquepersistence.util.BanqueException;

import java.util.List;

public interface BanqueService {

    public abstract Client authentifier(String indentifiant, String motDePasse) throws BanqueException;

    public abstract List<Compte> mesComptes(Long idClient) throws BanqueException;

    public abstract void virement(long numeroADebiter, long numeroACrediter, double montant) throws BanqueException;
}
