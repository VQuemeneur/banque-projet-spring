package fr.toucan.banquepersistence.dao;

import fr.toucan.banquepersistence.entities.Client;
import fr.toucan.banquepersistence.util.BanqueException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("clientDAO")
@Primary
public class DevClientDAO implements ClientDAO {

    private final Map<Long, Client> clients = new HashMap<>();
    private long nextId = 2L;

    public DevClientDAO() {
        // Création d'un client par défaut
        Client client = new Client();
        client.setId(1L);
        client.setNom("Dupont");
        client.setPrenom("Jean");
        client.setMotDePasse("1234");
        clients.put(client.getId(), client);
        System.out.println("DevClientDAO instance: " + this);
    }

    @Override
    public void ajouterClient(Client client) throws BanqueException {
        client.setId(nextId++);
        clients.put(client.getId(), client);
    }

    @Override
    public Client rechercherClientParId(long id) throws BanqueException {
        Client client = clients.get(id);
        if (client == null) {
            throw new BanqueException("Client non trouvé pour l'id : " + id);
        }
        return client;
    }

    @Override
    public List<Client> rechercherTousLesClients() throws BanqueException {
        return new ArrayList<>(clients.values());
    }
}
