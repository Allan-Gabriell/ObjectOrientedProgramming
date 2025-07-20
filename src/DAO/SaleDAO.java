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
            ps.setInt(2, sale.getIdClient());
            ps.setInt(3, sale.getIdEmploee());


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
                sale.setIdClient(rs.getInt("ID_CLIENTE"));
                sale.setIdEmploee(rs.getInt("ID_FUNCIONARIO"));
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
                sale.setIdClient(resultSet.getInt("ID_CLIENTE"));
                sale.setIdEmploee(resultSet.getInt("ID_FUNCIONARIO"));
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
            ps.setInt(2, sale.getIdClient());
            ps.setInt(3, sale.getIdEmploee());
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
                sale.setIdEmploee(rs.getInt("id_funcionario"));
                sale.setIdClient(rs.getInt("id_cliente"));
                sale.setTotalValue(rs.getDouble("total_venda"));
                
                saleItems.add(sale);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    
        return saleItems;
    }
    
    public List<SaleItem> getSaleItemsBySaleId(int saleId) throws SQLException {
        List<SaleItem> saleItems = new ArrayList<>();
        String sql = "SELECT * FROM item_venda WHERE id_venda = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setInt(1, saleId);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                SaleItem item = new SaleItem();
                item.setId(rs.getInt("id_item"));
                item.setIdSale(rs.getInt("id_venda"));
                item.setIsbn(rs.getInt("isbn_livro"));
                item.setQuantity(rs.getInt("quantidade_item"));
                saleItems.add(item);
            }
        } finally {
            if(ps != null) ps.close();
            if(rs != null) rs.close();
        }
    
        return saleItems;
    }

    public double calculateTotalBySaleId(int id) throws SQLException {
        double total = 0.0;
        String sql = "SELECT SUM(iv.quantidade_item * l.preco_livro) AS total " +
                     "FROM item_venda iv " +
                     "JOIN livro l ON iv.isbn_livro = l.isbn_livro " +
                     "WHERE iv.id_venda = ?";
    
        PreparedStatement ps = Conection.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
    
        if (rs.next()) {
            total = rs.getDouble("total");
        }
    
        return total;
    }
    
    
    public List<Sale> getSaleItemsByClient(int Id) throws SQLException {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM venda WHERE id_cliente = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setInt(1, Id);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Sale sale = new Sale();
                sale.setId(rs.getInt("id_venda"));
                sale.setDate(rs.getDate("data_venda"));
                sale.setIdEmploee(rs.getInt("id_funcionario"));
                sale.setIdClient(rs.getInt("id_cliente"));
                sales.add(sale);
            }
        } finally {
            if(ps != null) ps.close();
            if(rs != null) rs.close();
        }
    
        return sales;
    }

    public List<Sale> listAllSales() throws SQLException {
        List<Sale> sales = new ArrayList<>();
        String sql = """
                        SELECT 
                            v.id_venda,
                            v.data_venda,
                            v.id_cliente,
                            v.id_funcionario,
                            COALESCE(SUM(iv.quantidade_item * l.preco_livro), 0) AS total
                        FROM venda v
                        LEFT JOIN item_venda iv ON v.id_venda = iv.id_venda
                        LEFT JOIN livro l ON iv.isbn_livro = l.isbn_livro
                        GROUP BY v.id_venda, v.data_venda, v.id_cliente, v.id_funcionario
                        ORDER BY v.id_venda
                    """;
    
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            ps = Conection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
    
            while (rs.next()) {
                Sale sale = new Sale();
                sale.setId(rs.getInt("id_venda"));
                sale.setDate(rs.getDate("data_venda"));
                sale.setIdClient(rs.getInt("id_cliente"));
                sale.setIdEmploee(rs.getInt("id_funcionario"));
                sale.setTotalValue(rs.getDouble("total"));
                sales.add(sale);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    
        return sales;
    }
    
    
}
