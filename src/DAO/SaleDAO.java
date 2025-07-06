package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import conection.Conection;
import entity.Sale;
import java.util.Date;

public class SaleDAO {
    public void resgisterSale (Sale sale){
        String sql = "INSERT INTO venda (ID_VENDA, VALOR_TOTAL, DATA) VALUES (?, ?, ?)";

        PreparedStatement ps = null;
        try {
            ps = Conection.getConnection().prepareStatement(sql);

            ps.setInt(1, sale.getId());
            ps.setDouble(2, sale.getTotalValue());

            Date utilDate = sale.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps.setDate(3, sqlDate);

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
