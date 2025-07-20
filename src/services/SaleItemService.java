package services;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import DAO.BookDAO;
import DAO.SaleDAO;
import DAO.SaleItemDAO;
import entity.Book;
import entity.SaleItem;

public class SaleItemService {
    private SaleItemDAO saleItemDAO;
    private SaleDAO saleDAO;
    private BookDAO bookDAO;
    Scanner sc = new Scanner(System.in);


    public SaleItemService(SaleItemDAO saleItemDAO, SaleDAO saleDAO, BookDAO bookDAO) {
        this.saleItemDAO = saleItemDAO;
        this.saleDAO = saleDAO;
        this.bookDAO = bookDAO;
    }

    public void createSaleItem(int isbn) throws SQLException {
        int answer;
        int quantity;
        Book book = bookDAO.searcBookByISBNBook(isbn);
        if (book == null) {
            System.out.println("Livro não encontrado!");
            return;
        }
    
        SaleItem saleItem = new SaleItem();
        saleItem.setIsbn(isbn);
        System.out.println("O livro escolhido é: " + book.getTitle() + " valor: R$ " + book.getPrice());
    
        System.out.print("Informe a quantidade: ");
        quantity = sc.nextInt();
    
        if (quantity > book.getStock()) {
            System.out.println("Quantidade indisponível em estoque! Estoque atual: " + book.getStock());
            return;
        }
    
        saleItem.setQuantity(quantity);
        bookDAO.atualizeStockBook(isbn, book.getStock() - quantity);
        saleItem.setIdSale(saleDAO.getLastSale().getId());
        saleItemDAO.createSaleItem(saleItem);
    
        sc.nextLine();
    
        while (true) {
            System.out.print("Deseja adicionar um novo livro à compra? (1 - Sim, 2 - Não): ");
            try {
                answer = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Tente novamente. " + e.getMessage());
                continue;
            }
    
            if (answer != 1) {
                break;
            }
    
            List<Book> books = bookDAO.listBooks();
            for (Book b : books) {
                System.out.println(b);
            }
    
            System.out.print("Informe o ISBN do livro: ");
            int newIsbn = sc.nextInt();
            sc.nextLine();
    
            book = bookDAO.searcBookByISBNBook(newIsbn);
            if (book == null) {
                System.out.println("Livro não registrado!");
                continue;
            }
    
            SaleItem newItem = new SaleItem();
            newItem.setIsbn(newIsbn);
            System.out.println("O livro escolhido é: " + book.getTitle() + " valor: R$ " + book.getPrice());
    
            System.out.print("Informe a quantidade: ");
            quantity = sc.nextInt();
    
            if (quantity > book.getStock()) {
                System.out.println("Quantidade indisponível em estoque! Estoque atual: " + book.getStock());
                sc.nextLine();
                continue;
            }
    
            newItem.setQuantity(quantity);
            bookDAO.atualizeStockBook(newIsbn, book.getStock() - quantity);
            sc.nextLine();
    
            newItem.setIdSale(saleDAO.getLastSale().getId());
            saleItemDAO.createSaleItem(newItem);
        }
    
        System.out.println("Itens adicionados à venda com sucesso!");
    }
    

    public void dataDisplayItem() throws SQLException{
        List<SaleItem> saleItems = saleItemDAO.loadSaleItem();
        for(SaleItem saleItem : saleItems) {
            System.out.println(saleItem);
        }
    }

    public void deleteSaleItem(int id) throws SQLException {
        saleItemDAO.deleteSaleItem(id);
    }

    public double calculateSubtotal() throws SQLException {
        double subtotal = 0.0;
        List<SaleItem> saleItems = saleItemDAO.loadSaleItem();
    
        for (SaleItem item : saleItems) {
            double unitPrice = bookDAO.searcBookByISBNBook(item.getIsbn()).getPrice();
            subtotal += item.getQuantity() * unitPrice;
        }
    
        return subtotal;
    }
    

    public void editSaleItem(int id) throws SQLException {
        SaleItem currentItem = saleItemDAO.searchSaleItem(id);
        int Isbn = currentItem.getIsbn();
        int Quantity = currentItem.getQuantity();
    
        Book Book = bookDAO.searcBookByISBNBook(Isbn);
        if (Book != null) {
            bookDAO.atualizeStockBook(Isbn, Book.getStock() + Quantity);
        }

        System.out.print("Deseja mudar o livro? (1 - Sim, 2 - Não): ");
        int option = Integer.parseInt(sc.nextLine());
    
        int newIsbn = Isbn;
        Book newBook = Book;
    
        if (option == 1) {
            System.out.print("Informe o novo ISBN: ");
            newIsbn = Integer.parseInt(sc.nextLine());
            newBook = bookDAO.searcBookByISBNBook(newIsbn);
            if (newBook == null) {
                System.out.println("Livro não encontrado!");
                bookDAO.atualizeStockBook(Isbn, Book.getStock());
                return;
            }
        }

        System.out.print("Informe a nova quantidade: ");
        int newQuantity = Integer.parseInt(sc.nextLine());
    
        if (newBook.getStock() < newQuantity) {
            System.out.println("Estoque insuficiente para o livro com ISBN " + newIsbn);
            bookDAO.atualizeStockBook(Isbn, Book.getStock());
            return;
        }

        bookDAO.atualizeStockBook(newIsbn, newBook.getStock() - newQuantity);

        currentItem.setIsbn(newIsbn);
        currentItem.setQuantity(newQuantity);
        saleItemDAO.updateSaleItem(currentItem);
    
        System.out.println("Item de venda atualizado com sucesso.");
    }
    
}    