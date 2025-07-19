package menu;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.EmployeeDAO;
import entity.Employee;
import services.EmployeeService;

public class Menu {
    private EmployeeService employeeService;

    
    public Menu(EmployeeDAO employeeDAO) {
        this.employeeService = new EmployeeService(employeeDAO);
    }


    public void menuEmployee() throws SQLException{
        int option;
        Scanner sc = new Scanner(System.in);

        System.out.println("----- Área do funcionário -----"); 
        System.out.println("-------------------------------");
        System.out.println("1 - Visualizar funcionário     ");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.print("Escolha uma opção: ");
        option = Integer.parseInt(sc.nextLine());

        switch(option){
            case 1:
                System.out.print("Informe o ID do funcionário: ");
                int id = Integer.parseInt(sc.nextLine());
                Employee employee = employeeService.readEmployee(id);
                System.out.println(employee);
        }


    }
}
