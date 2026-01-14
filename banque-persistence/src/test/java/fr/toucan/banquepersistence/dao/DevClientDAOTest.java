package fr.toucan.banquepersistence.dao;

import fr.toucan.banquepersistence.entities.Client;
import fr.toucan.banquepersistence.util.BanqueException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DevClientDAOTest {

    @Mock
    private ClientDAO clientDAO;

    private final Map<Long, Client> clients = new HashMap<>();

    public void testAjouterClient() {
    }
    @Test
    public void testRechercherClientParId() throws BanqueException {

        Client client = new Client();
        client.setId(1L);
        client.setMotDePasse("1234");
        client.setNom("dupont");
        clients.put(client.getId(), client);

        when(clientDAO.rechercherClientParId(anyLong())).thenReturn(client);

        Client result = clientDAO.rechercherClientParId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("dupont", result.getNom());
    }

    public void testRechercherTousLesClients() {
    }
}