package ma.enset.javafx.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDBSingleton {
    private static Connection connection ;
    static{

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_stock","root","");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection(){
        return connection;
    }
}
