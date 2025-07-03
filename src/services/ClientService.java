package services;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import DAO.ClientDAO;
import entity.Client;

public class ClientService {
    private ClientDAO clientDAO;
    Scanner sc = new Scanner(System.in);

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public void newClient(String name, String cpf){
        Client client = new Client(name, cpf);
        clientDAO.resgisterClient(client);
    }
    
    public List<Client> getAllClients(){
        try {
            return clientDAO.loadClients();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar a lista de clientes no banco de dados: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Não foi possível carregar a lista de clientes.");
        }
    }

    public void updateClient(int id) throws SQLException{
        Client client = new Client();
        client = clientDAO.searchClientById(id);

        System.out.print("Informe o novo nome do cliente: ");
        client.setName(sc.nextLine());
        System.out.print("Informe o novo CPF: ");
        client.setCpf(sc.nextLine());
        
        clientDAO.updateClient(client);
    }

    public void deletedClient(int id) throws SQLException{
        Client client = clientDAO.searchClientById(id);
        clientDAO.deletedClient(client);
    }

    public void displayClient(int id) throws SQLException{
        Client client = clientDAO.searchClientById(id);
        System.out.println(client);
    }
}
