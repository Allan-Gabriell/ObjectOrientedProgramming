package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conection.Conection;
import entity.Client;

public class ClientDAO {
    public void resgisterClient(Client client){
        String sql = "INSERT INTO cliente (NOME_CLIENTE, CPF_CLIENTE) VALUES (?, ?)";

        PreparedStatement ps = null;
        try {
            ps = Conection.getConnection().prepareStatement(sql);

            ps.setString(1, client.getName());
            ps.setString(2, client.getCpf());

            ps.execute();
            ps.close();
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Client> loadClients () throws SQLException {
        String sql = "SELECT ID_CLIENTE, NOME_CLIENTE, CPF_CLIENTE FROM cliente";
        PreparedStatement ps = null;
        ResultSet result = null;
        List<Client> clients = new ArrayList<>();

        try {
            ps = Conection.getConnection().prepareStatement(sql);
            result = ps.executeQuery();
            
            while(result.next()){
                Client client = new Client();
                client.setId(result.getInt("ID_CLIENTE"));
                client.setName(result.getString("NOME_CLIENTE"));
                client.setCpf(result.getString("CPF_CLIENTE"));
                clients.add(client);
            }
        } finally {
            if(result != null) result.close();
            if(ps != null) ps.close();
        }
        return clients;
    }

    public Client searchClientById(int id) throws SQLException{
    String sql = "SELECT ID_CLIENTE, NOME_CLIENTE, CPF_CLIENTE FROM cliente WHERE ID_CLIENTE = ?";
    PreparedStatement ps = null;
    ResultSet result = null;
    Client client = null;
        try{
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setInt(1, id);

            result = ps.executeQuery();
            if(result.next()){
                client = new Client();
                client.setId(result.getInt("ID_CLIENTE"));
                client.setName(result.getString("NOME_CLIENTE"));
                client.setCpf(result.getString("CPF_CLIENTE"));
            }
        } finally {
            if(result != null) result.close();
            if(ps != null) ps.close();
        }
        return client;
    }

    public void updateClient(Client client) throws SQLException {
        String sql = "UPDATE cliente SET NOME_CLIENTE = ?, CPF_CLIENTE = ? WHERE ID_CLIENTE = ?";
        PreparedStatement ps = null;

        try {
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setString(1, client.getName());
            ps.setString(2, client.getCpf());
            ps.setInt(3, client.getId());

            ps.executeUpdate();
            System.out.println("DAO: Cliente ID " + client.getId() + " atualizado.");
        } finally {
            if(ps != null) ps.close();
        }
    }

    public void deletedClient(Client client) throws SQLException{
        String sql = "DELETE FROM cliente WHERE ID_CLIENTE = ?";
        PreparedStatement ps = null;
        try{
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setInt(1, client.getId());

            ps.executeUpdate();
            System.out.println("DAO: cliente com o id " + client.getId() + " deletado");
        } finally {
            if(ps != null) ps.close();
        }
    }
}
