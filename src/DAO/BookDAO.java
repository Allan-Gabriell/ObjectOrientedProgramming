package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import conection.Conection;
import entity.Book;

public class BookDAO {
    public void criarLivro(Book livro) {
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
    };

    // public void atualizarLivroEstoque(int isbn, int novoEstoque) throws SQLException {
    //     String sql = "UPDATE livro SET ESTOQUE = ? WHERE ISBN = ?";
    //     try (PreparedStatement ps = Conection.getConnection().prepareStatement(sql)) {
    //         ps.setInt(1, novoEstoque);
    //         ps.setInt(2, isbn);
    //         ps.executeUpdate();
    //         System.out.println("DAO: Estoque do livro ISBN " + isbn + " atualizado.");
    //     }
    // }

    // public void deletarLivro(int isbn) throws SQLException {
    //     String sql = "DELETE FROM livro WHERE ISBN = ?";
    //     try (PreparedStatement ps = Conection.getConnection().prepareStatement(sql)) {
    //         ps.setInt(1, isbn);
    //         ps.executeUpdate();
    //         System.out.println("DAO: Livro ISBN " + isbn + " deletado.");
    //     }
    // }

    // public List<Livro> listarLivros() throws SQLException {
    //     List<Livro> livros = new ArrayList<>();
    //     String sql = "SELECT * FROM livro";

    //     try (PreparedStatement ps = Conection.getConnection().prepareStatement(sql);
    //          ResultSet rs = ps.executeQuery()) {
    //         while (rs.next()) {
    //             Livro livro = new Livro(
    //                 rs.getInt("ISBN"),
    //                 rs.getString("TITULO"),
    //                 rs.getString("AUTOR"),
    //                 rs.getInt("EDICAO"),
    //                 rs.getString("GENERO"),
    //                 rs.getInt("ESTOQUE"),
    //                 rs.getDouble("PRECO")
    //             );
    //             livros.add(livro);
    //         }
    //     }
    //     return livros;
    // }
}
