package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conection.Conection;
import entity.Client;
import entity.Sale;
import entity.SaleItem;

import java.util.Date;

public class SaleDAO {
    public void resgisterSale (Sale sale){
        String sql = "INSERT INTO venda (DATA_VENDA, ID_CLIENTE, ID_FUNCIONARIO) VALUES (?, ?, ?)";

        PreparedStatement ps = null;
        try {
            ps = Conection.getConnection().prepareStatement(sql);

            java.sql.Date sqlDate = new java.sql.Date(sale.getDate().getTime());

            ps.setDate(1, sqlDate);
            ps.setInt(2, sale.getIdCliente());
            ps.setInt(3, sale.getIdFuncionario());


            ps.execute();
            ps.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Sale getLastSale() throws SQLException {
        String sql = "SELECT * FROM venda ORDER BY ID_VENDA DESC LIMIT 1";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Sale sale = null;

        try{
            ps = Conection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();

            if(rs.next()) {
                sale = new Sale();
                sale.setId(rs.getInt("ID_VENDA"));
                sale.setDate(rs.getDate("DATA_VENDA"));
                sale.setIdCliente(rs.getInt("ID_CLIENTE"));
                sale.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return sale;
    }

    public Sale searchSale(int id) throws SQLException {
        String sql = "SELECT * FROM venda WHERE ID_VENDA = ?";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Sale sale = null;

        try {
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setInt(1, id);

            resultSet = ps.executeQuery();

            if(resultSet.next()){
                sale = new Sale();
                sale.setId(resultSet.getInt("ID_VENDA"));
                sale.setDate(resultSet.getDate("DATA_VENDA"));
                sale.setIdCliente(resultSet.getInt("ID_CLIENTE"));
                sale.setIdFuncionario(resultSet.getInt("ID_FUNCIONARIO"));
            }
        } finally {
            if(resultSet != null) resultSet.close();
            if(ps != null) ps.close();
        }
        return sale;
    }

    public void updateSale(Sale sale) throws SQLException{
        String sql = "UPDATE venda SET DATA_VENDA = ?, ID_CLIENTE = ?, ID_FUNCIONARIO = ? WHERE ID_VENDA = ?";
        PreparedStatement ps = null;
         try {
            ps = Conection.getConnection().prepareStatement(sql);

            // Converte java.util.Date para java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(sale.getDate().getTime());
            
            ps.setDate(1, sqlDate);
            ps.setInt(2, sale.getIdCliente());
            ps.setInt(3, sale.getIdFuncionario());
            ps.setInt(4, sale.getId());

            ps.executeUpdate();
            System.out.println("DAO: Venda ID " + sale.getId() + " atualizado com sucesso!");
        } finally {
            if(ps != null) ps.close();
        }
    }

}
