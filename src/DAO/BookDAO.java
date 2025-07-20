package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import conection.Conection;
import entity.Book;

public class BookDAO {
    
    public void createBook (Book book) {
        String sql = "INSERT INTO livro (ISBN_LIVRO, TITULO_LIVRO, AUTOR_LIVRO, EDICAO_LIVRO, GENERO_LIVRO, ESTOQUE_LIVRO, PRECO_LIVRO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = Conection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, book.getIsbn());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setInt(4, book.getEdition());
            ps.setString(5, book.getGender());
            ps.setInt(6, book.getStock());
            ps.setDouble(7, book.getPrice());
            ps.execute();
            System.out.println("DAO: Livro criado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book searcBookByISBNBook(int isbn) throws SQLException{
        String slq = "SELECT ISBN_LIVRO, TITULO_LIVRO, AUTOR_LIVRO, EDICAO_LIVRO, GENERO_LIVRO, ESTOQUE_LIVRO, PRECO_LIVRO FROM livro WHERE ISBN_LIVRO = ?";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Book book = null;

        try {
            ps = Conection.getConnection().prepareStatement(slq);
            ps.setInt(1, isbn);

            resultSet = ps.executeQuery();
            if(resultSet.next()){
                book = new Book();
                book.setIsbn(resultSet.getInt("ISBN_LIVRO"));
                book.setTitle(resultSet.getString("TITULO_LIVRO"));
                book.setAuthor(resultSet.getString("AUTOR_LIVRO"));
                book.setEdition(resultSet.getInt("EDICAO_LIVRO"));
                book.setGender(resultSet.getString("GENERO_LIVRO"));
                book.setStock(resultSet.getInt("ESTOQUE_LIVRO"));
                book.setPrice(resultSet.getDouble("PRECO_LIVRO"));
            }
        } finally {
            if(resultSet != null) resultSet.close();
            if(ps != null) ps.close();
        }

        return book;
    }

    public void atualizeStockBook(int isbn, int newStock) throws SQLException {
        String sql = "UPDATE livro SET estoque_livro = ? WHERE isbn_livro = ?";
        PreparedStatement ps = null;
    
        try {
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setInt(1, newStock);
            ps.setInt(2, isbn);
            int rowsUpdated = ps.executeUpdate();
    
            if (rowsUpdated > 0) {
                System.out.println("Estoque atualizado com sucesso para o livro de ISBN " + isbn);
            } else {
                System.out.println("Livro não encontrado para atualizar estoque.");
            }
        } finally {
            if (ps != null) ps.close();
        }
    }
    

    public void atualizeBook (Book book) throws SQLException{
        String sql = "UPDATE livro SET TITULO_LIVRO = ?, AUTOR_LIVRO = ?, EDICAO_LIVRO = ?, GENERO_LIVRO = ?, ESTOQUE_LIVRO = ?, PRECO_LIVRO = ? WHERE ISBN_LIVRO = ?"; 
        PreparedStatement ps = null;

        try {
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getEdition());
            ps.setString(4, book.getGender());
            ps.setInt(5, book.getStock());
            ps.setDouble(6, book.getPrice());
            ps.setInt(7, book.getIsbn());

            ps.executeUpdate();
            System.out.println("DAO: Livro ISBN " + book.getIsbn() + " atualizado com sucesso");
        } finally {
            if(ps != null) ps.close();
        }
    }

    public void deleteBook (int isbn) throws SQLException{
        String sql = "DELETE FROM livro WHERE ISBN_LIVRO = ?";
        PreparedStatement ps = null;
        try{
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setInt(1, isbn);

            ps.executeUpdate();
            System.out.println("DAO: Livro com ISBN " + isbn + " deletado com sucesso");
        } finally {
            if(ps != null) ps.close();
        }
    }

    public List<Book> listBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
         String sql = "SELECT * FROM livro";

        try (PreparedStatement ps = Conection.getConnection().prepareStatement(sql);
              ResultSet rs = ps.executeQuery()) {
             while (rs.next()) {
                 Book book = new Book();
                 book.setIsbn(rs.getInt("ISBN_LIVRO"));
                 book.setTitle(rs.getString("TITULO_LIVRO"));
                 book.setAuthor(rs.getString("AUTOR_LIVRO"));
                 book.setEdition(rs.getInt("EDICAO_LIVRO"));
                 book.setGender(rs.getString("GENERO_LIVRO"));
                 book.setStock(rs.getInt("ESTOQUE_LIVRO"));
                 book.setPrice(rs.getDouble("PRECO_LIVRO"));
                 books.add(book);
             }
         }
         return books;
     }
    
    //para essa função funcionar corretamente tenho que adicionar um id em livro
    public Book getLastBook() throws SQLException {
        String sql = "SELECT * FROM livro ORDER BY ISBN_LIVRO DESC LIMIT 1";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Book book = null;

        try{
            ps = Conection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();

            if(rs.next()) {
                book = new Book();
                book.setIsbn(rs.getInt("ISBN_LIVRO"));
                book.setTitle(rs.getString("TITULO_LIVRO"));
                book.setAuthor(rs.getString("AUTOR_LIVRO"));
                book.setEdition(rs.getInt("EDICAO_LIVRO"));
                book.setGender(rs.getString("GENERO_LIVRO"));
                book.setStock(rs.getInt("ESTOQUE_LIVRO"));
                book.setPrice(rs.getDouble("PRECO_LIVRO"));
            }
        } finally {
            if(ps != null) ps.close();
            if(rs != null) rs.close();
        }

        return book;
    }
}