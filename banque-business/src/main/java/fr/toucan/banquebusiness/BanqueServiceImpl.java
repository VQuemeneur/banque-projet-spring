package fr.toucan.banquebusiness;

import fr.toucan.banquepersistence.dao.ClientDAO;
import fr.toucan.banquepersistence.dao.CompteDAO;
import fr.toucan.banquepersistence.entities.Client;
import fr.toucan.banquepersistence.entities.Compte;
import fr.toucan.banquepersistence.util.BanqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class BanqueServiceImpl implements BanqueService {


    private ClientDAO clientDAO;


    private CompteDAO compteDAO;

//    public void setClientDAO(ClientDAO clientDAO) {
//        this.clientDAO = clientDAO;
//    }
//
//    public void setCompteDAO(CompteDAO compteDAO) {
//        this.compteDAO = compteDAO;
//    }
private static final Client CLIENT_DEV;

    static {
        CLIENT_DEV = new Client();
        CLIENT_DEV.setId(1L);
        CLIENT_DEV.setNom("Dupont");
        CLIENT_DEV.setPrenom("Jean");
        CLIENT_DEV.setMotDePasse("1234");
    }

    @Override
    public Client authentifier(String indentifiant, String motDePasse) throws BanqueException {
        try {
//            Client client = clientDAO.rechercherClientParId(Long.parseLong(indentifiant));
//            if (client != null && client.getMotDePasse().equals(motDePasse))
//                return client;
            if ("1".equals(indentifiant) && "1234".equals(motDePasse)) {
                return CLIENT_DEV;
            }

            else
                throw new BanqueException();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BanqueException("Erreur d'authentification.");
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
