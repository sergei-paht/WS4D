package sample;

import sample.POJO.User;

import java.sql.*;

public class DataBase {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://localhost:3306/vs4k" ;
        String dbUser = "root";
        String dbPass = "1234";

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }






}
