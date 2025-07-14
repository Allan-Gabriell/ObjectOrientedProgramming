package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import conection.Conection;
import entity.Book;

public class BookDAO {
    public void createBook (Book livro) {
        String sql = "INSERT INTO livro (ISBN_LIVRO, TITULO_LIVRO, AUTOR_LIVRO, EDICAO_LIVRO, GENERO_LIVRO, ESTOQUE_LIVRO, PRECO_LIVRO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = Conection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, livro.getIsbn());
            ps.setString(2, livro.getTitle());
            ps.setString(3, livro.getAuthor());
            ps.setInt(4, livro.getEdition());
            ps.setString(5, livro.getGender());
            ps.setInt(6, livro.getStock());
            ps.setDouble(7, livro.getPrice());
            ps.execute();
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

    public void atualizeBookStock (int isbn, int newStock) throws SQLException{
        String sql = "UPDATE livro SET ESTOQUE = ? WHERE ISBN = ?";
             try (PreparedStatement ps = Conection.getConnection().prepareStatement(sql)) {
                ps.setInt(1, newStock);
                 ps.setInt(2, isbn);
                 ps.executeUpdate();
                 System.out.println("DAO: O nNovo estoque: " +newStock+ ", Foi atribuido ao livro de ISBN " + isbn);
             }
    }

    public void deleteBook (int isbn) throws SQLException{
        String sql = "DELETE FROM livro WHERE ISBN_LIVRO = ?";
        try (Connection conn = Conection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, isbn);
            ps.executeUpdate();
            System.out.println("DAO: Livro com ISBN " + isbn + "deletado com sucesso");
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
}
