package services;

import DAO.SaleDAO;
import entity.Sale;
import java.util.Date;

public class SaleService {
    private SaleDAO saleDAO;

    public SaleService(SaleDAO saleDAO) {
        this.saleDAO = saleDAO;
    }

    public void createSale(int idCliente, int idFuncionario) {
        Sale sale = new Sale();
        sale.setDate(new Date());
        sale.setIdCliente(idCliente);
        sale.setIdFuncionario(idFuncionario);

        saleDAO.resgisterSale(sale);
        System.out.println("Venda criada com sucesso.");
    }
}
