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

            Date utilDate = sale.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
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

    public void updateSale(int id){
        String sql = "UPDATE venda SET DATA_VENDA = ?, ID_CLIENTE = ?, ID_FUNCIONARIO = ? WHERE ID_VENDA = ?";
        
    }

}
