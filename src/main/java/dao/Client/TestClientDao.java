package dao.Client;

import java.util.List;

public class TestClientDao {
    static ClientDaoImpl dao;


    public static void main(String[] args) {
        dao = new ClientDaoImpl();
       // dao.addClient(new Client("A12345","Jhon","Doe","+1 01 02 03 04 05","j.doe@example.com"));
       // dao.addClient(new Client("A54321","Steve","Smith","+1 11 22 33 44 55","s.smith@example.com"));
        List<Client> stds= dao.getAllClients();
        stds.forEach(std -> {
            System.out.println(std.toString());
        });
        System.out.println(dao.getClient("A12345").toString());
        System.out.println("Hello world");
    }
}
