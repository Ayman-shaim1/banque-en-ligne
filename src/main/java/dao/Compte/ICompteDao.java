package dao.Compte;

import dao.Client.Client;

import java.util.List;

public interface ICompteDao {
    public void addCompte(Compte compte);
    public Compte getCompte(int num);
    public List<Compte> getAllComptes();
    public void deleteCompte(Compte compte);
    public void updateCompte(Compte compte);

    public double soldeCompte(int numCompte);

    public List<Compte> getAllComptesByClient(String cin);


}
