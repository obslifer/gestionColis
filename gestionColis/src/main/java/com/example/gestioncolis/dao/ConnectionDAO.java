package com.example.gestioncolis.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public ConnectionDAO() throws SQLException {
        String url = "jdbc:mysql://192.168.200.126:3306/gestion_colis";
        String user = "root";
        String pass = "";
        connection = DriverManager.getConnection(url,user,pass);
    }
}
