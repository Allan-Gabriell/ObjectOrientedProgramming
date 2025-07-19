package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import DAO.EmployeeDAO;
import DAO.SaleDAO;
import entity.Employee;
import entity.Sale;

public class SaleService {
    private SaleDAO saleDAO;
    private EmployeeDAO employeeDAO;
    Scanner sc = new Scanner(System.in);

    public SaleService(SaleDAO saleDAO, EmployeeDAO employeeDAO) {
        this.saleDAO = saleDAO;
        this.employeeDAO = employeeDAO;
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

    public void deleteSale(int id) throws SQLException{
        saleDAO.deleteSale(id);
    }

    public void listSaleEmployee(int id) throws SQLException{
        Employee employee = employeeDAO.searchEmployee(id);
        System.out.println("Lista de vendas do funcionário " + employee.getName());
        List<Sale> sales = saleDAO.listSaleEmployee(id);
        for(Sale sale : sales){
            System.out.println(sale);
        }
    }

}
