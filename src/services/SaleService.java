package services;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.SaleDAO;
import entity.Sale;

public class SaleService {
    public SaleDAO saleDAO;
    Scanner sc = new Scanner(System.in);
    // Criar função que cria uma venda

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
}
