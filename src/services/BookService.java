package services;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.BookDAO;
import entity.Book;

public class BookService {
    private BookDAO bookDAO;
    Scanner sc = new Scanner(System.in);
    
    public BookService(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    public void newBook(int isbn, String title, String autor, int edition, String gender, int stock, double price){
        Book book = new Book(isbn, title, autor, edition, gender, stock, price);
        bookDAO.createBook(book);
    }

    // Implementar ler livro aqui e na bookDao
    // Ajustar a classe que atualiza livro na bookDAO

    public void deleteBook(int isbn) throws SQLException {
        bookDAO.deleteBook(isbn);
    }
}
