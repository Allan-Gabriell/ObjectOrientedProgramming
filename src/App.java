import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Date;


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
import services.BookService;
import services.ClientService;
import services.EmployeeService;
import services.SaleItemService;
import services.SaleService;

public class App {
    public static void main(String[] args) throws Exception {
        final ClientDAO clientDAO = new ClientDAO();
        final EmployeeDAO employeeDAO = new EmployeeDAO();
        final BookDAO bookDAO = new BookDAO();

        ClientService clientService = new ClientService(clientDAO);
        EmployeeService employeeService = new EmployeeService(employeeDAO);
        BookService bookService = new BookService(bookDAO);


        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            try {
                System.out.println("\n===== MENU PRINCIPAL =====");
                System.out.println("1. Cliente");
                System.out.println("2. Funcionário");
                System.out.println("3. Livro");
                System.out.println("4. Venda");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1:
                        menuClient(sc, clientService);
                        break;
                    case 2:
                        menuEmployee(sc, employeeService);
                        break;
                    case 3:
                        menuBook(sc, bookService);
                        break;
                    case 4:
                        menuSale(sc);
                        break;
                    case 0:
                        System.out.println("Encerrando...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                sc.nextLine(); 
                opcao = -1; 
            }
        } while (opcao != 0);

        sc.close();
    }

    static void menuClient(Scanner sc, ClientService clientService) throws SQLException {
        int opcao = -1;
        int id;
        do {
            try {
                System.out.println("\n--- Menu Cliente ---");
                System.out.println("1. Cadastrar cliente");
                System.out.println("2. Listar clientes");
                System.out.println("3. Buscar cliente por ID");
                System.out.println("4. Atualizar cliente");
                System.out.println("5. Remover cliente");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1:
                        String name;
                        String cpf;
                        System.out.print("Informe o nome do cliente: ");
                        name = sc.nextLine();
                        System.out.print("Informe o CPF do cliente: ");
                        cpf = sc.nextLine();
                        clientService.createClient(name, cpf);
                        break;
                    case 2:
                        List<Client> clients = clientService.listClients();
                        for(Client client: clients){
                            System.out.println(client);
                        }
                        break;
                    case 3:
                        System.out.print("Informe o ID do cliente: ");
                        id = Integer.parseInt(sc.nextLine());
                        clientService.displayClientData(id);
                        break;
                    case 4:
                        List<Client> clients2 = clientService.listClients();
                        for(Client client: clients2){
                            System.out.println(client);
                        }
                        System.out.print("Informe o ID do cliente: ");
                        id = Integer.parseInt(sc.nextLine());
                        clientService.updateClient(id);
                        break;
                    case 5:
                        List<Client> clients3 = clientService.listClients();
                        for(Client client: clients3){
                            System.out.println(client);
                        }
                       System.out.print("Informe o ID do cliente: ");
                       id = Integer.parseInt(sc.nextLine());
                       try {
                        clientService.deletedClient(id);
                       } catch(SQLIntegrityConstraintViolationException e){
                            System.out.println("ALERTA! Somente clientes que não contém ainda vendas podem ser deletados do banco de dados.");
                       }
                        break;
                    case 0:
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                opcao = -1; 
            }
        } while (opcao != 0);
    }

