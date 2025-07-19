package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conection.Conection;
import entity.Employee;

public class EmployeeDAO {
    public void registerEmployee(Employee employee){
        String sql = "INSERT INTO funcionario (NOME_FUNCIONARIO, EMAIL_FUNCIONARIO, ADMIN_FUNCIONARIO, SENHA_FUNCIONARIO) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = null;
        try {
            ps = Conection.getConnection().prepareStatement(sql);

            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setBoolean(3, employee.getAdmin());
            ps.setString(4, employee.getPassword());

            ps.execute();
            ps.close();
            System.out.println("DAO: Funcionário cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> loadEmployees() throws SQLException{
        String sql = "SELECT ID_FUNCIONARIO, NOME_FUNCIONARIO, EMAIL_FUNCIONARIO, ADMIN_FUNCIONARIO, SENHA_FUNCIONARIO FROM funcionario";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Employee> employees = new ArrayList<>();

        try{
            ps = Conection.getConnection().prepareStatement(sql);
            resultSet = ps.executeQuery();

            while(resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("ID_FUNCIONARIO"));
                employee.setName(resultSet.getString("NOME_FUNCIONARIO"));
                employee.setEmail(resultSet.getString("EMAIL_FUNCIONARIO"));
                employee.setAdmin(resultSet.getBoolean("ADMIN_FUNCIONARIO"));
                employee.setPassword(resultSet.getString("SENHA_FUNCIONARIO"));
                employees.add(employee);
            }
        } finally {
            if(resultSet != null) resultSet.close();
            if(ps != null) ps.close();
        }
        return employees;
    }

    public Employee searchEmployee(int id) throws SQLException{
        String sql = "SELECT ID_FUNCIONARIO, NOME_FUNCIONARIO, EMAIL_FUNCIONARIO, ADMIN_FUNCIONARIO, SENHA_FUNCIONARIO FROM funcionario WHERE ID_FUNCIONARIO = ?";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Employee employee = null;

        try {
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setInt(1, id);

            resultSet = ps.executeQuery();
            if(resultSet.next()){
                employee = new Employee();
                employee.setId(resultSet.getInt("ID_FUNCIONARIO"));
                employee.setName(resultSet.getString("NOME_FUNCIONARIO"));
                employee.setEmail(resultSet.getString("EMAIL_FUNCIONARIO"));
                employee.setAdmin(resultSet.getBoolean("ADMIN_FUNCIONARIO"));
                employee.setPassword(resultSet.getString("SENHA_FUNCIONARIO"));
            }
        } finally {
            if(resultSet != null) resultSet.close();
            if(ps != null) ps.close();
        }

        return employee;
    }

    public void updateEmployee(Employee employee) throws SQLException{
        String sql = "UPDATE funcionario SET NOME_FUNCIONARIO = ?, EMAIL_FUNCIONARIO = ?, ADMIN_FUNCIONARIO = ?, SENHA_FUNCIONARIO = ? WHERE ID_FUNCIONARIO = ?";
        PreparedStatement ps = null;

        try{
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setBoolean(3, employee.getAdmin());
            ps.setString(4, employee.getPassword());
            ps.setInt(5, employee.getId());

            ps.executeUpdate();
            System.out.println("DAO: Funcionário ID " + employee.getId() + " atualizado com sucesso!");
        } finally {
            if(ps != null) ps.close();
        }
    }

    public void deletedEmployee(Employee employee) throws SQLException{
        String sql = "DELETE FROM funcionario WHERE ID_FUNCIONARIO = ?";
        PreparedStatement ps = null;

        try{
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setInt(1, employee.getId());

            ps.executeUpdate();
            System.out.println("DAO: Funcionario com o id " + employee.getId() + " deletado com sucesso!");
        } finally {
            if(ps != null) ps.close();
        }
    }
    
    public Employee getLastEmployee() throws SQLException {
        String sql = "SELECT * FROM funcionario ORDER BY ID_FUNCIONARIO DESC LIMIT 1";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;
    
        try {
            ps = Conection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
    
            if (rs.next()) {
                employee = new Employee();
                employee.setId(rs.getInt("ID_FUNCIONARIO"));
                employee.setName(rs.getString("NOME_FUNCIONARIO"));
                employee.setEmail(rs.getString("EMAIL_FUNCIONARIO"));
                employee.setAdmin(rs.getBoolean("ADMIN_FUNCIONARIO"));
                employee.setPassword(rs.getString("SENHA_FUNCIONARIO"));
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    
        return employee;
    }

    public Employee getEmployeeByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM funcionario WHERE EMAIL_FUNCIONARIO = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;
        
        try {
            ps = Conection.getConnection().prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
    
            if (rs.next()) {
                employee = new Employee();
                employee.setId(rs.getInt("ID_FUNCIONARIO"));
                employee.setName(rs.getString("NOME_FUNCIONARIO"));
                employee.setEmail(rs.getString("EMAIL_FUNCIONARIO"));
                employee.setAdmin(rs.getBoolean("ADMIN_FUNCIONARIO"));
                employee.setPassword(rs.getString("SENHA_FUNCIONARIO"));
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    
        return employee;
    }
    
    
}