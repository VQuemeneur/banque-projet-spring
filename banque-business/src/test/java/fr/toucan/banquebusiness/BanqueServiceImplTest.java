package fr.toucan.banquebusiness;

import fr.toucan.banquepersistence.dao.ClientDAO;
import fr.toucan.banquepersistence.entities.Client;
import fr.toucan.banquepersistence.util.BanqueException;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BanqueServiceImplTest {

    @InjectMocks
    private BanqueServiceImpl banqueService;

    @Mock
    private ClientDAO clientDAO;

    @Test
    public void testAuthentifierOK() throws BanqueException {
        Client client = new Client();
        client.setId(1L);
        client.setMotDePasse("1234");
        client.setNom("dupont");

        when(clientDAO.rechercherClientParId(anyLong())).thenReturn(client);

        Client result = banqueService.authentifier("1", "1234");

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("dupont", result.getNom());
    }

    @Test
    public void testAuthentifierMauvaisMDP() throws BanqueException {
        Client client = new Client();
        client.setId(1L);
        client.setMotDePasse("1234");
        client.setNom("dupont");

        when(clientDAO.rechercherClientParId(anyLong())).thenReturn(client);

        assertThrows(BanqueException.class, () -> banqueService.authentifier("1", "1000"));
    }

    public void testMesComptes() {
    }

    public void testVirement() {
    }
}