    static void menuEmployee(Scanner sc, EmployeeService employeeService) throws SQLException {
        int opcao = -1;
        int admin;
        int id;
        boolean admini = false;
        do {
            try {
                System.out.println("\n--- Menu Funcionário ---");
                System.out.println("1. Cadastrar funcionário");
                System.out.println("2. Listar funcionários");
                System.out.println("3. Buscar funcionário por ID");
                System.out.println("4. Atualizar funcionário");
                System.out.println("5. Remover funcionário");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.print("Informe o nome do funcionário: ");
                        String name = sc.nextLine();
                        System.out.print("Informe o e-mail: ");
                        String email = sc.nextLine();
                        System.out.print("O funcionário é administrador? (1 - Sim, 2 - Não) ");
                        try {
                            admin = Integer.parseInt(sc.nextLine());
                            if(admin == 1) admini = true;
                        } catch(InputMismatchException e){
                            System.out.println("Entrada inválida! Por favor, insira um número.");
                        }
                        System.out.print("Informe o a senha: ");
                        String password = sc.nextLine();
                        employeeService.createEmployee(name, email, admini, password);
                    case 2:
                        List<Employee> employees = employeeService.listEmployees();
                        for(Employee employee : employees){
                            System.out.println(employee);
                        }
                        break;
                    case 3:
                        System.out.print("Informe o ID do funcionário: ");
                        id = Integer.parseInt(sc.nextLine());
                        System.out.println(employeeService.readEmployee(id));
                        break;
                    case 4:
                        List<Employee> employees1 = employeeService.listEmployees();
                        for(Employee employee : employees1){
                            System.out.println(employee);
                        }
                        System.out.print("Informe o ID do funcionário: ");
                        id = Integer.parseInt(sc.nextLine());
                        employeeService.updateEmployee(id);
                        break;
                    case 5:
                        List<Employee> employees2 = employeeService.listEmployees();
                        for(Employee employee : employees2){
                            System.out.println(employee);
                        }
                        System.out.print("Informe o ID do funcionário: ");
                        id = Integer.parseInt(sc.nextLine());
                        employeeService.deleteEmployee(id);
                        break;
                    case 0:
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                sc.nextLine();
                opcao = -1;
            }
        } while (opcao != 0);
    }

    static void menuBook(Scanner sc, BookService bookService) throws SQLException {
        int opcao = -1;
        int isbn;
        int edition;
        int stock;
        double price;

        do {
            try {
                System.out.println("\n--- Menu Livro ---");
                System.out.println("1. Cadastrar livro");
                System.out.println("2. Listar livros");
                System.out.println("3. Buscar livro por ISBN");
                System.out.println("4. Atualizar livro");
                System.out.println("5. Remover livro");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("Informe o ISBN: ");
                        isbn = Integer.parseInt(sc.nextLine());
                        System.out.println("Informe o Título: ");
                        String title = sc.nextLine();
                        System.out.println("Informe o(a) Autor(a): ");
                        String author = sc.nextLine();
                        System.out.println("Informe a Edição: ");
                        edition = Integer.parseInt(sc.nextLine());
                        System.out.println("Informe o genero do livro: ");
                        String gender = sc.nextLine();
                        System.out.println("Informe a quantidade em estoque: ");
                        stock = Integer.parseInt(sc.nextLine());
                        System.out.println("Informe o preço: ");
                        price = Double.parseDouble(sc.nextLine());
                        bookService.createBook(isbn, title, author, edition, gender, stock, price);
                        System.out.println ("ISBN: " + isbn + " Titulo: " + title+ " Autor:" + author +" Edition" + edition + " Gendero" + gender + " Estoque: " + stock + " Preço: " + price);
                        break;
                    case 2:
                        List<Book> books= bookService.listBooks();
                        for(Book book: books){
                            System.out.println(book);
                        }
                        break;
                    case 3:
                        System.out.println("Informe o ISBN do Livro que deseja buscar");
                        isbn = Integer.parseInt(sc.nextLine());
                        bookService.readBook(isbn);
                        break;
                    case 4:
                        System.out.println("Informe o ISBN do Livro que deseja atualizar");
                        isbn = Integer.parseInt(sc.nextLine());
                        bookService.updateBook(isbn);
                        break;
                    case 5:
                        System.out.println("Informe o ISBN do Livro que deseja remover");
                        isbn = Integer.parseInt(sc.nextLine());
                        bookService.deleteBook(isbn);
                        break;
                    case 0:
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                sc.nextLine();
                opcao = -1;
            }
        } while (opcao != 0);
    }

    static void menuSale(Scanner sc) throws SQLException {
        int opcao = -1;
        int id;

        do {
            try {
                System.out.println("\n--- Menu Venda ---");
                System.out.println("1. Registrar venda");
                System.out.println("2. Listar vendas");
                System.out.println("3. Buscar vendas por cliente");
                System.out.println("4. Excluir venda");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        // implementar Registrar venda
                        break;
                    case 2:
                        // implementar listar vendas
                        break;
                    case 3:
                        // implementar buscar vendas
                        break;
                    case 4:
                        // implementar excluir venda
                        break;
                    case 0:
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                sc.nextLine();
                opcao = -1;
            }
        } while (opcao != 0);
    }
}