package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import conection.Conection;
import entity.Funcionario;

public class FuncionarioDAO {
        public void registerFuncionario(Funcionario funcionario){
            String sql = "INSERT INTO funcionario (ID_FUNCIONARIO, NOME_FUNCIONARIO, EMAIL_FUNCIONARIO, ADMIN_FUNCIONARIO, SENHA_FUNCIONARIO) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = null;
            try {
                ps = Conection.getConnection().prepareStatement(sql);

                ps.setInt(1, funcionario.getId());
                ps.setString(2, funcionario.getNome());
                ps.setString(3, funcionario.getEmail());
                ps.setBoolean(4, funcionario.isAdmin());
                ps.setString(5, funcionario.getSenha());

                ps.execute();
                ps.close();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

