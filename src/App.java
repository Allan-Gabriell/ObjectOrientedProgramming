import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import DAO.ClientDAO;
import DAO.EmployeeDAO;
import DAO.BookDAO;
import DAO.SaleDAO;
import DAO.SaleItemDAO;
import entity.Client;
import entity.Employee;
import entity.Book;
import entity.Sale;
import entity.SaleItem;
import menu.Menu;
import services.BookService;
import services.ClientService;
import services.EmployeeService;
import services.SaleItemService;
import services.SaleService;

public class App {
    public static void main(String[] args) throws Exception {
        final ClientDAO clientDAO = new ClientDAO();
        final EmployeeDAO employeeDAO = new EmployeeDAO();
        final EmployeeService employeeService = new EmployeeService(employeeDAO);
        final BookDAO bookDAO = new BookDAO();
        final SaleItemDAO saleItemDAO = new SaleItemDAO();
        final SaleDAO saleDAO = new SaleDAO();
        final Menu menu = new Menu(employeeDAO);
        Scanner sc = new Scanner(System.in);
        SaleItemService saleItemService = new SaleItemService(saleItemDAO, saleDAO, bookDAO);
        BookService bookService = new BookService(bookDAO);

        SaleService saleService = new SaleService(saleDAO, employeeDAO, saleItemDAO, bookDAO, clientDAO);
        List<Sale> sales = saleService.salesHistory();
        for(Sale sale: sales){
            System.out.println(sale);
        }

        // int option = 0;
        // boolean validOption = false;

        // while (!validOption) {
        //     System.out.println("---------- Bem-vindo a Sorvil ----------");
        //     System.out.println("  Sistema de gerenciamento de livrarias ");
        //     System.out.println("----------------------------------------");
        //     System.out.println(" 1 - Cadastrar funcionário");
        //     System.out.println(" 2 - Realizar login");
        //     System.out.println("----------------------------------------");
        //     System.out.print("Escolha uma opção: ");

        //     try {
        //         option = Integer.parseInt(sc.nextLine());

        //         if (option == 1 || option == 2) {
        //             validOption = true;
        //         } else {
        //             System.out.println("Opção inválida! Digite 1 ou 2.");
        //         }
        //     } catch (NumberFormatException e) {
        //         System.out.println("Entrada inválida! Por favor, digite um número (1 ou 2).");
        //     }
        // }

        // try {
        //     if (option == 1) {
        //         System.out.print("Informe o nome do funcionário: ");
        //         String name = sc.nextLine();
        //         System.out.print("Informe o e-mail: ");
        //         String email = sc.nextLine();

        //         boolean validAdmin = false;
        //         boolean admin = false;
        //         while (!validAdmin) {
        //             System.out.print("Informe se é admin: (1 - Sim, 2 - Não): ");
        //             try {
        //                 int option2 = Integer.parseInt(sc.nextLine());
        //                 if (option2 == 1 || option2 == 2) {
        //                     admin = (option2 == 1);
        //                     validAdmin = true;
        //                 } else {
        //                     System.out.println("Digite 1 para Sim ou 2 para Não.");
        //                 }
        //             } catch (NumberFormatException e) {
        //                 System.out.println("Entrada inválida! Digite 1 ou 2.");
        //             }
        //         }

        //         System.out.print("Informe a senha: ");
        //         String password = sc.nextLine();

        //         employeeService.createEmployee(name, email, admin, password);

        //     } else if (option == 2) {
        //         System.out.print("Informe o e-mail: ");
        //         String email = sc.nextLine();
        //         System.out.print("Informe a senha: ");
        //         String password = sc.nextLine();

        //         employeeService.logIn(email, password);
        //     }

        // } catch (Exception e) {
        //     System.out.println("Erro ao executar a operação: " + e.getMessage());
        // }

        // System.out.println("------------ Menu principal ------------");
        // System.out.println("----------------------------------------");
        // System.out.println(" 1 - Área do funcionário");
        // System.out.println(" 2 - Área do cliente");
        // System.out.println(" 3 - Área de vendas");
        // System.out.println("----------------------------------------");
        // System.out.print("Escolha uma opção: ");
        // option = Integer.parseInt(sc.nextLine());
        // switch(option){
        //     case 1:
        //         menu.menuEmployee();
        // }
        sc.close();
    }
}