package conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {
    public static final String url = "jdbc:mysql://nozomi.proxy.rlwy.net:36361/livraria_db";
    public static final String user = "root";
    public static final String password = "MzBmDCuIjLBYNwZWznpBEYdDqbOqRvAS";

    public static Connection conn;

    // método que verififica se a conexão foi bem estabelecida
    public static Connection getConnection(){
        try {
            if(conn == null){
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            } else {
                return conn;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
