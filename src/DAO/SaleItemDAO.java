package DAO;

import conection.Conection;
import entity.SaleItem;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaleItemDAO {
    public void resgisterSaleItem (SaleItem saleItem) {
        String sql = "INSERT INTO itemVenda (ID_ITEMVENDA, QNT_ITEMVENDA) VALUES (?, ?)";

        PreparedStatement ps = null;
        try {
            ps = Conection.getConnection().prepareStatement(sql);

            ps.setInt(1, saleItem.getId());
            ps.setInt(2, saleItem.getQuantity());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
