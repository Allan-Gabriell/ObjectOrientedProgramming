package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAO.BookDAO;
import DAO.ClientDAO;
import DAO.EmployeeDAO;
import DAO.SaleDAO;
import DAO.SaleItemDAO;
import entity.Employee;
import entity.Sale;
import entity.SaleItem;

public class SaleService {
    private SaleDAO saleDAO;
    private EmployeeDAO employeeDAO;
    private SaleItemService saleItemService;
    private ClientDAO clientDAO;
    Scanner sc = new Scanner(System.in);

    public SaleService(SaleDAO saleDAO, EmployeeDAO employeeDAO, SaleItemDAO saleItemDAO, BookDAO bookDAO, ClientDAO clientDAO) {
        this.saleDAO = saleDAO;
        this.employeeDAO = employeeDAO;
        this.saleItemService = new SaleItemService(saleItemDAO, saleDAO, bookDAO);
        this.clientDAO = clientDAO;
    }

    public void updateSale(int id) throws SQLException{
        Sale sale = saleDAO.searchSale(id);
        System.out.print("Informe o ID do cliente: ");
        sale.setIdClient(Integer.parseInt(sc.nextLine()));
        System.out.print("Inform o ID do funcionário: ");
        sale.setIdEmploee(Integer.parseInt(sc.nextLine()));
        saleDAO.updateSale(sale);
    }

    public void createSale(Sale sale) {
        saleDAO.resgisterSale(sale);
        System.out.println("DAO: Venda criada com sucesso!");
    }

    public void deleteSale(int id) throws SQLException{
        saleDAO.deleteSale(id);
    }

    public void addSaleItemSale(SaleItem saleItem) throws SQLException{
        saleItemService.createSaleItem(saleItem.getIsbn());
    }

    public void deleteSaleItemSale(SaleItem saleItem) throws SQLException{
        saleItemService.deleteSaleItem(saleItem.getId());
    }

    public void displayDataSale(int id) throws SQLException{
        Sale sale = saleDAO.searchSale(id);
        sale.setTotalValue(saleItemService.calculateSubtotal(sale.getId()));
        System.out.println(sale);
        System.out.println(employeeDAO.searchEmployee(sale.getIdEmploee()));
        System.out.println(clientDAO.searchClientById(sale.getIdClient()));
    }

    public void listSaleEmployee(int id) throws SQLException{
        Employee employee = employeeDAO.searchEmployee(id);
        System.out.println("Lista de vendas do funcionário " + employee.getName());
        List<Sale> sales = saleDAO.listSaleEmployee(id);
        for(Sale sale : sales){
            sale.setTotalValue(saleItemService.calculateSubtotal(sale.getId()));
            System.out.println(sale);

        }
    }

    public List<SaleItem> listSaleItem(int id) {
        try {
            List<SaleItem> saleItems = saleDAO.getSaleItemsBySaleId(id);
            return saleItems;
        } catch (SQLException e) {
            return new ArrayList<>(); 
        }
    }
    
    public void listSaleClient(int id) throws SQLException{
        List<Sale> sales = saleDAO.getSaleItemsByClient(id);
        System.out.println("----- Dados da Venda -----");
        for(Sale sale : sales){
            sale.setTotalValue(saleItemService.calculateSubtotal(sale.getId()));
            System.out.println(sale + "\n-- Dados do funcionário --");
            EmployeeService employeeService = new EmployeeService(employeeDAO);
            System.out.println(employeeService.readEmployee(sale.getIdEmploee()));
        }
        System.out.println("---- Dados da cliente ----");
        ClientService clientService = new ClientService(clientDAO);
        clientService.displayClientData(id);
    }

    public List<Sale> salesHistory() throws SQLException{
        List<Sale> sales = saleDAO.listAllSales();
        for(Sale sale: sales){
            sale.setTotalValue(saleItemService.calculateSubtotal(sale.getId()));
        }
        return sales;
    }
}
