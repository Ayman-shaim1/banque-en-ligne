package dao.Operation;

import dao.Compte.Compte;

import java.util.List;

public interface IOperationDao {
    public void addOperation(Operation Operation);
    public Operation getOperation(int num);
    public List<Operation> getAllOperations();
    public void deleteOperation(Operation Operation);
    public void updateOperation(Operation Operation);
    public  List<Operation> getOperationByCompte(int numCompte);
    public  List<Operation> getOperationsByClient(String cin);
    public  void retrait(double solde,int numCompte);


}
