package entity;

import java.util.List;

public class SaleItem {
    private int id;
    private int quantity;
    private int idVenda;
    private int isbn;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getIdVenda() {
        return idVenda;
    }
    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }
    public int getIsbn() {
        return isbn;
    }
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "SaleItem [id=" + id + ", quantity=" + quantity + ", idVenda=" + idVenda + ", isbn=" + isbn + "]";
    }
}
