package sample;

import java.sql.*;

public class SqliteConnection {


    //Connection method

    public static Connection Connector()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:UserDb.db");
            return conn;

        } catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }
}
