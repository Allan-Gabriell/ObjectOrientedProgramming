package menu;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.EmployeeDAO;
import entity.Employee;
import services.EmployeeService;

public class Menu {
    private EmployeeService employeeService;
    private Scanner sc;

    public Menu(EmployeeDAO employeeDAO) {
        this.employeeService = new EmployeeService(employeeDAO);
        this.sc = new Scanner(System.in);
    }

    public void menuEmployee() throws SQLException{
        int option;
        boolean startMenu = true;
        while (startMenu) {
            System.out.println("----- Área do funcionário -----");
            System.out.println("-------------------------------");
            System.out.println("1 - Visualizar funcionário     ");
            System.out.println("2 - Atualizar funcionário      ");
            System.out.println("3 - Deletar funcionário        ");
            System.out.println("4 - Listar Empregados          ");
            System.out.println("0 - Retornar Menu              ");
            System.out.print("Escolha uma opção: ");
            try {
                option = Integer.parseInt(sc.nextLine());
                switch (option) {
                    case 1:
                        System.out.print("Informe o ID do funcionário: ");
                        int id = Integer.parseInt(sc.nextLine());
                        Employee employee = employeeService.readEmployee(id);
                        System.out.println(employee);
                        break;
                    case 2:
                        System.out.print("Informe o ID do funcionário: ");
                        int id2 = Integer.parseInt(sc.nextLine());
                        employeeService.updateEmployee(id2);
                        break;
                    case 3:
                        // ADM
                        System.out.print("Informe o ID do funcionário: ");
                        int id3 = Integer.parseInt(sc.nextLine());
                        employeeService.deleteEmployee(id3);
                        break;
                    case 4:
                        // ADM
                        System.out.println("\n--- Lista de Funcionários ---");
                        employeeService.listEmployees();
                        break;
                    case 0:
                        System.out.println("Saindo de Menu Funcionário");
                        startMenu = false;
                        break;
                    default:
                        System.out.println("Opção Inválida, digite um número válido");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, digite um número.");
            }
        }

    }
}
