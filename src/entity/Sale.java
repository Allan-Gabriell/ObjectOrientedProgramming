package entity;

import java.util.Date;

public class Sale {
    private int id;
    private double totalValue;
    private Date date;
    private int idCliente;
    private int idFuncionario;

    // public Sale(int id, double totalValue, Date date) {
    //     this.id = id;
    //     this.totalValue = totalValue;
    //     this.date = date;
    // }


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
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

    @Override
    public String toString() {
        return "Venda ID: " + id + ", valor total: " + String.format("%.2f", totalValue) + ", data: " + date + ", ID cliente: " + idCliente
                + ", ID funcionário" + idFuncionario;
    }
}
