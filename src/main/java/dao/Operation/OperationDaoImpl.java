package dao.Operation;

import dao.MyConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

            PreparedStatement ps=con.prepareStatement("select * from OPERATION where numCompte=?");
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
            PreparedStatement ps=con.prepareStatement("select * from operation");
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
            PreparedStatement ps=con.prepareStatement("SELECT * FROM operation op inner JOIN compte cpt on op.compte =cpt.numcompte WHERE cpt.cinClient = ?");
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
}
