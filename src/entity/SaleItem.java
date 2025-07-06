package entity;

public class SaleItem {
    private int id;
    private int quantity;

    // Constructor
    public SaleItem(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    // Get and Set
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

    // ToString
    @Override
    public String toString() {
        return "SaleItem{" +  "id=" + id +  ", quantity=" + quantity +  '}';
    }
}
