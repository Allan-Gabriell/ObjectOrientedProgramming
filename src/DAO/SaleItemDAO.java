package DAO;

import conection.Conection;
import entity.SaleItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleItemDAO {
    public void createSaleItem(SaleItem saleItem) {
        String sql = "INSERT INTO item_venda (QUANTIDADE_ITEM, ID_VENDA, ISBN_LIVRO) VALUES (?, ?, ?)";
    
        PreparedStatement ps = null;
        try {
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setInt(1, saleItem.getQuantity());
            ps.setInt(2, saleItem.getIdVenda());
            ps.setInt(3, saleItem.getIsbn());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public List<SaleItem> loadSaleItem() throws SQLException {
        String sql = "SELECT id_item, id_venda, isbn_livro, quantidade_item FROM item_venda WHERE id_venda = (SELECT MAX(id_venda) FROM venda)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SaleItem> saleItems = new ArrayList<>();

        try {
            ps = Conection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                SaleItem saleItem = new SaleItem();
                saleItem.setId(rs.getInt("ID_ITEM"));
                saleItem.setIdVenda(rs.getInt("ID_VENDA"));
                saleItem.setIsbn(rs.getInt("ISBN_LIVRO"));
                saleItem.setQuantity(rs.getInt("QUANTIDADE_ITEM"));

                saleItems.add(saleItem);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }

        return saleItems;
    }

    public void deleteSaleItem(int id) throws SQLException{
        String sql = "DELETE FROM item_venda WHERE ID_ITEM = ?";
        PreparedStatement ps = null;

        try{
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("DAO: Item com o id " + id + " deletado com sucesso!");
        } finally {
            if(ps != null) ps.close();
        }
    }

    public void updateSaleItem(SaleItem saleItem) throws SQLException{
        String sql = "UPDATE item_venda SET ID_VENDA = ?, ISBN_LIVRO = ?, QUANTIDADE_ITEM = ? WHERE ID_ITEM = ?";
        PreparedStatement ps = null;

        try {
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setInt(1, saleItem.getIdVenda());
            ps.setInt(2, saleItem.getIsbn());
            ps.setInt(3, saleItem.getQuantity());
            ps.setInt(4, saleItem.getId());

            ps.executeUpdate();
            System.out.println("DAO: Item com ID " + saleItem.getId() + " atualizado com sucesso!");
        } finally {
            if(ps != null) ps.close();
        }
    }

    public SaleItem searchSaleItem(int id) throws SQLException {
        String sql = "SELECT ID_ITEM, QUANTIDADE_ITEM, ID_VENDA, ISBN_LIVRO FROM item_venda WHERE ID_ITEM = ?";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        SaleItem saleItem = null;

        try{
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setInt(1, id);

            resultSet = ps.executeQuery();
            if(resultSet.next()){
                saleItem = new SaleItem();
                saleItem.setId(resultSet.getInt("ID_ITEM"));
                saleItem.setQuantity(resultSet.getInt("QUANTIDADE_ITEM"));
                saleItem.setIdVenda(resultSet.getInt("ID_VENDA"));
                saleItem.setIsbn(resultSet.getInt("ISBN_LIVRO"));
            } 
        } finally {
            if(resultSet != null) resultSet.close();
            if(ps != null) ps.close();
        }

        return saleItem;
    }
}