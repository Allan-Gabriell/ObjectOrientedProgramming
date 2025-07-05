package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import conection.Conection;
import entity.Livro;

public class LivroDAO {

    public void criarLivro(Livro livro) {
        String sql = "INSERT INTO livro (ISBN, TITULO, AUTOR, EDICAO, GENERO, ESTOQUE, PRECO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = Conection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, livro.getIsbn());
            ps.setString(2, livro.getTitulo());
            ps.setString(3, livro.getAutor());
            ps.setInt(4, livro.getEdicao());
            ps.setString(5, livro.getGenero());
            ps.setInt(6, livro.getEstoque());
            ps.setDouble(7, livro.getPreco());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Livro lerLivro(int isbn) throws SQLException {
        String sql = "SELECT * FROM livro WHERE ISBN = ?";
        Livro livro = null;

        try (PreparedStatement ps = Conection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, isbn);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    livro = new Livro(
                        rs.getInt("ISBN"),
                        rs.getString("TITULO"),
                        rs.getString("AUTOR"),
                        rs.getInt("EDICAO"),
                        rs.getString("GENERO"),
                        rs.getInt("ESTOQUE"),
                        rs.getDouble("PRECO")
                    );
                }
            }
        }
        return livro;
    }

    public void atualizarLivroEstoque(int isbn, int novoEstoque) throws SQLException {
        String sql = "UPDATE livro SET ESTOQUE = ? WHERE ISBN = ?";
        try (PreparedStatement ps = Conection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, novoEstoque);
            ps.setInt(2, isbn);
            ps.executeUpdate();
            System.out.println("DAO: Estoque do livro ISBN " + isbn + " atualizado.");
        }
    }

    public void deletarLivro(int isbn) throws SQLException {
        String sql = "DELETE FROM livro WHERE ISBN = ?";
        try (PreparedStatement ps = Conection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, isbn);
            ps.executeUpdate();
            System.out.println("DAO: Livro ISBN " + isbn + " deletado.");
        }
    }

    public List<Livro> listarLivros() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro";

        try (PreparedStatement ps = Conection.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Livro livro = new Livro(
                    rs.getInt("ISBN"),
                    rs.getString("TITULO"),
                    rs.getString("AUTOR"),
                    rs.getInt("EDICAO"),
                    rs.getString("GENERO"),
                    rs.getInt("ESTOQUE"),
                    rs.getDouble("PRECO")
                );
                livros.add(livro);
            }
        }
        return livros;
    }
}
