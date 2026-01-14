package fr.toucan.banquebusiness;

import fr.toucan.banquepersistence.dao.ClientDAO;
import fr.toucan.banquepersistence.dao.CompteDAO;
import fr.toucan.banquepersistence.entities.Client;
import fr.toucan.banquepersistence.entities.Compte;
import fr.toucan.banquepersistence.util.BanqueException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BanqueServiceImpl implements BanqueService {

    private static final Logger logger = LoggerFactory.getLogger(BanqueServiceImpl.class);

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private CompteDAO compteDAO;

    public BanqueServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public Client authentifier(String indentifiant, String motDePasse) throws BanqueException {
        long id = Long.parseLong(indentifiant);
        try {
            Client client = clientDAO.rechercherClientParId(id);
            if (client != null && client.getMotDePasse().equals(motDePasse))
                return client;

            else
                throw new BanqueException();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BanqueException("Erreur d'authentification pour l'identifiant de client : " + id);
        }
    }

    @Override
    public List<Compte> mesComptes(Long idClient) throws BanqueException {
        try {
            Client client = clientDAO.rechercherClientParId(idClient);
            return compteDAO.rechercherComptesClient(client);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BanqueException("Erreur lors de la récupération de vos comptes.");
        }
    }

    @Override
    public void virement(long numeroADebiter, long numeroACrediter, double montant) throws BanqueException {
        try {
            Compte compteADebiter = compteDAO.rechercherCompteParNumero(numeroADebiter);
            double soldeCompteADebiter = compteADebiter.getSolde();

            Compte compteACrediter = compteDAO.rechercherCompteParNumero(numeroACrediter);
            double soldeCompteACrediter = compteACrediter.getSolde();

            if (soldeCompteADebiter >= montant) {
                compteADebiter.setSolde(soldeCompteADebiter -= montant);
                compteACrediter.setSolde(soldeCompteACrediter += montant);

                compteDAO.modifierCompte(compteADebiter);
                compteDAO.modifierCompte(compteACrediter);

            } else
                throw new BanqueException("le solde du compte est insuffisant");

        } catch (Exception e) {
            e.printStackTrace();
            throw new BanqueException("Erreur lors du virement.");
        }
    }
}
