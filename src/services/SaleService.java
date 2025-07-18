package services;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import DAO.SaleDAO;
import entity.Sale;

public class SaleService {
    private SaleDAO saleDAO;
    Scanner sc = new Scanner(System.in);


    public SaleService(SaleDAO saleDAO) {
        this.saleDAO = saleDAO;
    }

    public void updateSale(int id) throws SQLException{
        Sale sale = saleDAO.searchSale(id);
        System.out.print("Informe o ID do cliente: ");
        sale.setIdCliente(Integer.parseInt(sc.nextLine()));
        System.out.print("Inform o ID do funcionário: ");
        sale.setIdFuncionario(Integer.parseInt(sc.nextLine()));
        saleDAO.updateSale(sale);
    }

    public void createSale(Sale sale) {
        saleDAO.resgisterSale(sale);
        System.out.println("Venda criada com sucesso.");
    }
}
