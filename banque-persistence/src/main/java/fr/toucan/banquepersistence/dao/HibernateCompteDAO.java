package fr.toucan.banquepersistence.dao;

import fr.toucan.banquepersistence.entities.Client;
import fr.toucan.banquepersistence.entities.Compte;
import fr.toucan.banquepersistence.util.BanqueException;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateCompteDAO implements CompteDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void ajouterCompte(Compte compte) throws BanqueException {
        try {
            sessionFactory.getCurrentSession().save(compte);
        }catch(HibernateException e){
            throw new BanqueException("Erreur d'enregistrement du compte");
        }
    }

    @Override
    public Compte rechercherCompteParNumero(long numero) throws BanqueException {
        try {
            return (Compte)sessionFactory.getCurrentSession().load(Compte.class, new Long(numero));
        }catch(HibernateException e){
            throw new BanqueException("Erreur de chargement du compte");
        }
    }

    @Override
    public List<Compte> rechercherComptesClient(Client client) throws BanqueException {
        try {
            String hql = "from Compte as c where c.client=?";
            return sessionFactory.getCurrentSession().createQuery(hql).setEntity(1,client).list();
        } catch (HibernateException e) {
            throw new BanqueException("Erreur de récupération des comptes");
        }
    }

    @Override
    public void modifierCompte(Compte compte) throws BanqueException {
        try {
            sessionFactory.getCurrentSession().merge(compte);
        } catch (HibernateException e) {
            throw new BanqueException("Erreur de modification du compte");
        }
    }
}
