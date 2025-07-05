   package entity;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private int isbn;
    private String titulo;
    private String autor;
    private int edicao;
    private String genero;
    private int estoque;
    private double preco;

    private static List<Livro> livros = new ArrayList<>();

    public Livro() {}

    public Livro(int isbn, String titulo, String autor, int edicao, String genero, int estoque, double preco) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.edicao = edicao;
        this.genero = genero;
        this.estoque = estoque;
        this.preco = preco;
    }

    public static void criarLivro(int isbn, String titulo, String autor, int edicao, String genero, int estoque, double preco) {
        Livro livro = new Livro(isbn, titulo, autor, edicao, genero, estoque, preco);
        livros.add(livro);
    }

    public static Livro lerLivro(int isbn) {
        for (Livro livro : livros) {
            if (livro.getIsbn() == isbn) {
                return livro;
            }
        }
        return null;
    }

    public static void atualizarLivro(int isbn, int novoEstoque) {
        Livro livro = lerLivro(isbn);
        if (livro != null) {
            livro.setEstoque(novoEstoque);
        }
    }

    public static void deletarLivro(int isbn) {
        livros.removeIf(livro -> livro.getIsbn() == isbn);
    }

    public void exibirDadosLivro() {
        System.out.println(this);
    }

    public static List<Livro> listarLivros() {
        return livros;
    }

    @Override
    public String toString() {
        return "Livro [isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor +
                ", edicao=" + edicao + ", genero=" + genero + ", estoque=" + estoque +
                ", preco=" + preco + "]";
    }

    // Getters e Setters
    public int getIsbn() { return isbn; }
    public void setIsbn(int isbn) { this.isbn = isbn; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public int getEdicao() { return edicao; }
    public void setEdicao(int edicao) { this.edicao = edicao; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public int getEstoque() { return estoque; }
    public void setEstoque(int estoque) { this.estoque = estoque; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
}
