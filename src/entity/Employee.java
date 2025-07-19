package entity;

public class Employee {
    private int id;
    private String name;
    private String email;
    private boolean admin;
    private String password;
    
    public Employee(String name, String email, boolean admin, String password) {
        this.name = name;
        this.email = email;
        this.admin = admin;
        this.password = password;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Funcionário " + id + ": Nome: " + name + ", E-mail: " + email + ", Admin: " + admin + ", Password: "
                + password;
    }
}
