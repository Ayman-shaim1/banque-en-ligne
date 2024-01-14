package dao.Compte;

import dao.Client.Client;
import dao.MyConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompteDaoImpl implements ICompteDao{
    Connection con;
    @Override
    public void addCompte(Compte compte) {
        try {
            Class.forName(MyConnection.driver);
            con= DriverManager.getConnection(MyConnection.url, MyConnection.user, MyConnection.password);
            PreparedStatement ps=con.prepareStatement("insert into compte(solde,cinClient) values(?,?)");

            ps.setDouble(1, compte.getSolde());
            ps.setString(2, compte.getCinClient());

            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Compte getCompte(int num) {
        Compte cpt = new Compte();

        try {
            Class.forName(MyConnection.driver);
            con= DriverManager.getConnection(MyConnection.url, MyConnection.user, MyConnection.password);

            PreparedStatement ps=con.prepareStatement("select * from COMPTE where numCompte=?");
            ps.setInt(1, num);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                cpt.setNumCompte(rs.getInt("numCompte"));
                cpt.setSolde(rs.getDouble("solde"));
                cpt.setCinClient(rs.getString("cinClient"));
            }
            ps.close();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cpt;
    }

    @Override
    public List<Compte> getAllComptes() {
        List<Compte> cpts= new ArrayList<>();
        try {
            Class.forName(MyConnection.driver);
            con= DriverManager.getConnection(MyConnection.url, MyConnection.user, MyConnection.password);
            PreparedStatement ps=con.prepareStatement("select * from compte");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {

                Compte cpt= new Compte();
                cpt.setNumCompte(rs.getInt("numCompte"));
                cpt.setSolde(rs.getDouble("solde"));
                cpt.setCinClient(rs.getString("cinClient"));

                cpts.add(cpt);
            }
            con.close();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return cpts;
    }

    @Override
    public void deleteCompte(Compte compte) {

    }

    @Override
    public void updateCompte(Compte compte) {

    }

    @Override
    public double soldeCompte(int numCompte) {
        try {
            Class.forName(MyConnection.driver);
            con= DriverManager.getConnection(MyConnection.url, MyConnection.user, MyConnection.password);
            PreparedStatement ps=con.prepareStatement("select solde from compte where numcompte = ?");
            ps.setInt(1, numCompte);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getDouble("solde");
            }
            con.close();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Compte> getAllComptesByClient(String cin) {
        List<Compte> cpts= new ArrayList<>();
        try {
            Class.forName(MyConnection.driver);
            con= DriverManager.getConnection(MyConnection.url, MyConnection.user, MyConnection.password);
            PreparedStatement ps=con.prepareStatement("select * from compte where cinClient = ?");
            ps.setString(1,cin);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {

                Compte cpt= new Compte();
                cpt.setNumCompte(rs.getInt("numCompte"));
                cpt.setSolde(rs.getDouble("solde"));
                cpt.setCinClient(rs.getString("cinClient"));

                cpts.add(cpt);
            }
            con.close();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return cpts;
    }

}
