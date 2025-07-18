import java.util.ArrayList;
import java.util.List;

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
        final SaleItemDAO saleItemDAO = new SaleItemDAO();
        final SaleDAO saleDAO = new SaleDAO();
        final SaleItemService saleItemService = new SaleItemService(saleItemDAO, saleDAO, bookDAO);
        SaleService saleService = new SaleService(saleDAO);

        saleService.updateSale(7);
        // saleItemService.createSaleItem(124);
        // saleItemService.dataDisplayItem();
        // saleItemService.editSaleItem(4);
        // saleItemService.deleteSaleItem(13);
        // saleItemService.dataDisplayItem();
        // System.out.println("Valor total da compra: R$ " + saleItemService.calculateSubtotal());
        // System.out.println(saleDAO.getLastSale().getId());
        
        // ClientService clientService = new ClientService(clientDAO);
        // EmployeeService employeeService = new EmployeeService(employeeDAO);
        // // employeeService.createEmployee("Allan", "allan@", true, "123#");

        // // employeeService.logIn("allan@", "123");
        // System.out.println(employeeService.readEmployee(14));

        // BookService bookService = new BookService(bookDAO);
        // bookService.newBook(124, "Dom Casmurro", "Machado de Assis", 1, "Romance", 100, 30);
        // bookService.readBook(124);
        // bookService.updateBook(124);
        // bookService.displayBookData();
        // List<Book> books = new ArrayList<>();
        // books = bookService.listBooks();

        // for(Book book: books){
        //     System.out.println(book);
        // }
        // bookService.deleteBook(124);
        // // employeeService.newEmployee("Maria", "maria@", false, "1234");
        // employeeService.displayEmployeeData();
        // employeeService.deletedEmployee(4);
        // List<Employee> employees = new ArrayList<>();
        // employees = employeeService.listEmployees();
        // for(Employee employee: employees){
        //     System.out.println(employee);
        // }
        // clientService.newClient("Wanderson Carvalho", "789456964");
        // clientService.newClient("Jonas", "13579086426");
        // List<Client> clients = clientService.getAllClients();
        // for(Client client: clients){
        //     System.out.println(client);
        // }

        // FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        // BookDAO bookDAO = new BookDAO();

        // Sale sale = new Sale();
        // sale.setDate(new java.util.Date());
        // sale.setClient(clientDAO.searchClientById(14)); // coloque um ID válido
        // sale.setFuncionario(funcionarioDAO.searchEmployee(1));
        // new SaleDAO().resgisterSale(sale);

        // Book book1 = bookDAO.searcBookByISBNBook(123456);
        // Book book2 = bookDAO.searcBookByISBNBook(1234456);

        // SaleItem saleItem = new SaleItem();
        // saleItem.setQuantity(2);
        // saleItem.setSale(sale);

        // List<Book> books = new ArrayList<>();
        // books.add(book1);
        // books.add(book2);

        // saleItem.setBooks(books);
        // new SaleItemDAO().resgisterSaleItem(saleItem);

        // Funcionario funcionario = new Funcionario();
        // funcionario.setNome("teste");
        // funcionario.setEmail("teste@");
        // funcionario.setAdmin(false);
        // funcionario.setSenha("yuhj");
        // new FuncionarioDAO().registerFuncionario(funcionario);

        // Sale sale = new Sale();Funcionario funcionario = new Funcionario();
        // funcionario.setNome("teste");
        // funcionario.setEmail("teste@");
        // funcionario.setAdmin(false);
        // funcionario.setSenha("yuhj");
        // new FuncionarioDAO().registerFuncionario(funcionario);

        // Sale sale = new Sale();
        // sale.setDate(new java.util.Date());
        // sale.setClient(clientDAO.searchClientById(14));
        // sale.setFuncionario(new FuncionarioDAO().searchEmployee(1));

        // SaleDAO saleDAO = new SaleDAO();
        // saleDAO.resgisterSale(sale);

        // System.out.println(sale);
        // sale.setDate(new java.util.Date());
        // sale.setClient(clientDAO.searchClientById(14));
        // sale.setFuncionario(new FuncionarioDAO().searchEmployee(1));

        // SaleDAO saleDAO = new SaleDAO();
        // saleDAO.resgisterSale(sale);

        // System.out.println(sale);
        
        // clientService.displayClient(12);

        // Funcionario funcionario = new Funcionario();
        // funcionario.setNome("Allan");
        // funcionario.setEmail("allang@gmail.com");
        // funcionario.setAdmin(true);
        // funcionario.setSenha("allanf#123");
        // new FuncionarioDAO().registerFuncionario(funcionario);
        // Livro livro = new Livro();
        // livro.setIsbn(963258741);
        // livro.setTitle("Harryr Potter");
        // livro.setAuthor("anonimo");
        // livro.setEdition(3);
        // livro.setGender("Fantasia");
        // livro.setPrice(3.75);
        // livro.setStock(8);
        // new LivroDAO().criarLivro(livro);
        
        // clientService.deletedClient(4);

        // Sale sale = new Sale();
        // FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        // sale.setDate(new java.util.Date());
        // // sale.setFuncionario(funcionario);
        // sale.setClient(clientDAO.searchClientById(12));
        // sale.setFuncionario(funcionarioDAO.searchEmployee(1));
        // new SaleDAO().resgisterSale(sale);

        // System.out.println(sale);


    }
}