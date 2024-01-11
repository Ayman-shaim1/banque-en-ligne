package dao.Client;

import java.util.List;

public interface IClientDao {

    public void addClient(Client client);
    public Client getClient(String cin);
    public List<Client> getAllClients();
    public void deleteClient(Client client);
    public void updateClient(Client client);
    public boolean findClient(String cin,String motdepasse);
}
