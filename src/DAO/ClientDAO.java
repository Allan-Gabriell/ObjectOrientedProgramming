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
        ResultSet resultSet = null;
        List<Client> clients = new ArrayList<>();

        try {
            ps = Conection.getConnection().prepareStatement(sql);
            resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                Client client = new Client();
                client.setId(resultSet.getInt("ID_CLIENTE"));
                client.setName(resultSet.getString("NOME_CLIENTE"));
                client.setCpf(resultSet.getString("CPF_CLIENTE"));
                clients.add(client);
            }
        } finally {
            if(resultSet != null) resultSet.close();
            if(ps != null) ps.close();
        }
        return clients;
    }

    public Client searchClientById(int id) throws SQLException{
    String sql = "SELECT ID_CLIENTE, NOME_CLIENTE, CPF_CLIENTE FROM cliente WHERE ID_CLIENTE = ?";
    PreparedStatement ps = null;
    ResultSet resultSet = null;
    Client client = null;
    try{
        ps = Conection.getConnection().prepareStatement(sql);
        ps.setInt(1, id);

        resultSet = ps.executeQuery();
        if(resultSet.next()){
            client = new Client();
            client.setId(resultSet.getInt("ID_CLIENTE"));
            client.setName(resultSet.getString("NOME_CLIENTE"));
            client.setCpf(resultSet.getString("CPF_CLIENTE"));
        }
    } finally {
        if(resultSet != null) resultSet.close();
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