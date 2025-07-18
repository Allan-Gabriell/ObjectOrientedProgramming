package services;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import DAO.BookDAO;
import entity.Book;

public class BookService {
    private BookDAO bookDAO;
    Scanner sc = new Scanner(System.in);
    
    public BookService(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    public void createBook(int isbn, String title, String autor, int edition, String gender, int stock, double price){
        Book book = new Book(isbn, title, autor, edition, gender, stock, price);
        bookDAO.createBook(book);
    }

    public void readBook(int isbn) throws SQLException {
        Book book = bookDAO.searcBookByISBNBook(isbn);
        System.out.println(book);
    }

    public void updateBook(int isbn) throws SQLException {
        Book book = bookDAO.searcBookByISBNBook(isbn);
        System.out.print("Informe o titulo: ");
        book.setTitle(sc.nextLine());
        System.out.print("Informe o autor: ");
        book.setAuthor(sc.nextLine());
        System.out.print("Informe a edição: ");
        book.setEdition(sc.nextInt());
        sc.nextLine();
        System.err.print("Informe o gênero: ");
        book.setGender(sc.nextLine());
        System.out.print("Informe o estoque: ");
        book.setStock(sc.nextInt());
        System.out.print("Informe o preço: ");
        book.setPrice(sc.nextDouble());
        
        bookDAO.atualizeBook(book);
    }

    public void deleteBook(int isbn) throws SQLException {
        bookDAO.deleteBook(isbn);
    }

    public void displayBookData() throws SQLException{
        Book book = bookDAO.getLastBook();
        System.out.println(book);
    }

    public List<Book> listBooks(){
        try{
            return bookDAO.listBooks();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar a lista de livros no banco de dados: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Não foi possível carregar a lista de livros.");
        }
    }
}
