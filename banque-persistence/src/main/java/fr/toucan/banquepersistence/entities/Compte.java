package fr.toucan.banquepersistence.entities;

import javax.persistence.*;

@Entity
@Table(name="compte")
public class Compte {
    @Id
    @Column(name="numero")
    private Long numero;

    @Column(name="solde")
    private double solde;

    @ManyToOne (targetEntity=Client.class)
    @JoinColumn(name = "idclient")
    private Client client;

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
