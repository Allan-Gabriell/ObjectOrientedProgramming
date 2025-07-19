package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conection.Conection;
import entity.Client;
import entity.Sale;
import entity.SaleItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public void deleteSale(int id) throws SQLException{
        String sql =  "DELETE FROM venda WHERE ID_VENDA = ?";
        PreparedStatement ps = null;

        try {
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("DAO: Venda com o id " + id + " deletado com sucesso!");
        } finally {
            if(ps != null) ps.close();
        }
    }

    public List<Sale> listSaleEmployee(int idFuncionario) throws SQLException {
        String sql = "SELECT " +
                     "    v.id_venda, " +
                     "    v.data_venda, " +
                     "    v.id_funcionario, " +
                     "    v.id_cliente, " +
                     "    f.nome_funcionario, " +
                     "    SUM(iv.quantidade_item * l.preco_livro) AS total_venda " +
                     "FROM venda v " +
                     "JOIN funcionario f ON v.id_funcionario = f.id_funcionario " +
                     "JOIN item_venda iv ON v.id_venda = iv.id_venda " +
                     "JOIN livro l ON iv.isbn_livro = l.isbn_livro " +
                     "WHERE f.id_funcionario = ? " +
                     "GROUP BY v.id_venda, v.data_venda, v.id_funcionario, v.id_cliente, f.nome_funcionario " +
                     "ORDER BY v.id_venda;";
    
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Sale> saleItems = new ArrayList<>();
    
        try {
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setInt(1, idFuncionario);
    
            rs = ps.executeQuery();
    
            while (rs.next()) {
                Sale sale = new Sale();
                sale.setId(rs.getInt("id_venda"));
                sale.setDate(rs.getDate("data_venda"));
                sale.setIdFuncionario(rs.getInt("id_funcionario"));
                sale.setIdCliente(rs.getInt("id_cliente"));
                sale.setTotalValue(rs.getDouble("total_venda"));
                
                saleItems.add(sale);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    
        return saleItems;
    }
    
    
}
