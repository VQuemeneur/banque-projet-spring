package fr.toucan.banquepersistence.dao;

import fr.toucan.banquepersistence.entities.Client;
import fr.toucan.banquepersistence.util.BanqueException;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateClientDAO implements ClientDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void ajouterClient(Client client) throws BanqueException {
        try {
            sessionFactory.getCurrentSession().save(client);
        }catch(HibernateException e){
            throw new BanqueException("Erreur d'enregistrement du client");
        }
    }

    @Override
    public Client rechercherClientParId(long id) throws BanqueException {
        try {
           return (Client)sessionFactory.getCurrentSession().load(Client.class, new Long(id));
        }catch(HibernateException e){
            throw new BanqueException("Erreur de chargement du client");
        }
    }

    @Override
    public List<Client> rechercherTousLesClients() throws BanqueException {
        try {
            String hql = "from Client as c";
            return sessionFactory.getCurrentSession().createQuery(hql).list();
        } catch (HibernateException e) {
            throw new BanqueException("Erreur d'obtention de la liste des clients");
        }
    }
}
