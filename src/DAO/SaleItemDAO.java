package DAO;

import conection.Conection;
import entity.Book;
import entity.SaleItem;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaleItemDAO {
    public void resgisterSaleItem (SaleItem saleItem) {
        String sql = "INSERT INTO item_venda (QUANTIDADE_ITEM, ID_VENDA, ISBN_LIVRO) VALUES (?, ?, ?)";

        PreparedStatement ps = null;
        try {
            ps = Conection.getConnection().prepareStatement(sql);

            for(Book book : saleItem.getBooks()) {
                ps.setInt(1, saleItem.getQuantity());
                ps.setInt(2, saleItem.getSale().getId());
                ps.setInt(3, book.getIsbn());
                ps.execute();
            }

            ps.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
