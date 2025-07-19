import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
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
import services.BookService;
import services.ClientService;
import services.EmployeeService;
import services.SaleItemService;
import services.SaleService;

public class App {
    public static void main(String[] args) throws Exception {
        final ClientDAO clientDAO = new ClientDAO();

        ClientService clientService = new ClientService(clientDAO);

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            try {
                System.out.println("\n===== MENU PRINCIPAL =====");
                System.out.println("1. Cliente");
                System.out.println("2. Funcionário");
                System.out.println("3. Livro");
                System.out.println("4. Venda");
                System.out.println("5. Item de Venda");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1:
                        menuCliente(sc, clientService);
                        break;
                    case 2:
                        menuFuncionario(sc);
                        break;
                    case 3:
                        menuLivro(sc);
                        break;
                    case 4:
                        menuVenda(sc);
                        break;
                    case 5:
                        menuItemVenda(sc);
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

    static void menuCliente(Scanner sc, ClientService clientService) throws SQLException {
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
                // sc.nextLine();
                opcao = -1; 
            }
        } while (opcao != 0);
    }

    static void menuFuncionario(Scanner scanner) {
        int opcao = -1;
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
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        // implementar cadastro
                        break;
                    case 2:
                        // implementar listar
                        break;
                    case 3:
                        // implementar buscar
                        break;
                    case 4:
                        // implementar atualizar
                        break;
                    case 5:
                        // implementar remover
                        break;
                    case 0:
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                scanner.nextLine();
                opcao = -1;
            }
        } while (opcao != 0);
    }

    static void menuLivro(Scanner scanner) {
        int opcao = -1;
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
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        // implementar cadastro
                        break;
                    case 2:
                        // implementar listar
                        break;
                    case 3:
                        // implementar buscar
                        break;
                    case 4:
                        // implementar atualizar
                        break;
                    case 5:
                        // implementar remover
                        break;
                    case 0:
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                scanner.nextLine();
                opcao = -1;
            }
        } while (opcao != 0);
    }

    static void menuVenda(Scanner scanner) {
        int opcao = -1;
        do {
            try {
                System.out.println("\n--- Menu Venda ---");
                System.out.println("1. Registrar venda");
                System.out.println("2. Listar vendas");
                System.out.println("3. Buscar vendas por cliente");
                System.out.println("4. Excluir venda");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        // implementar registrar venda
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
                scanner.nextLine();
                opcao = -1;
            }
        } while (opcao != 0);
    }

    static void menuItemVenda(Scanner scanner) {
        int opcao = -1;
        do {
            try {
                System.out.println("\n--- Menu ItemVenda ---");
                System.out.println("1. Adicionar item à venda");
                System.out.println("2. Listar itens de uma venda");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        // implementar adicionar item
                        break;
                    case 2:
                        // implementar listar itens
                        break;
                    case 0:
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                scanner.nextLine();
                opcao = -1;
            }
        } while (opcao != 0);
    }
}


