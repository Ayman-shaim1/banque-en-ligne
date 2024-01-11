package dao.Client;

import dao.MyConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements IClientDao{

    Connection con;

    @Override
    public void addClient(Client client) {
        try {
            Class.forName(MyConnection.driver);
            con= DriverManager.getConnection(MyConnection.url, MyConnection.user, MyConnection.password);
            PreparedStatement ps=con.prepareStatement("insert into client(cin,nom,prenom,telephone,email) values(?,?,?,?,?)");

            ps.setString(1, client.getCin());
            ps.setString(2, client.getNom());
            ps.setString(3, client.getPrenom());
            ps.setString(4, client.getTelephone());
            ps.setString(5, client.getEmail());

            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public Client getClient(String cin) {
        Client clt= new Client();

        try {
            Class.forName(MyConnection.driver);
            con= DriverManager.getConnection(MyConnection.url, MyConnection.user, MyConnection.password);

            PreparedStatement ps=con.prepareStatement("select * from CLIENT where cin=?");
            ps.setString(1, cin);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                clt.setCin(rs.getString("cin"));
                clt.setNom(rs.getString("nom"));
                clt.setPrenom(rs.getString("prenom"));
                clt.setTelephone(rs.getString("telephone"));
                clt.setEmail(rs.getString("email"));
            }

            ps.close();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return clt;
    }
    @Override
    public boolean findClient(String cin,String motdepasse) {
        try {
            Class.forName(MyConnection.driver);
            con= DriverManager.getConnection(MyConnection.url, MyConnection.user, MyConnection.password);

            PreparedStatement ps=con.prepareStatement("select * from CLIENT where cin=? and motdepasse=?");
            ps.setString(1, cin);
            ps.setString(2, motdepasse);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                ps.close();
                return true;
            }

            ps.close();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Client> getAllClients() {
        List<Client> clts= new ArrayList<>();
        try {
            Class.forName(MyConnection.driver);
            con= DriverManager.getConnection(MyConnection.url, MyConnection.user, MyConnection.password);
            PreparedStatement ps=con.prepareStatement("select * from Client");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Client clt= new Client();
                clt.setCin(rs.getString("cin"));
                clt.setNom(rs.getString("nom"));
                clt.setPrenom(rs.getString("prenom"));
                clt.setPrenom(rs.getString("telephone"));
                clt.setPrenom(rs.getString("email"));
                clts.add(clt);
            }
            con.close();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return clts;
    }

    @Override
    public void deleteClient(Client client) {
        try {
            Class.forName(MyConnection.driver);
            con= DriverManager.getConnection(MyConnection.url, MyConnection.user, MyConnection.password);

            PreparedStatement ps=con.prepareStatement("delete from CLIENT where cin = ?");
            ps.setString(1, client.getCin());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void updateClient(Client client) {

    }
}
