package dao.Operation;

import dao.Compte.Compte;
import dao.Compte.CompteDaoImpl;

import java.util.Date;
import java.util.List;

public class TestOperationDao {
    static OperationDaoImpl dao;


    public static void main(String[] args) {
        dao = new OperationDaoImpl();

       //  dao.addOperation(new Operation(new Date(),OperationType.RETRAIT,1,100));
        // dao.addOperation(new Operation(new Date(),OperationType.RETRAIT,1,120));
        // dao.addOperation(new Operation(new Date(),OperationType.RETRAIT,3,330));
        // dao.addOperation(new Operation(new Date(),OperationType.RETRAIT,3,567.48));
        // dao.addOperation(new Operation(new Date(),OperationType.RETRAIT,3,199.99));
        // dao.addOperation(new Operation(new Date(),OperationType.RETRAIT,4,299));
        // dao.addOperation(new Operation(new Date(),OperationType.RETRAIT,5,67));


       List<Operation> opts= dao.getAllOperations();
        for (Operation std : opts) {
            System.out.println(std.toString());
        }

        System.out.println("Hello world");
    }
}
