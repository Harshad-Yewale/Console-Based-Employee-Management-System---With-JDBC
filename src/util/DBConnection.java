package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    //Connection parameters
    private static final String Url=System.getenv("URL");
    private static final String userName=System.getenv("USERNAME");
    private static final String password=System.getenv("PASSWORD");


    //function to connect to database
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(Url,userName,password);


    }
}
