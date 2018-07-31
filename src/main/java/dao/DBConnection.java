package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection  {
    public static Connection getConnectionToDatabase(){

        String url ="jdbc:postgresql://localhost:5432/Exercicio";
        String user = "postgres";
        String password = "admin";

        Connection connection = null;

        try{
            Class.forName ("org.postgresql.Driver");

            connection = DriverManager.getConnection (url, user, password);

        } catch (ClassNotFoundException e) {
            System.out.println ("Where is your JDBC driver?" );
            e.printStackTrace ();
        } catch (SQLException e) {
            System.out.println ("Connection Failed! check output console" );
            e.printStackTrace ( );
        }

        if(connection != null){
            System.out.println ("Connection made in DB!" );
        }
        return connection;
    }
}
