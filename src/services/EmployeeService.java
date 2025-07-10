package services;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import DAO.EmployeeDAO;
import entity.Employee;

public class EmployeeService {
    private EmployeeDAO employeeDAO;
    Scanner sc = new Scanner(System.in);

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void newEmployee(String name, String email, boolean admin, String password){
        Employee employee = new Employee(name, email, admin, password);
        employeeDAO.registerEmployee(employee);
    }

    public List<Employee> listEmployees(){
        try{
            return employeeDAO.loadEmployees();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar a lista de funcionários. " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Não foi possível carregar a lista de funcionarios.");
        }
    }

    // o que seria a função ler funcionário?
    //  falta a lunção de loguin

    public void updateEmployee(int id) throws SQLException{ 
        Employee employee = new Employee();
        employee = employeeDAO.searchEmployee(id);

        System.out.print("Informe o nome do funcionário: ");
        employee.setName(sc.nextLine());
        System.out.print("Informe o e-mail: ");
        employee.setEmail(sc.nextLine());
        System.out.print("O cliente ainda vai ser administrador (1 - Sim, 2 - Não)? ");
        int result = sc.nextInt();
        sc.nextLine();
        if(result == 1){
            employee.setAdmin(true);
        } else if(result == 2){
            employee.setAdmin(false);
        } else {
            System.out.println("Valor inválido");
        }
        System.out.print("Informe a nova senha: ");
        employee.setPassword(sc.nextLine());

        employeeDAO.updateEmployee(employee);
    }

    public void deletedEmployee(int id) throws SQLException{
        Employee employee = employeeDAO.searchEmployee(id);
        employeeDAO.deletedEmployee(employee);
    }

    public void displayEmployeeData() throws SQLException{
        Employee employee = employeeDAO.getLastEmployee();
        System.out.println(employee);
    }
}
