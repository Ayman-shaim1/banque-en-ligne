package metier;

import dao.Compte.Compte;
import dao.Compte.CompteDaoImpl;

import java.util.List;

public class Metier {

    public double totalSoldeComptes(String cin){
        double total = 0;
        List<Compte> lstcpt =  new CompteDaoImpl().getAllComptesByClient(cin);
        for(Compte compte:lstcpt)
            total += new CompteDaoImpl().soldeCompte(compte.getNumCompte());
        return total;
    }

}
