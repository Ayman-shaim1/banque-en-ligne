package dao.Compte;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Compte {

    private int numCompte;
    private double solde;
    private String cinClient;


    public Compte(double solde, String cinClient) {
        this.solde = solde;
        this.cinClient = cinClient;
    }
}
