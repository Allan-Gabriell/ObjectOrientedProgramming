package entity;

import java.util.Date;

public class Sale {
    private int id;
    private double totalValue;
    private Date date;
    private Client client;
    private Employee funcionario;

    // public Sale(int id, double totalValue, Date date) {
    //     this.id = id;
    //     this.totalValue = totalValue;
    //     this.date = date;
    // }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Employee funcionario) {
        this.funcionario = funcionario;
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
        return "Sale [id=" + id + ", totalValue=" + totalValue + ", date=" + date + ", client=" + client
                + ", funcionario=" + funcionario + "]";
    }

}
