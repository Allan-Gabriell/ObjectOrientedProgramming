package entity;

import java.sql.Date;

public class Sale {
    private int id;
    private double totalValue;
    private Date date;

    // Constructor
    public Sale(int id, double totalValue, Date date) {
        this.id = id;
        this.totalValue = totalValue;
        this.date = date;
    }

    // Get and Set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    // To String
    @Override
    public String toString() {
        return "Sale{" +  "id=" + id +  ", totalValue=" + totalValue +  ", date=" + date +  '}';
    }
}
