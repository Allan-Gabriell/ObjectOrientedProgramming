package entity;

import java.util.Date;

public class Sale {
    private int id;
    private double totalValue;
    private Date date;
    private int idClient;
    private int idEmploee;

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdEmploee() {
        return idEmploee;
    }

    public void setIdEmploee(int idEmploee) {
        this.idEmploee = idEmploee;
    }

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

    @Override
    public String toString() {
        return "Venda ID: " + id + ", valor total: " + String.format("%.2f", totalValue) + ", data: " + date + ", ID cliente: " + idClient
                + ", ID funcionário: " + idEmploee;
    }
}
