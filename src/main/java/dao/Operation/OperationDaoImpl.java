package dao.Operation;

import dao.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperationDaoImpl implements IOperationDao{
    Connection con;
    @Override
    public void addOperation(Operation operation) {
        try {
            Class.forName(MyConnection.driver);
            con= DriverManager.getConnection(MyConnection.url, MyConnection.user, MyConnection.password);
            PreparedStatement ps=con.prepareStatement("insert into operation(dateOp,type,compte,commission) values(?,?,?,?)");

            java.util.Date utilDate = operation.getDateOp();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps.setDate(1, sqlDate);

            String typeAsString = operation.getType().toString();
            ps.setString(2, typeAsString);

            ps.setInt(3, operation.getNumCompte());
            ps.setDouble(4,operation.getCommission());

            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Operation getOperation(int num) {
        Operation opt = new Operation();

        try {
            Class.forName(MyConnection.driver);
            con= DriverManager.getConnection(MyConnection.url, MyConnection.user, MyConnection.password);

            PreparedStatement ps=con.prepareStatement("select * from OPERATION where numCompte=? order by dateop desc");
            ps.setInt(1, num);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                opt.setNumOp(rs.getInt("numOp"));
                opt.setNumCompte(rs.getInt("compte"));
                String typeAsString = rs.getString("type");
                OperationType type = OperationType.valueOf(typeAsString.toUpperCase());
                opt.setType(type);
                opt.setDateOp(rs.getDate("dateOp"));
                opt.setCommission(rs.getDouble("commission"));
            }
            ps.close();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return opt;
    }

    @Override
    public List<Operation> getAllOperations() {
        List<Operation> opts= new ArrayList<>();
        try {
            Class.forName(MyConnection.driver);
            con= DriverManager.getConnection(MyConnection.url, MyConnection.user, MyConnection.password);
            PreparedStatement ps=con.prepareStatement("select * from operation order by dateop desc");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {

                Operation opt= new Operation();
                opt.setNumOp(rs.getInt("numOp"));
                opt.setNumCompte(rs.getInt("compte"));
                String typeAsString = rs.getString("type");
                OperationType type = OperationType.valueOf(typeAsString.toUpperCase());
                opt.setType(type);
                opt.setDateOp(rs.getDate("dateOp"));
                opt.setCommission(rs.getDouble("commission"));
                opts.add(opt);
            }
            con.close();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return opts;
    }

    @Override
    public void deleteOperation(Operation Operation) {

    }

    @Override
    public void updateOperation(Operation Operation) {

    }

    @Override
    public  List<Operation> getOperationByCompte(int numCompte) {
        List<Operation> opts= new ArrayList<>();
        try {
            Class.forName(MyConnection.driver);
            con= DriverManager.getConnection(MyConnection.url, MyConnection.user, MyConnection.password);
            PreparedStatement ps=con.prepareStatement("select * from operation where compte = ?");
            ps.setInt(1,numCompte);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {

                Operation opt= new Operation();
                opt.setNumOp(rs.getInt("numOp"));
                opt.setNumCompte(rs.getInt("compte"));
                String typeAsString = rs.getString("type");
                OperationType type = OperationType.valueOf(typeAsString.toUpperCase());
                opt.setType(type);
                opt.setDateOp(rs.getDate("dateOp"));
                opt.setCommission(rs.getDouble("commission"));
                opts.add(opt);
            }
            con.close();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return opts;
    }

    @Override
    public List<Operation> getOperationsByClient(String cin) {
        List<Operation> opts= new ArrayList<>();
        try {
            Class.forName(MyConnection.driver);
            con= DriverManager.getConnection(MyConnection.url, MyConnection.user, MyConnection.password);
            PreparedStatement ps=con.prepareStatement("SELECT * FROM operation op inner JOIN compte cpt on op.compte =cpt.numcompte WHERE cpt.cinClient = ? order by dateop desc");
            ps.setString(1,cin);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {

                Operation opt= new Operation();
                opt.setNumOp(rs.getInt("numOp"));
                opt.setNumCompte(rs.getInt("compte"));
                String typeAsString = rs.getString("type");
                OperationType type = OperationType.valueOf(typeAsString.toUpperCase());
                opt.setType(type);
                opt.setDateOp(rs.getDate("dateOp"));
                opt.setCommission(rs.getDouble("commission"));
                opts.add(opt);
            }
            con.close();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return opts;
    }

    @Override
    public void retrait(double solde, int numCompte) {
        try {
            Class.forName(MyConnection.driver);
            con = DriverManager.getConnection(MyConnection.url, MyConnection.user, MyConnection.password);

            // Set autocommit to false to start a transaction
            con.setAutoCommit(false);

            // Update Compte table
            PreparedStatement ps1 = con.prepareStatement("update Compte set solde = solde - ? where numCompte = ?");
            ps1.setDouble(1, solde + 5);
            ps1.setInt(2, numCompte);
            ps1.executeUpdate();
            ps1.close();

            // Insert into operation table
            PreparedStatement ps = con.prepareStatement("insert into operation(dateOp, type, compte, commission) values(?,?,?,?)");
            java.util.Date utilDate = new Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            ps.setDate(1, sqlDate);
            ps.setString(2, "retrait");
            ps.setInt(3, numCompte);
            ps.setDouble(4, 5);
            ps.executeUpdate();
            ps.close();

            // Commit the transaction
            con.commit();

        } catch (SQLException e) {
            // Rollback the transaction in case of an exception
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Set autocommit back to true to end the transaction
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
