package fr.toucan.banque;

import fr.toucan.banquebusiness.BanqueService;
import fr.toucan.banquepersistence.entities.Client;
import fr.toucan.banquepersistence.util.BanqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController
{
    @Autowired
    private BanqueService banqueService;

    // Affichage du formulaire
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        return "login";
    }

    // Soumission du formulaire
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(
            @RequestParam String identifiant,
            @RequestParam String motDePasse,
            HttpSession session,
            Model model) {

        try {
            Client client = banqueService.authentifier(identifiant, motDePasse);

            // Stocker le client en session
            session.setAttribute("client", client);

            return "redirect:/home";

        } catch (BanqueException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }
}

