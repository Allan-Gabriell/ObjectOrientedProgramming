package conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {
    public static final String url = "jdbc:mysql://yamabiko.proxy.rlwy.net:43693/livraria_db";
    public static final String user = "root";
    public static final String password = "bhfgiATHmIIuwtmCUReUcfxxlxVScpQL";

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
            System.out.println("Error ao buscar dados no banco!");
            e.printStackTrace();
            return null;
        }
    }
}
