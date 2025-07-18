package entity;

import java.util.List;

public class SaleItem {
    private int id;
    private int quantity;
    private int idSale;
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
    public int getIdSale() {
        return idSale;
    }
    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }
    public int getIsbn() {
        return isbn;
    }
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "SaleItem [id=" + id + ", quantity=" + quantity + ", idSale=" + idSale + ", isbn=" + isbn + "]";
    }
}
