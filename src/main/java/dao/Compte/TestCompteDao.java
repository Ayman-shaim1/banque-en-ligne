package dao.Compte;

import dao.Client.Client;
import dao.Client.ClientDaoImpl;

import java.util.List;

public class TestCompteDao {
    static CompteDaoImpl dao;


    public static void main(String[] args) {
        dao = new CompteDaoImpl();

        // dao.addCompte(new Compte(15000,"A12345"));

        // dao.addCompte(new Compte(7000,"A54321"));
        // dao.addCompte(new Compte(9000,"A54321"));



        List<Compte> stds= dao.getAllComptesByClient("A54321");
        stds.forEach(std -> {
            System.out.println(std.toString());
        });
        System.out.println("solde: "+dao.soldeCompte(1));


        System.out.println("Hello world");
    }
}
